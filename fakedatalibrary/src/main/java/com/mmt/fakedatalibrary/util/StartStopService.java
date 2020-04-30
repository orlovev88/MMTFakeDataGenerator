package com.mmt.fakedatalibrary.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.mmt.fakedatalibrary.FakeDataGenerationService;

public class StartStopService {

    public static void startFakeDataService(Activity activity, Context context)
    {
        Log.d("toto", "Launch Service");
        Intent intent = new Intent(context, FakeDataGenerationService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            activity.startForegroundService(intent);
        } else {
            activity.startService(intent);
        }
    }

    public static void stopFakeDataService(Activity activity, Context context)
    {
        Log.d("toto", "Stop Service");
        Intent intent = new Intent(context, FakeDataGenerationService.class);
        activity.stopService(intent);
    }
}
