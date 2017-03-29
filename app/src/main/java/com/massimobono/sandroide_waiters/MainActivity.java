package com.massimobono.sandroide_waiters;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    private Button toggleLED;
    private boolean toggle;

    //SandroidE buttons
    private BLEGeneralIO nanoButton;
    private BLEGeneralIO redLED;


    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toggleLED = (Button) this.findViewById(R.id.button);
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

        this.nanoButton = (BLEGeneralIO) BLEContext.findViewById("nano_rbs_general_io_5");
        this.redLED = (BLEGeneralIO) BLEContext.findViewById("nano_rbs_general_io_9");

        this.toggle = false;
        this.toggleLED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggle) {
                    Log.i(TAG, "setting LED high");
                    redLED.setDigitalValueHigh(true);
                } else {
                    Log.i(TAG, "setting LED low");
                    redLED.setDigitalValueHigh(false);
                }
                toggle = !toggle;
            }
        });

        this.redLED.setOnGeneralIOEventListener(new BLEOnGeneralIOEventListener() {
            @Override
            public void onBoardInitEnded() {
                Log.i(TAG, "LED initialized!" + redLED.getDevItem() + " " + redLED.getActualDeviceName()+ " status "+ redLED.getStatus());
                redLED.setStatus(BLEGeneralIO.GENERAL_IO_DI);
                Log.i(TAG, "LED new STATUS " + redLED.getStatus());
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
                Log.i(TAG, "onGeneralIOStatusChanged " + redLED.getDevItem());
            }

            @Override
            public void onSetGeneralIOParameter(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.i(TAG, "onSetGeneralIOParameter");
            }
        });

        this.nanoButton.setOnGeneralIOEventListener(new BLEOnGeneralIOEventListener() {
            @Override
            public void onBoardInitEnded() {
                Log.i(TAG, "BUTTON initialized!" + nanoButton.getDevItem() + " " + nanoButton.getActualDeviceName());
                nanoButton.setStatus(BLEGeneralIO.GENERAL_IO_AI);
                //redLED.setDigitalValueHigh(true);
            }

            @Override
            public void onDigitalInputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.i(TAG, "onDigitalInputValueChanged: " + bleGeneralIOEvent.values[1]);
            }

            @Override
            public void onAnalogValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.i(TAG, "onAnalogValueChanged");
                //values[1] 96 se pulsante non premuto; 0.02 se il pulsante è premuto
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
                Log.i(TAG, "onGeneralIOStatusChanged "  + redLED.getDevItem());
            }

            @Override
            public void onSetGeneralIOParameter(BLEGeneralIOEvent bleGeneralIOEvent) {
                Log.i(TAG, "onSetGeneralIOParameter");
            }
        });

    }
}
