package com.mmt.fakedatalibrary;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        MyApplication.context = getApplicationContext();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                //.schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}

