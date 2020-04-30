package com.mmt.fakedatalibrary.models;

import io.realm.RealmObject;

public class MetricPPG extends RealmObject {
    private long timestamp;
    private String date;
    private int ppg;
    private int acc;
    private int bpm;

    public MetricPPG() {
        this.timestamp = 0;
        this.date = "";
        this.ppg = 0;
        this.acc = 0;
        this.bpm = 0;
    }

    public MetricPPG(long timestamp, String date, int ppg, int acc, int bpm) {
        this.timestamp = timestamp;
        this.date = date;
        this.ppg = ppg;
        this.acc = acc;
        this.bpm = bpm;
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

    public int getPpg() {
        return ppg;
    }

    public void setPpg(int ppg) {
        this.ppg = ppg;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    @Override
    public String toString() {
        return "MetricPPG{" +
                "timestamp=" + timestamp +
                ", date='" + date + '\'' +
                ", ppg=" + ppg +
                ", acc=" + acc +
                ", bpm=" + bpm +
                '}';
    }
}
