package com.fatece.manatech.model;

import java.util.Date;

public class Meeting {

    private Date date;
    private String ata;
    private Time time;

    public Meeting(Date date, String ata, Time time) {
        this.date = date;
        this.ata = ata;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAta() {
        return ata;
    }

    public void setAta(String ata) {
        this.ata = ata;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
