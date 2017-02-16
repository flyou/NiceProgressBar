package com.xdja.nicecircleprogressbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.xdja.progressbarlibrary.NiceProgressBar;

public class MainActivity extends AppCompatActivity {

    private NiceProgressBar progressbar1;
    private NiceProgressBar progressbar2;
    private NiceProgressBar progressbar3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.progressbar3 = (NiceProgressBar) findViewById(R.id.progressbar3);
        this.progressbar2 = (NiceProgressBar) findViewById(R.id.progressbar2);
        this.progressbar1 = (NiceProgressBar) findViewById(R.id.progressbar1);
        // easy way
        progressbar1.setTextMax(88).show();


        // or
        progressbar2.setTextMax(33)
        .setTextColor(getResources().getColor(R.color.red))
        .setDefaultWheelColor(getResources().getColor(R.color.sandybrown))
        .setWheelColor(getResources().getColor(R.color.yellow))
        .show();

        //or
        progressbar3.setTextMax(100)
        .showWithPercent(true)
//                .setProgressBarListener(new CircleProgressBar.ProgressBarListener() {
//                    @Override
//                    public void onProgressBarComplete() {
//                        // TODO: what you want
//                    }
//
//                    @Override
//                    public void onProgressBarStart() {
//                        // TODO: what you want
//                    }
//                })
        .show();

    }
}

