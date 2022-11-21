package com.acharyamukti.astrology.model;

public class CallingModel {
    String name;
    String duration;
    String minute;
    String callrat;
    String callamount;
    String calldate;
    String call_pack;
    String amount_paid;
    String amount_credited;
    String orderID;
    String order_date;

    public CallingModel(String name, String duration, String minute, String callrat, String callamount, String calldate) {
        this.name = name;
        this.duration = duration;
        this.minute = minute;
        this.callrat = callrat;
        this.callamount = callamount;
        this.calldate = calldate;
    }

    public CallingModel(String call_pack, String amount_paid, String amount_credited, String orderID, String order_date) {
        this.call_pack = call_pack;
        this.amount_paid = amount_paid;
        this.amount_credited = amount_credited;
        this.orderID = orderID;
        this.order_date = order_date;
    }

    public String getCall_pack() {
        return call_pack;
    }

    public void setCall_pack(String call_pack) {
        this.call_pack = call_pack;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(String amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getAmount_credited() {
        return amount_credited;
    }

    public void setAmount_credited(String amount_credited) {
        this.amount_credited = amount_credited;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }


    public String getMinute() {
        return minute;
    }

    public String getCallrat() {
        return callrat;
    }


    public String getCallamount() {
        return callamount;
    }


    public String getCalldate() {
        return calldate;
    }

}
