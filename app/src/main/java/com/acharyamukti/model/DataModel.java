package com.acharyamukti.model;

public class DataModel {
    private String userid;
    private String error;
    private String mobile;
    private String message;


    public DataModel(String error, String mobile, String message, String userid) {
        this.userid = userid;
        this.error = error;
        this.mobile = mobile;
        this.message = message;
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
