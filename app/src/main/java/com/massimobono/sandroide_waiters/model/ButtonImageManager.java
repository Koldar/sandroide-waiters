package com.massimobono.sandroide_waiters.model;

import android.widget.ImageView;

import com.massimobono.sandroide_waiters.R;

/**
 * Created by massi on 3/29/2017.
 */
public class ButtonImageManager implements TableListener{

    private ImageView imageView;

    private Thread thread;
    private boolean stopThread;

    public ButtonImageManager(ImageView iv) {
        this.imageView = iv;
        this.thread = null;
        this.stopThread = false;
    }

    public void applyStateToImage(TableState ts) {
        switch (ts) {
            case OFF:
                this.imageView.setImageResource(R.drawable.button_gray);
                break;
            case WAITER_NEEDED:
                this.thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!stopThread) {
                            try {
                                imageView.setImageResource(R.drawable.button_yellow);
                                Thread.sleep(1000);
                                imageView.setImageResource(R.drawable.button_gray);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                this.stopThread = false;
                this.thread.start();

                break;
            case WAITER_STILL_NEEDED:
                this.stopThread = true;
                this.imageView.setImageResource(R.drawable.button_red);
                break;
            case RESOLVING:
                this.imageView.setImageResource(R.drawable.button_gray);
                break;
            case RESOLVED:
                this.imageView.setImageResource(R.drawable.button_gray);
                break;
        }
    }


    @Override
    public void onBuzzOn(ITable table) {

    }

    @Override
    public void onBuzzOff(ITable table) {

    }

    @Override
    public void onTableOff(TableState oldState, ITable table) {
        this.applyStateToImage(table.getStatus());
    }

    @Override
    public void onTableWaiterNeeded(TableState oldState, ITable table) {
        this.applyStateToImage(table.getStatus());
    }

    @Override
    public void onTableWaiterStillNeeded(TableState oldState, ITable table) {
        this.applyStateToImage(table.getStatus());
    }

    @Override
    public void onTableResolving(TableState oldState, ITable table) {
        this.applyStateToImage(table.getStatus());
    }

    @Override
    public void onTableResolved(TableState oldState, ITable table) {
        this.applyStateToImage(table.getStatus());
    }
}
