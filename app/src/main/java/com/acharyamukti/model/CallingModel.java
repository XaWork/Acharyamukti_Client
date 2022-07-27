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


    public String getMinute() {
        return minute;
    }

    public String getCallrat() {
        return callrat;
    }


    public String getCallamount() {
        return callamount;
    }


    public String getCalldate() {
        return calldate;
    }

}
