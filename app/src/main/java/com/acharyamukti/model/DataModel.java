package com.acharyamukti.model;

public class DataModel {
    private String userid;
    private String error;
    private String mobile;
    private String message;
    private String heading;
    private String today_horo;
    private String wallet;


    public DataModel(String error, String mobile, String message, String userid, String heading, String today_horo, String wallet) {
        this.userid = userid;
        this.error = error;
        this.mobile = mobile;
        this.message = message;
        this.heading = heading;
        this.today_horo = today_horo;
        this.wallet = wallet;
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
