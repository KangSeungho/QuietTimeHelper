package com.example.kangseungho.quiettimehelper.Alarm;

public class AlarmItem {

    private int id;
    private String day;
    private boolean ampm;
    private int hour;
    private int minute;
    private boolean onoff;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }

    public boolean isAmpm() {
        return ampm;
    }
    public void setAmpm(boolean ampm) {
        this.ampm = ampm;
    }

    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        if(hour >= 13)
            hour = 12;
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }
    public void setMinute(int minute) {
        if(minute >= 60)
            minute = 59;
        this.minute = minute;
    }

    public boolean isOnoff() {
        return onoff;
    }
    public void setOnoff(boolean onoff) {
        this.onoff = onoff;
    }

}
