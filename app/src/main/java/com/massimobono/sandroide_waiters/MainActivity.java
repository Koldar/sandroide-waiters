package com.massimobono.sandroide_waiters;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.massimobono.sandroide_waiters.model.ITable;
import com.massimobono.sandroide_waiters.model.Model;
import com.massimobono.sandroide_waiters.model.TableListener;
import com.massimobono.sandroide_waiters.model.TableState;
import com.massimobono.sandroide_waiters.model.standard.StandardDAO;
import com.massimobono.sandroide_waiters.model.standard.Table;

import it.unibs.sandroide.lib.activities.SandroideBaseActivity;

public class MainActivity extends SandroideBaseActivity implements TableListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    //recycle views implementaed has been inspired from https://developer.android.com/training/material/lists-cards.html
    private RecyclerView tableButtons;
    private RecyclerView.Adapter tableButtonsAdapter;
    private LinearLayoutManager tableButtonsLayoutManager;

    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the model
        this.model = new Model(new StandardDAO());

        for (ITable t : this.model.getDao().getAllTables()) {
            t.addTableListener(this);
        }

        this.tableButtons = (RecyclerView) this.findViewById(R.id.tableButtons);
        this.tableButtons.setHasFixedSize(true);

        this.tableButtonsLayoutManager = new LinearLayoutManager(this);
        this.tableButtons.setLayoutManager(this.tableButtonsLayoutManager);

        this.tableButtonsAdapter = new TableButtonAdapter(this.model);
        this.tableButtons.setAdapter(this.tableButtonsAdapter);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((Table)model.getDao().getTable(3)).getPhysicalButton().pressButton();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((Table)model.getDao().getTable(3)).getPhysicalButton().unPressButton();
                    }
                }, 2000);
            }
        }, 2000);

    }

    @Override
    public void onBuzzOn(ITable table) {

    }

    @Override
    public void onBuzzOff(ITable table) {

    }

    @Override
    public void onTableOff(TableState oldState, ITable table) {

    }

    @Override
    public void onTableWaiterNeeded(TableState oldState, ITable table) {
        Toast.makeText(this, String.format(this.getString(R.string.table_request_help), table.getName()), Toast.LENGTH_SHORT).show();
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500); // Vibrate for 500 milliseconds
    }

    @Override
    public void onTableWaiterStillNeeded(TableState oldState, ITable table) {

    }

    @Override
    public void onTableResolving(TableState oldState, ITable table) {

    }

    @Override
    public void onTableResolved(TableState oldState, ITable table) {

    }
}
