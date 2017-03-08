package com.massimobono.sandroide_waiters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tableButtons;
    private RecyclerView.Adapter tableButtonsAdapter;
    private RecyclerView.LayoutManager tableButtonsLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
