package com.acharyamukti.astrology.model;

public class DataModel {
    private String userid;
    private String error;
    private String mobile;
    private boolean can_use_promotional_pack;
    private String email;
    private String name;
    private String message;
    private String status;
    private String callDurationTime;
    private String wallet;


    public DataModel(String error, String mobile, String message, boolean can_use_promotional_pack, String userid, String status, String callDurationTime, String wallet, String name, String email) {
        this.userid = userid;
        this.error = error;
        this.mobile = mobile;
        this.message = message;
        this.can_use_promotional_pack = can_use_promotional_pack;
        this.status = status;
        this.callDurationTime = callDurationTime;
        this.wallet = wallet;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean getCanUsPromotionalPack() {
        return can_use_promotional_pack;
    }

    public void setCanUsPromotionalPack(boolean canUsPromotionalPack) {
        this.can_use_promotional_pack = canUsPromotionalPack;
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
