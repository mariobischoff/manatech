package com.fatece.manatech.domain.acitivity;

import com.fatece.manatech.domain.time.Time;

import java.util.Date;

public class Activity {

    private String deadline;
    private double cost;
    private String des;
    private Integer time;
    private boolean done;

    public Activity(String deadline, float cost, String des, Integer time, boolean done) {
        this.deadline = deadline;
        this.cost = cost;
        this.des = des;
        this.time = time;
        this.done = done;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        String data = this.deadline.substring(0, 11);
        return "Deadline: " + data;
    }
}
