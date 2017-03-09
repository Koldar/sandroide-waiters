package com.massimobono.sandroide_waiters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.massimobono.sandroide_waiters.model.realm.RealmDAO;
import com.massimobono.sandroide_waiters.model.Model;

public class MainActivity extends AppCompatActivity {

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
    }
}
