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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import it.unibs.sandroide.lib.BLEContext;
import it.unibs.sandroide.lib.activities.SandroideBaseActivity;
import it.unibs.sandroide.lib.item.generalIO.BLEGeneralIO;
import it.unibs.sandroide.lib.item.generalIO.BLEGeneralIOEvent;
import it.unibs.sandroide.lib.item.generalIO.BLEOnGeneralIOEventListener;

public class MainActivity extends SandroideBaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    //recycle views implementaed has been inspired from https://developer.android.com/training/material/lists-cards.html
    private RecyclerView tableButtons;
    private RecyclerView.Adapter tableButtonsAdapter;
    private LinearLayoutManager tableButtonsLayoutManager;

    //SandroidE buttons
    private BLEGeneralIO nanoButton;
    private BLEGeneralIO redLED;


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

        this.nanoButton = (BLEGeneralIO) BLEContext.findViewById("nano_rbs_general_io_11");
        this.redLED = (BLEGeneralIO) BLEContext.findViewById("nano_rbs_general_io_9");

        Collection<String> pinId = Arrays.asList("8", "0", "29", "4", "6", "19", "9", "10", "28", "15", "11", "5", "1", "3", "2", "7");
        for (String pd : pinId) {
            Log.i(TAG, "pin " + pd + " \"" + BLEContext.findViewById("nano_rbs_general_io_" + pd)+ "\"");
        }

        this.redLED.setOnGeneralIOEventListener(new BLEOnGeneralIOEventListener() {
            @Override
            public void onBoardInitEnded() {
                Log.i(TAG, "RED LED initialized!");
                redLED.setStatus(BLEGeneralIO.GENERAL_IO_DO);
            }

            @Override
            public void onDigitalInputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
            }

            @Override
            public void onAnalogValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
            }

            @Override
            public void onDigitalOutputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.i(TAG, "red led event! " + bleGeneralIOEvent.values[1]);
            }

            @Override
            public void onServoValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onPWMValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onGeneralIOStatusChanged(BLEGeneralIOEvent bleGeneralIOEvent) {

            }

            @Override
            public void onSetGeneralIOParameter(BLEGeneralIOEvent bleGeneralIOEvent) {

            }
        });

        this.nanoButton.setOnGeneralIOEventListener(new BLEOnGeneralIOEventListener() {
            @Override
            public void onBoardInitEnded() {
                Log.i(TAG, "BUTTON initialized!");
                nanoButton.setStatus(BLEGeneralIO.GENERAL_IO_DI);
                redLED.setDigitalValueHigh(true);
            }

            @Override
            public void onDigitalInputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.i(TAG, "onDigitalInputValueChanged: " + bleGeneralIOEvent.values[1]);
            }

            @Override
            public void onAnalogValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.i(TAG, "onAnalogValueChanged");
            }

            @Override
            public void onDigitalOutputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.i(TAG, "onDigitalOutputValueChanged");
            }

            @Override
            public void onServoValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.i(TAG, "onServoValueChanged");
            }

            @Override
            public void onPWMValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.i(TAG, "onPWMValueChanged");
            }

            @Override
            public void onGeneralIOStatusChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.i(TAG, "onGeneralIOStatusChanged");
            }

            @Override
            public void onSetGeneralIOParameter(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.i(TAG, "onSetGeneralIOParameter");
            }
        });

//        Handler h = new Handler();
//        h.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG, "setting led high!");
//                redLED.setDigitalValueHigh(true);
//                Log.i(TAG, "setting led high DONE");
//            }
//        }, 12000);

//        final ITable table2 = this.model.getDao().getTable(2);
//        table2.addTableListener(new TableListener() {
//            @Override
//            public void onBuzzOn(ITable table) {
//                Log.i(TAG, "buzz on!");
//            }
//
//            @Override
//            public void onBuzzOff(ITable table) {
//                Log.i(TAG, "buzz off!");
//            }
//        });
//
//        Handler handler = new Handler();
//        Log.i(TAG, "INIT POST DELAY");
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG, "POST DELAY NOW STARTS");
//                //simulate behaviour of a customer and a waiter
//                table2.setBuzzing(true);
//                Log.i(TAG, "Waiting...");
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                table2.setBuzzing(false);
//            }
//        }, 2000);

    }
}
