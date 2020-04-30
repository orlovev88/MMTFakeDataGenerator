package com.mmt.fakedatalibrary.util;

import io.realm.Realm;

public class Util {

    public static void eraseAllRealmDatabase()
    {
        Realm realm;

        realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }
}
