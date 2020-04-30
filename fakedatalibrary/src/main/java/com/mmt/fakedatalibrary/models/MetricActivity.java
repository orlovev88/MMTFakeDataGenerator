package com.mmt.fakedatalibrary.models;

import io.realm.RealmObject;

public class MetricActivity extends RealmObject {
    private long timestamp;
    private String date;
    private int bpm;
    private int q;
    private int last_steps;
    private int activity_type;
    private int speed;
    private int skin_proximity;
    private int energy_exp;
    private int respiration_rate;

    public MetricActivity() {
        this.timestamp = 0;
        this.date = "";
        this.bpm = 0;
        this.q = 0;
        this.last_steps = 0;
        this.activity_type = 0;
        this.speed = 0;
        this.skin_proximity = 0;
        this.energy_exp = 0;
        this.respiration_rate = 0;
    }

    public MetricActivity(long timestamp, String date, int bpm, int q, int last_steps, int activity_type, int speed, int skin_proximity, int energy_exp, int respiration_rate) {
        this.timestamp = timestamp;
        this.date = date;
        this.bpm = bpm;
        this.q = q;
        this.last_steps = last_steps;
        this.activity_type = activity_type;
        this.speed = speed;
        this.skin_proximity = skin_proximity;
        this.energy_exp = energy_exp;
        this.respiration_rate = respiration_rate;
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

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getLast_steps() {
        return last_steps;
    }

    public void setLast_steps(int last_steps) {
        this.last_steps = last_steps;
    }

    public int getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(int activity_type) {
        this.activity_type = activity_type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSkin_proximity() {
        return skin_proximity;
    }

    public void setSkin_proximity(int skin_proximity) {
        this.skin_proximity = skin_proximity;
    }

    public int getEnergy_exp() {
        return energy_exp;
    }

    public void setEnergy_exp(int energy_exp) {
        this.energy_exp = energy_exp;
    }

    public int getRespiration_rate() {
        return respiration_rate;
    }

    public void setRespiration_rate(int respiration_rate) {
        this.respiration_rate = respiration_rate;
    }

    @Override
    public String toString() {
        return "MetricActivity{" +
                "timestamp=" + timestamp +
                ", date='" + date + '\'' +
                ", bpm=" + bpm +
                ", q=" + q +
                ", last_steps=" + last_steps +
                ", activity_type=" + activity_type +
                ", speed=" + speed +
                ", skin_proximity=" + skin_proximity +
                ", energy_exp=" + energy_exp +
                ", respiration_rate=" + respiration_rate +
                '}';
    }
}
