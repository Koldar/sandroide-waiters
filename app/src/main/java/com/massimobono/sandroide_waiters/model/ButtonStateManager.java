package com.massimobono.sandroide_waiters.model;

import android.util.Log;
import android.widget.Toast;

import com.massimobono.sandroide_waiters.R;
import com.massimobono.sandroide_waiters.TableButtonAdapter;

/**
 * Classes used to
 *
 * Created by massi on 3/29/2017.
 */
public class ButtonStateManager implements TableListener {

    private static final String TAG = ButtonStateManager.class.getSimpleName();

    private TableButtonAdapter.ViewHolder viewHolder;

    private ITable table;
    private Thread thread;
    private boolean stopThread;
    private double timeElapsedWhenRequest;
    private int resourceToDrawInThread;

    public ButtonStateManager(ITable table, TableButtonAdapter.ViewHolder vh) {
        this.table = table;
        this.viewHolder = vh;
        this.thread = null;
        this.stopThread = false;
        this.timeElapsedWhenRequest = 0;
    }

    @Override
    public void onBuzzOn(ITable table) {

    }

    @Override
    public void onBuzzOff(ITable table) {

    }

    @Override
    public void onTableOff(TableState oldState, ITable table) {
        this.viewHolder.setImage(R.drawable.button_gray);
    }

    @Override
    public void onTableWaiterNeeded(TableState oldState, final ITable table) {
        this.timeElapsedWhenRequest = System.currentTimeMillis();
        this.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean active = true;
                while (!stopThread) {
                    resourceToDrawInThread = active ? R.drawable.button_yellow : R.drawable.button_gray;
                    viewHolder.setImage(resourceToDrawInThread);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if ((System.currentTimeMillis() - timeElapsedWhenRequest) >= (10 * 1e3)) {
                        Log.i(TAG, "setting status to waiter still needed");
                        table.setStatus(TableState.WAITER_STILL_NEEDED);
                    } else {
                        active = !active;
                    }
                }
            }
        });
        this.stopThread = false;
        this.thread.start();
    }

    @Override
    public void onTableWaiterStillNeeded(TableState oldState, ITable table) {
        this.stopThread = true;
        this.viewHolder.setImage(R.drawable.button_red);
    }

    @Override
    public void onTableResolving(TableState oldState, ITable table) {
        this.viewHolder.setImage(R.drawable.button_gray);
    }

    @Override
    public void onTableResolved(TableState oldState, ITable table) {
        this.viewHolder.setImage(R.drawable.button_gray);
    }
}
