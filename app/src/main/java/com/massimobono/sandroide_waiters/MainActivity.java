package com.massimobono.sandroide_waiters;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.massimobono.sandroide_waiters.model.Model;
import com.massimobono.sandroide_waiters.model.standard.StandardDAO;
import com.massimobono.sandroide_waiters.model.standard.Table;

import it.unibs.sandroide.lib.activities.SandroideBaseActivity;
import it.unibs.sandroide.lib.item.generalIO.BLEGeneralIO;
import it.unibs.sandroide.lib.item.generalIO.BLEGeneralIOEvent;
import it.unibs.sandroide.lib.item.generalIO.BLEOnGeneralIOEventListener;

public class MainActivity extends SandroideBaseActivity{

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

        this.tableButtons = (RecyclerView) this.findViewById(R.id.tableButtons);
        this.tableButtons.setHasFixedSize(true);

        this.tableButtonsLayoutManager = new LinearLayoutManager(this);
        this.tableButtons.setLayoutManager(this.tableButtonsLayoutManager);

        this.tableButtonsAdapter = new TableButtonAdapter(this.model);
        this.tableButtons.setAdapter(this.tableButtonsAdapter);

        //we can use this to scroll to a position when a buzzer rings
        //Scroll item 2 to 20 pixels from the top
        //this.tableButtonsLayoutManager.scrollToPositionWithOffset(2, 20);

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
}
