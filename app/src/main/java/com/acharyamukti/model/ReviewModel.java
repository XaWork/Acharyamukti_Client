package com.acharyamukti.model;

public class ReviewModel {
    String rating;
    String reg_id;
    String commente;
    String date;
    String usernme;

    public ReviewModel(String rating, String reg_id, String commente, String date, String usernme) {
        this.rating = rating;
        this.reg_id = reg_id;
        this.commente = commente;
        this.date = date;
        this.usernme = usernme;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getCommente() {
        return commente;
    }

    public void setCommente(String commente) {
        this.commente = commente;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsernme() {
        return usernme;
    }

    public void setUsernme(String usernme) {
        this.usernme = usernme;
    }
}
