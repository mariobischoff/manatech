package com.fatece.manatech.domain.meeting;

import com.fatece.manatech.domain.time.Time;

import java.util.Date;

public class Meeting {

    private String dateTime;
    private String ata;
    private Integer id_time;

    public Meeting(String dateTime, String ata, Integer id_time) {
        this.dateTime = dateTime;
        this.ata = ata;
        this.id_time = id_time;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getAta() {
        return ata;
    }

    public void setAta(String ata) {
        this.ata = ata;
    }

    public Integer getId_time() {
        return id_time;
    }

    public void setId_time(Integer id_time) {
        this.id_time = id_time;
    }

    @Override
    public String toString() {
        String data = this.dateTime.substring(0, 10);
        String time = this.dateTime.substring(10);
        return "Date: " + data + " Time: " + time;
    }
}
