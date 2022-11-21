package com.acharyamukti.astrology.model;

public class BlogModel {
    String date;
    String name;
    String blog_id;
    String description;
    String image;

    public BlogModel(String date, String name, String blog_id, String description, String image) {
        this.date = date;
        this.name = name;
        this.blog_id=blog_id;
        this.description = description;
        this.image = image;
    }

    public String getBlog_id() {
        return blog_id;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
