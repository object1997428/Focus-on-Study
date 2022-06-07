package com.pos.posproject.domain;

public class Time {
    private Integer hour;
    private Integer minute;
    private Integer second;

    public Time(Integer seconds) {
        this.hour=seconds/360;
        this.minute=seconds/60;
        this.second=seconds%60;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }
}
