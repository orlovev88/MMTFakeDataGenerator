package com.mmt.fakedatalibrary.models;

import io.realm.RealmObject;

public class MetricHRV extends RealmObject {
    private long timestamp;
    private String date;
    private int beats;

    public MetricHRV() {
        this.timestamp = 0;
        date = "";
        this.beats = 0;
    }

    public MetricHRV(long timestamp, String date, int beats) {
        this.timestamp = timestamp;
        this.date = date;
        this.beats = beats;
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

    public int getBeats() {
        return beats;
    }

    public void setBeats(int beats) {
        this.beats = beats;
    }

    @Override
    public String toString() {
        return "MetricHRV{" +
                "timestamp=" + timestamp +
                ", date='" + date + '\'' +
                ", beats=" + beats +
                '}';
    }
}
