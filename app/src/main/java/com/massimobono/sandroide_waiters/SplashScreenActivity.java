package com.massimobono.sandroide_waiters;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

//FIXME this is actually a bad way to implement a splash screen. The right way is described here: https://www.bignerdranch.com/blog/splash-screens-the-right-way/
/**
 * This activity handles the loading screen of the application
 */
public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar splashScreenProgressBar;
    private ImageView splashScreenImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        this.splashScreenImage = (ImageView) this.findViewById(R.id.splashScreenImage);
        this.splashScreenProgressBar = (ProgressBar) this.findViewById(R.id.splashScreenProgressBar);
        this.splashScreenProgressBar.setMax(100);
        this.splashScreenProgressBar.setProgress(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.loadApplicationData();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Loads application data and uses a progress bar to show the loading to the user
     *
     * <p>Note: at the moment this functtion loads nothing</p>
     *
     * The implementation is based upon <a href="https://developer.android.com/reference/android/os/AsyncTask.html">asynch task</a>
     */
    private void loadApplicationData() {
        new AsyncTask<Integer, Integer, Integer>() {

            private int steps = 5;

            @Override
            protected Integer doInBackground(Integer... params) {
                for (int i=0; i<steps; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                    publishProgress((int)((100./steps) * i));
                }
                return 0;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                splashScreenProgressBar.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                  callMainActivity();
            }
        }.execute();
    }


    /**
     * Create an intent to go to {@link MainActivity}
     */
    private void callMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
