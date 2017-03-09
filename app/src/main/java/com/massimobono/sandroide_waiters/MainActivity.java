package com.massimobono.sandroide_waiters;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.massimobono.sandroide_waiters.model.ITable;
import com.massimobono.sandroide_waiters.model.TableListener;
import com.massimobono.sandroide_waiters.model.realm.RealmDAO;
import com.massimobono.sandroide_waiters.model.Model;

public class MainActivity extends AppCompatActivity {

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

        this.model = new Model(new RealmDAO(this));

        this.tableButtons = (RecyclerView) this.findViewById(R.id.tableButtons);
        this.tableButtons.setHasFixedSize(true);

        this.tableButtonsLayoutManager = new LinearLayoutManager(this);
        this.tableButtons.setLayoutManager(this.tableButtonsLayoutManager);

        this.tableButtonsAdapter = new TableButtonAdapter(this.model);
        this.tableButtons.setAdapter(this.tableButtonsAdapter);

        //we can use this to scroll to a position when a buzzer rings
        //Scroll item 2 to 20 pixels from the top
        //this.tableButtonsLayoutManager.scrollToPositionWithOffset(2, 20);

        final ITable table2 = this.model.getDao().getTable(2);
        table2.addTableListener(new TableListener() {
            @Override
            public void onBuzzOn(ITable table) {
                Log.i(TAG, "buzz on!");
            }

            @Override
            public void onBuzzOff(ITable table) {
                Log.i(TAG, "buzz off!");
            }
        });

        Handler handler = new Handler();
        Log.i(TAG, "INIT POST DELAY");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "POST DELAY NOW STARTS");
                //simulate behaviour of a customer and a waiter
                table2.setBuzzing(true);
                Log.i(TAG, "Waiting...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                table2.setBuzzing(false);
            }
        }, 2000);

    }
}
