package com.fatece.manatech.domain.time;

public class Time {

    private Integer id;
    private String nameTime;


    public Time(String nameTime) {
        this.nameTime = nameTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameTime() {
        return nameTime;
    }

    public void setNameTime(String nameTime) {
        this.nameTime = nameTime;
    }


    @Override
    public String toString() {
        return this.nameTime;
    }
}
