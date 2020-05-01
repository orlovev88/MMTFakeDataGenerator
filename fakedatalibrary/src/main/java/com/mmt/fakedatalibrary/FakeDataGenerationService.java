package com.mmt.fakedatalibrary;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.mmt.fakedatalibrary.models.MetricActivity;
import com.mmt.fakedatalibrary.models.MetricHRV;
import com.mmt.fakedatalibrary.models.MetricPPG;
import com.mmt.fakedatalibrary.models.MetricWorkout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmList;

import static com.mmt.fakedatalibrary.util.HourISO8601.timestampMsToHourIso8601;

public class FakeDataGenerationService extends Service {
    public static final long PERIOD = 1 * 60 * 1000; // 5 minutes

    public static final long ACTIVITY_FREQUENCE_MS = 10 * 1000; // 10 seconds
    public static final long HRV_FREQUENCE_MS      = 1 * 1000;  // 1 second
    public static final long PPG_FREQUENCE_MS      = 40;        // 40 ms
    public static final long WORKOUT_FREQUENCE_MS  = 1*1000;    // 1 second

    private static final int NOTHING  = 0;
    private static final int ACTIVITY = 1;
    private static final int HRV      = 2;
    private static final int PPG      = 3;
    private static final int WORKOUT  = 4;

    private static final boolean ACTIVITY_ENABLED = true;
    private static final boolean HRV_ENABLED      = true;
    private static final boolean PPG_ENABLED      = false;
    private static final boolean WORKOUT_ENABLED  = false;

    private static int currentMetric = NOTHING; // No metric by default

    private static long lastTimestampActivity = 0;
    private static long lastTimestampHrv      = 0;
    private static long lastTimestampPpg      = 0;
    private static long lastTimestampWorkout  = 0;

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();

    // timer handling
    private Timer mTimer = null;

    // to manage the CPU running while the screen is OFF:
    private PowerManager.WakeLock wakeLock = null;

    public FakeDataGenerationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        keepCpuActive();

        initTimestamps();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(createID(), new Notification());

        // cancel if already existed
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }

        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, PERIOD);
    }


    @Override
    public void onDestroy() {
        Log.d("toto", "onDestroy");

        // Release CPU:
        if (wakeLock != null)
        {
            wakeLock.release();
            wakeLock = null;
        }

        if(mTimer != null)
        {
            mTimer.cancel();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true); //true will remove notification
        }

        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.d("toto", "onTaskRemoved");
    }


    private void startMyOwnForeground(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";
            String channelName = "My Background Service";
            NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
            chan.setLightColor(Color.BLUE);
            chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            assert manager != null;
            manager.createNotificationChannel(chan);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
            Notification notification = notificationBuilder.setOngoing(true)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("App is running in background")
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build();
            startForeground(createID(), notification);
        }

    }

    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread:
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    goToNextMetric();
                }

            });
        }
    }

    public int createID() {
        Date now = new Date();
        int id = Integer.parseInt(new SimpleDateFormat("ddHHmmss", Locale.FRENCH).format(now));
        return id;
    }

    // This function is used to keep the CPU active while the screen is off and the phone is in standby
    private void keepCpuActive()
    {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "NotifBacklgroundStartStop:Tag");
        wakeLock.acquire();

        // NB : to release the CPU, use : wakeLock.release();
    }


    private static void generateFakeDataActivity() {
        // Activity : every 10 seconds
        Log.d("toto", "Generate fake Activity");

        RealmList<MetricActivity> activityList = new RealmList();

        for(int i=0; i<(PERIOD/ACTIVITY_FREQUENCE_MS); i++)
        {
            activityList.add(new MetricActivity(lastTimestampActivity, timestampMsToHourIso8601(lastTimestampActivity), randNumber(50,170), randNumber(0,4), randNumber(1,7), randNumber(0,30), randNumber(0,20), 0, randNumber(0, 200), 0));
            lastTimestampActivity+= ACTIVITY_FREQUENCE_MS; // Adding 10 seconds to the timestamp, since each point represents 10 seconds
        }

        saveFakeActivityData(activityList);
    }


    private static void saveFakeActivityData(final RealmList<MetricActivity> list)
    {
        Realm realm;
        realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                List<MetricActivity> realmActivity = bgRealm.copyToRealmOrUpdate(list);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.d("toto", "onSuccess: Activity data written successfully. Go to next metric");
                goToNextMetric();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.d("toto", "onError: Error occured (during Activity data saving). Go to next metric");
                goToNextMetric();
            }
        });
    }


    private static void generateFakeDataHrv() {
        // HRV : every 1 second
        Log.d("toto", "Generate fake HRV");

        RealmList<MetricHRV> hrvList = new RealmList();

        for(int i=0; i<(PERIOD/HRV_FREQUENCE_MS); i++)
        {
            hrvList.add(new MetricHRV(lastTimestampHrv, timestampMsToHourIso8601(lastTimestampHrv), randNumber(400,2000)));
            lastTimestampHrv += HRV_FREQUENCE_MS; // Adding 30 seconds to the timestamp, since each point represents 30 seconds
        }

        saveFakeHrvData(hrvList);
    }

    private static void saveFakeHrvData(final RealmList<MetricHRV> list)
    {
        Realm realm;
        realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                List<MetricHRV> realmHrv = bgRealm.copyToRealmOrUpdate(list);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.d("toto", "onSuccess: Data written successfully. Continue with next metric");
                goToNextMetric();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.d("toto", "onError: Error occured. Continue with next metric");
                goToNextMetric();
            }
        });
    }


    private static void generateFakeDataPpg() {
        // PPG : every 40 ms
        Log.d("toto", "Generate fake PPG");

        RealmList<MetricPPG> ppgList = new RealmList();

        for(int i=0; i<(PERIOD/PPG_FREQUENCE_MS); i++)
        {
            ppgList.add(new MetricPPG(lastTimestampPpg, timestampMsToHourIso8601(lastTimestampPpg), randNumber(14000,17000), randNumber(240, 270), randNumber(50,170)));
            lastTimestampPpg += PPG_FREQUENCE_MS; // Adding 30 seconds to the timestamp, since each point represents 30 seconds
        }

        saveFakePpgData(ppgList);
    }


    private static void saveFakePpgData(final RealmList<MetricPPG> list)
    {
        Realm realm;
        realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                List<MetricPPG> realmPpg = bgRealm.copyToRealmOrUpdate(list);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.d("toto", "onSuccess: Data written successfully. Continue with next metric");
                goToNextMetric();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.d("toto", "onError: Error occured. Continue with next metric");
                goToNextMetric();
            }
        });
    }

    private static void generateFakeDataWorkout() {
        // Workout : every 1 second
        Log.d("toto", "Generate fake Workout");

        RealmList<MetricWorkout> workoutList = new RealmList();

        for(int i=0; i<(PERIOD/WORKOUT_FREQUENCE_MS); i++)
        {
            workoutList.add(new MetricWorkout(lastTimestampWorkout, timestampMsToHourIso8601(lastTimestampWorkout), randNumber(50,180), randNumber(0, 4), randNumber(0,20), 1, randNumber(0,30), randNumber(0,10), randNumber(0,10), randNumber(0,100), randNumber(0,10)));
            lastTimestampWorkout += WORKOUT_FREQUENCE_MS; // Adding 30 seconds to the timestamp, since each point represents 30 seconds
        }

        saveFakeWorkoutData(workoutList);
    }

    private static void saveFakeWorkoutData(final RealmList<MetricWorkout> list)
    {
        Realm realm;
        realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                List<MetricWorkout> realmWorkout = bgRealm.copyToRealmOrUpdate(list);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.d("toto", "onSuccess: Data written successfully. Finish !");
                goToNextMetric();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.d("toto", "onError: Error occured. Finish !");
                goToNextMetric();
            }
        });
    }

    private static long generateTimestamp()
    {
        Date date = new Date();

        long timestamp = date.getTime();
        timestamp -= /*5*60*1000*/PERIOD; // In the fake sleep, we have 924 points (1 point each 30 seconds)

        return timestamp;
    }


    public static int randNumber(int min, int max)
    {
        if (min > max || (max - min + 1 > Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("Invalid range");
        }

        return new Random().nextInt(max - min + 1) + min;
    }


    private static void goToNextMetric()
    {
        // The metric will be called in this order : ACTIVITY / HRV / PPG / WORKOUT
        // If one of them is not enabled, it will be skipped, and we will try with the next one.
        // If we are currently on the last metric (WORKOUT), nothing will happen.

        switch (currentMetric)
        {
            case NOTHING:
                currentMetric = ACTIVITY;
                if(ACTIVITY_ENABLED)
                {
                    generateFakeDataActivity();
                } else {
                    goToNextMetric();
                }
                break;

            case ACTIVITY:
                currentMetric = HRV;
                if(HRV_ENABLED)
                {
                    generateFakeDataHrv();
                } else {
                    goToNextMetric();
                }
                break;

            case HRV:
                currentMetric = PPG;
                if(PPG_ENABLED)
                {
                    generateFakeDataPpg();
                } else {
                    goToNextMetric();
                }
                break;

            case PPG:
                currentMetric = WORKOUT;
                if(WORKOUT_ENABLED)
                {
                    generateFakeDataWorkout();
                } else {
                    goToNextMetric();
                }
                break;

            case WORKOUT:
                currentMetric = NOTHING;
                break;
        }
    }

    private void initTimestamps()
    {
        long lastTimestamp = generateTimestamp();

        if(lastTimestampActivity == 0)
        {
            lastTimestampActivity = lastTimestamp;
        }
        if(lastTimestampHrv == 0)
        {
            lastTimestampHrv = lastTimestamp;
        }
        if(lastTimestampPpg == 0)
        {
            lastTimestampPpg = lastTimestamp;
        }
        if(lastTimestampWorkout == 0)
        {
            lastTimestampWorkout = lastTimestamp;
        }
    }

}
