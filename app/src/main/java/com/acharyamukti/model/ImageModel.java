package com.acharyamukti.model;

public class ImageModel {
    String horoscop_id;
    String horoscop_name;
    String horoscop_icon;

    public ImageModel(String horoscop_id, String horoscop_name, String horoscop_icon) {
        this.horoscop_id = horoscop_id;
        this.horoscop_name = horoscop_name;
        this.horoscop_icon = horoscop_icon;
    }

    public String getHoroscop_id() {
        return horoscop_id;
    }

    public void setHoroscop_id(String horoscop_id) {
        this.horoscop_id = horoscop_id;
    }

    public String getHoroscop_name() {
        return horoscop_name;
    }

    public void setHoroscop_name(String horoscop_name) {
        this.horoscop_name = horoscop_name;
    }

    public String getHoroscop_icon() {
        return horoscop_icon;
    }

    public void setHoroscop_icon(String horoscop_icon) {
        this.horoscop_icon = horoscop_icon;
    }
}
