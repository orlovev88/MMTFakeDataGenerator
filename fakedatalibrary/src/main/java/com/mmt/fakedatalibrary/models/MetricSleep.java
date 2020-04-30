package com.mmt.fakedatalibrary.models;

import io.realm.RealmObject;

public class MetricSleep extends RealmObject {
    private long timestamp;
    private String date;
    private int sleepState; // Awake = 4, RemSleep = 3, LightSleep = 2, DeepSleep = 1, Unspecified = 0

    public MetricSleep() {
        this.timestamp = 0;
        this.date = "";
        this.sleepState = 0;
    }

    public MetricSleep(long timestamp, String date, int sleepState) {
        this.timestamp = timestamp;
        this.date = date;
        this.sleepState = sleepState;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSleepState() {
        return sleepState;
    }

    public void setSleepState(int sleepState) {
        this.sleepState = sleepState;
    }

    @Override
    public String toString() {
        return "MetricSleep{" +
                "timestamp=" + timestamp +
                ", date='" + date + '\'' +
                ", sleepState=" + sleepState +
                '}';
    }
}
