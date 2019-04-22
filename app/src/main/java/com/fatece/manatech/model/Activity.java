package com.fatece.manatech.model;

import java.util.Date;

public class Activity {

    private Date deadline;
    private double cost;
    private String des;
    private Time time;

    public Activity(Date deadline, float cost, String des, Time time) {
        this.deadline = deadline;
        this.cost = cost;
        this.des = des;
        this.time = time;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
