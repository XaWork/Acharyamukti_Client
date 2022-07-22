package com.acharyamukti.model;

public class CallingModel {
    String name;
    String duration;
    String minute;
    String callrat;
    String callamount;
    String calldate;

    public CallingModel(String name, String duration, String minute, String callrat, String callamount, String calldate) {
        this.name = name;
        this.duration = duration;
        this.minute = minute;
        this.callrat = callrat;
        this.callamount = callamount;
        this.calldate = calldate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getCallrat() {
        return callrat;
    }

    public void setCallrat(String callrat) {
        this.callrat = callrat;
    }

    public String getCallamount() {
        return callamount;
    }

    public void setCallamount(String callamount) {
        this.callamount = callamount;
    }

    public String getCalldate() {
        return calldate;
    }

    public void setCalldate(String calldate) {
        this.calldate = calldate;
    }
}
