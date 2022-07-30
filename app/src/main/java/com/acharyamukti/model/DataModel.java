package com.acharyamukti.model;

public class DataModel {
    private String userid;
    private String error;
    private String mobile;
    private String message;
    private String status;
    private String callDurationTime;
    private String wallet;


    public DataModel(String error, String mobile, String message, String userid, String status, String callDurationTime, String wallet) {
        this.userid = userid;
        this.error = error;
        this.mobile = mobile;
        this.message = message;
        this.status = status;
        this.callDurationTime = callDurationTime;
        this.wallet = wallet;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCallDurationTime() {
        return callDurationTime;
    }

    public void setCallDurationTime(String callDurationTime) {
        this.callDurationTime = callDurationTime;
    }

    public String getWallet() {
        return wallet;
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

}
