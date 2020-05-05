package com.mmt.fakedatalibrary.util;

import com.mmt.fakedatalibrary.models.MetricActivity;
import com.mmt.fakedatalibrary.models.MetricHRV;
import com.mmt.fakedatalibrary.models.MetricPPG;
import com.mmt.fakedatalibrary.models.MetricSleep;
import com.mmt.fakedatalibrary.models.MetricWorkout;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmFakeData {

    public enum DataTypeEnum {
        ACTIVITY,
        HRV,
        PPG,
        SLEEP,
        WORKOUT;
    }

    public static RealmResults getRawData(DataTypeEnum dataTypeEnum)
    {
        Realm realm;
        realm = Realm.getDefaultInstance();

        RealmResults results;

        switch(dataTypeEnum)
        {
            case ACTIVITY:
                results = realm.where(MetricActivity.class).findAll();
                break;

            case HRV:
                results = realm.where(MetricHRV.class).findAll();
                break;

            case PPG:
                results = realm.where(MetricPPG.class).findAll();
                break;

            case SLEEP:
                results = realm.where(MetricSleep.class).findAll();
                break;

            case WORKOUT:
                results = realm.where(MetricWorkout.class).findAll();
                break;

                default:
                    results = null;
                    break;
        }

        return results;
    }
}
