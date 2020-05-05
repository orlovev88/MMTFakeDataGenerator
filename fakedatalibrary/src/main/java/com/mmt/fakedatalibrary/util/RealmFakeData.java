package com.mmt.fakedatalibrary.util;

import android.util.Log;

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

    public static RealmResults getRawDataBetweenTimestamps(DataTypeEnum dataTypeEnum, long timestampStart, long timestampStop)
    {
        Realm realm;
        realm = Realm.getDefaultInstance();
        final String TIMESTAMP_FIELD = "timestamp";

        RealmResults results;

        switch(dataTypeEnum)
        {
            case ACTIVITY:
                results = realm.where(MetricActivity.class)
                        .greaterThanOrEqualTo(TIMESTAMP_FIELD, timestampStart)
                        .lessThanOrEqualTo(TIMESTAMP_FIELD, timestampStop)
                        .findAll();
                break;

            case HRV:
                results = realm.where(MetricHRV.class)
                        .greaterThanOrEqualTo(TIMESTAMP_FIELD, timestampStart)
                        .lessThanOrEqualTo(TIMESTAMP_FIELD, timestampStop)
                        .findAll();
                break;

            case PPG:
                results = realm.where(MetricPPG.class)
                        .greaterThanOrEqualTo(TIMESTAMP_FIELD, timestampStart)
                        .lessThanOrEqualTo(TIMESTAMP_FIELD, timestampStop)
                        .findAll();
                break;

            case SLEEP:
                results = realm.where(MetricSleep.class)
                        .greaterThanOrEqualTo(TIMESTAMP_FIELD, timestampStart)
                        .lessThanOrEqualTo(TIMESTAMP_FIELD, timestampStop)
                        .findAll();
                break;

            case WORKOUT:
                results = realm.where(MetricWorkout.class)
                        .greaterThanOrEqualTo(TIMESTAMP_FIELD, timestampStart)
                        .lessThanOrEqualTo(TIMESTAMP_FIELD, timestampStop)
                        .findAll();
                break;

            default:
                results = null;
                break;
        }

        return results;
    }

    public static RealmResults getRawDataOlderOrEqualThanTimestamp(DataTypeEnum dataTypeEnum, long timestampMax)
    {
        Realm realm;
        realm = Realm.getDefaultInstance();
        final String TIMESTAMP_FIELD = "timestamp";

        RealmResults results;

        switch(dataTypeEnum)
        {
            case ACTIVITY:
                results = realm.where(MetricActivity.class)
                        .lessThanOrEqualTo(TIMESTAMP_FIELD, timestampMax)
                        .findAll();
                break;

            case HRV:
                results = realm.where(MetricHRV.class)
                        .lessThanOrEqualTo(TIMESTAMP_FIELD, timestampMax)
                        .findAll();
                break;

            case PPG:
                results = realm.where(MetricPPG.class)
                        .lessThanOrEqualTo(TIMESTAMP_FIELD, timestampMax)
                        .findAll();
                break;

            case SLEEP:
                results = realm.where(MetricSleep.class)
                        .lessThanOrEqualTo(TIMESTAMP_FIELD, timestampMax)
                        .findAll();
                break;

            case WORKOUT:
                results = realm.where(MetricWorkout.class)
                        .lessThanOrEqualTo(TIMESTAMP_FIELD, timestampMax)
                        .findAll();
                break;

            default:
                results = null;
                break;
        }

        return results;
    }

    public static RealmResults getRawDataNewerOrEqualThanTimestamp(DataTypeEnum dataTypeEnum, long timestampMin)
    {
        Realm realm;
        realm = Realm.getDefaultInstance();
        final String TIMESTAMP_FIELD = "timestamp";

        RealmResults results;

        switch(dataTypeEnum)
        {
            case ACTIVITY:
                results = realm.where(MetricActivity.class)
                        .greaterThanOrEqualTo(TIMESTAMP_FIELD, timestampMin)
                        .findAll();
                break;

            case HRV:
                results = realm.where(MetricHRV.class)
                        .greaterThanOrEqualTo(TIMESTAMP_FIELD, timestampMin)
                        .findAll();
                break;

            case PPG:
                results = realm.where(MetricPPG.class)
                        .greaterThanOrEqualTo(TIMESTAMP_FIELD, timestampMin)
                        .findAll();
                break;

            case SLEEP:
                results = realm.where(MetricSleep.class)
                        .greaterThanOrEqualTo(TIMESTAMP_FIELD, timestampMin)
                        .findAll();
                break;

            case WORKOUT:
                results = realm.where(MetricWorkout.class)
                        .greaterThanOrEqualTo(TIMESTAMP_FIELD, timestampMin)
                        .findAll();
                break;

            default:
                results = null;
                break;
        }

        return results;
    }

    public static void deleteRawData(DataTypeEnum dataTypeEnum)
    {
        Realm realm;
        realm = Realm.getDefaultInstance();

        switch(dataTypeEnum)
        {
            case ACTIVITY:
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<MetricActivity> result = realm.where(MetricActivity.class).findAll();
                        result.deleteAllFromRealm();
                    }
                });
                break;

            case HRV:
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<MetricHRV> result = realm.where(MetricHRV.class).findAll();
                        result.deleteAllFromRealm();
                    }
                });
                break;

            case PPG:
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<MetricPPG> result = realm.where(MetricPPG.class).findAll();
                        result.deleteAllFromRealm();
                    }
                });
                break;

            case SLEEP:
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<MetricSleep> result = realm.where(MetricSleep.class).findAll();
                        result.deleteAllFromRealm();
                    }
                });
                break;

            case WORKOUT:
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<MetricWorkout> result = realm.where(MetricWorkout.class).findAll();
                        result.deleteAllFromRealm();
                    }
                });
                break;

            default:
                Log.e("RealmFakeData: deleteRawData", "Bad input parameter");
                break;
        }
    }
}
