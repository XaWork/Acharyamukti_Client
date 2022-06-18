package com.acharyamukti.model;

public class DataModel {
    private String userid;
    private String error;
    private String message;


    public DataModel(String error, String message, String userid) {
        this.userid = userid;
        this.error = error;
        this.message = message;
    }

    public String getUserid() {
        return userid;
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
