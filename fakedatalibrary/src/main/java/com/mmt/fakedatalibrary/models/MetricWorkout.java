package com.mmt.fakedatalibrary.models;

import io.realm.RealmObject;

public class MetricWorkout extends RealmObject {
    private long timestamp;
    private String date;
    private int bpm;
    private int q;
    private int last_steps;
    private int activity_type;
    private int speed;
    private int energy_ex;
    private int respiration_rate;
    private int vo2_max;
    private int cfi;

    public MetricWorkout() {
        this.timestamp = 0;
        this.date = "";
        this.bpm = 0;
        this.q = 0;
        this.last_steps = 0;
        this.activity_type = 0;
        this.speed = 0;
        this.energy_ex = 0;
        this.respiration_rate = 0;
        this.vo2_max = 0;
        this.cfi = 0;
    }

    public MetricWorkout(long timestamp, String date, int bpm, int q, int last_steps, int activity_type, int speed, int energy_ex, int respiration_rate, int vo2_max, int cfi) {
        this.timestamp = timestamp;
        this.date = date;
        this.bpm = bpm;
        this.q = q;
        this.last_steps = last_steps;
        this.activity_type = activity_type;
        this.speed = speed;
        this.energy_ex = energy_ex;
        this.respiration_rate = respiration_rate;
        this.vo2_max = vo2_max;
        this.cfi = cfi;
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

    public int getEnergy_ex() {
        return energy_ex;
    }

    public void setEnergy_ex(int energy_ex) {
        this.energy_ex = energy_ex;
    }

    public int getRespiration_rate() {
        return respiration_rate;
    }

    public void setRespiration_rate(int respiration_rate) {
        this.respiration_rate = respiration_rate;
    }

    public int getVo2_max() {
        return vo2_max;
    }

    public void setVo2_max(int vo2_max) {
        this.vo2_max = vo2_max;
    }

    public int getCfi() {
        return cfi;
    }

    public void setCfi(int cfi) {
        this.cfi = cfi;
    }

    @Override
    public String toString() {
        return "MetricWorkout{" +
                "timestamp=" + timestamp +
                ", date='" + date + '\'' +
                ", bpm=" + bpm +
                ", q=" + q +
                ", last_steps=" + last_steps +
                ", activity_type=" + activity_type +
                ", speed=" + speed +
                ", energy_ex=" + energy_ex +
                ", respiration_rate=" + respiration_rate +
                ", vo2_max=" + vo2_max +
                ", cfi=" + cfi +
                '}';
    }
}
