package com.massimobono.sandroide_waiters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar splashScreenProgressBar;
    private ImageView splashScreenImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        this.splashScreenImage = (ImageView) this.findViewById(R.id.splashScreenImage);
        this.splashScreenProgressBar = (ProgressBar) this.findViewById(R.id.splashScreenProgressBar);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
