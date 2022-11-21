package com.acharyamukti.astrology.model;

public class UserProfileModel {
    String fName;
    String lName;
    String email;
    String mobile;
    String password;
    String error;
    String msg;

    public UserProfileModel(String fName, String lName, String email, String mobile, String password, String error, String msg) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.error = error;
        this.msg = msg;

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }
}
