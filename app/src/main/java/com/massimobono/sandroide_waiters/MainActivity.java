package com.massimobono.sandroide_waiters;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.massimobono.sandroide_waiters.dao.RealmDAO;
import com.massimobono.sandroide_waiters.model.Model;

public class MainActivity extends AppCompatActivity {

    //recycle views implementaed has been inspired from https://developer.android.com/training/material/lists-cards.html

    private RecyclerView tableButtons;
    private RecyclerView.Adapter tableButtonsAdapter;
    private RecyclerView.LayoutManager tableButtonsLayoutManager;

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
    }
}
