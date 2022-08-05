package com.acharyamukti.model;

public class AstroProfileModel {
    String image;
    String name;
    String reg_id;
    String experience;
    String callrate;
    String language;
    String asttype;
    String avgrating1;
    String status;

    public AstroProfileModel(String image, String name, String reg_id, String experience, String callrate, String language, String asttype,String avgrating1) {
        this.image = image;
        this.name = name;
        this.reg_id = reg_id;
        this.experience = experience;
        this.callrate = callrate;
        this.language = language;
        this.asttype = asttype;
        this.avgrating1 = avgrating1;
    }


    public AstroProfileModel(String image, String status, String name, String reg_id, String experience, String callrate, String language, String asttype, String avgrating1) {
        this.image = image;
        this.status = status;
        this.name = name;
        this.reg_id = reg_id;
        this.experience = experience;
        this.callrate = callrate;
        this.language = language;
        this.asttype = asttype;
        this.avgrating1 = avgrating1;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg_id() {
        return reg_id;
    }

    public String getExperience() {
        return experience;
    }

    public String getCallrate() {
        return callrate;
    }

    public String getAsttype() {
        return asttype;
    }


    public String getAvgrating1() {
        return avgrating1;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
