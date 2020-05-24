package com.mmt.fakedatalibrary.util;

import com.mmt.fakedatalibrary.GeneratorModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Util {

    private static RealmConfiguration realmConfiguration() {
        return new RealmConfiguration.Builder()
                .modules(new GeneratorModule())
                .name("generator.realm")
                .build();
    }

    public static void eraseAllRealmDatabase()
    {
        Realm realm;

        realm = Realm.getInstance(realmConfiguration());

        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }
}
