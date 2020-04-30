package com.mmt.realmfakedatademo;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.mmt.fakedatalibrary.util.StartStopService.startFakeDataService;
import static com.mmt.fakedatalibrary.util.StartStopService.stopFakeDataService;

public class MainActivity extends AppCompatActivity {

    private Button mBtnStartService, mBtnStopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnStartService = (Button) findViewById(R.id.button_start_service);
        mBtnStopService  = (Button) findViewById(R.id.button_stop_service);

        mBtnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFakeDataService(MainActivity.this, getApplicationContext());
            }
        });

        mBtnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopFakeDataService(MainActivity.this, getApplicationContext());
            }
        });
    }
}
