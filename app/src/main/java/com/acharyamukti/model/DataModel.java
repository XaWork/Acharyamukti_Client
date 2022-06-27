package com.acharyamukti.model;

public class DataModel {
    private String userid;
    private String error;
    private String mobile;
    private String message;
    private String heading;
    private String today_horo;


    public DataModel(String error, String mobile, String message, String userid, String heading, String today_horo) {
        this.userid = userid;
        this.error = error;
        this.mobile = mobile;
        this.message = message;
        this.heading=heading;
        this.today_horo=today_horo;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getToday_horo() {
        return today_horo;
    }

    public void setToday_horo(String today_horo) {
        this.today_horo = today_horo;
    }

    public String getUserid() {
        return userid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
