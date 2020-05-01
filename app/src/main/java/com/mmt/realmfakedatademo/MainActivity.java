package com.mmt.realmfakedatademo;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.mmt.fakedatalibrary.FakeDataGenerationService.enableActivity;
import static com.mmt.fakedatalibrary.FakeDataGenerationService.enableHRV;
import static com.mmt.fakedatalibrary.FakeDataGenerationService.enablePPG;
import static com.mmt.fakedatalibrary.FakeDataGenerationService.enableWorkout;
import static com.mmt.fakedatalibrary.FakeDataGenerationService.setFakeDataRefreshPeriodMs;
import static com.mmt.fakedatalibrary.util.MMTfakeSleep.generateFakeSleepData;
import static com.mmt.fakedatalibrary.util.StartStopService.startFakeDataService;
import static com.mmt.fakedatalibrary.util.StartStopService.stopFakeDataService;
import static com.mmt.fakedatalibrary.util.Util.eraseAllRealmDatabase;

public class MainActivity extends AppCompatActivity {

    private Button mBtnStartService, mBtnStopService, mBtnFakeSleep, mBtnSetPeriod, mBtnEnableWorkout, mBtnDisableWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eraseAllRealmDatabase(); // Erase all the data on the memory, to start with a clean database.

        mBtnStartService = (Button) findViewById(R.id.button_start_service);
        mBtnStopService  = (Button) findViewById(R.id.button_stop_service);
        mBtnFakeSleep    = (Button) findViewById(R.id.button_fake_sleep);
        mBtnSetPeriod = (Button) findViewById(R.id.button_set_period);
        mBtnEnableWorkout = (Button) findViewById(R.id.button_enable_workout);
        mBtnDisableWorkout = (Button) findViewById(R.id.button_disable_workout);

        mBtnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This function starts the background service which generates fake data every
                // 1 minute (it can be adjusted with the PERIOD parameter in the FakeDataGerenationService)
                // By default, only Activity and Hrv data are enabled. PPG and workout can be
                // enabled with enablePPG(true) and enableWorkout(true).
                startFakeDataService(MainActivity.this, getApplicationContext());
            }
        });

        mBtnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This function stops the FakeDataGenerationService:
                stopFakeDataService(MainActivity.this, getApplicationContext());
            }
        });

        mBtnFakeSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This function generates a fake sleep.
                // The generated sleep is always with the same pattern, and the end date is always
                // the current date at 07:40:00
                // This function has not been added in the FakeDataGenerationService, since the app
                // can't get the data of the night regularly like the other metrics.
                generateFakeSleepData();
            }
        });

        mBtnSetPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFakeDataRefreshPeriodMs(30*1000L);
            }
        });

        mBtnEnableWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableWorkout(true);
            }
        });

        mBtnDisableWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableWorkout(false);
            }
        });
    }
}
