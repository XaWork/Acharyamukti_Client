package com.acharyamukti.model;

public class CallDataModel {
    String status;
    String message;
    String call_id;

    public CallDataModel(String status, String message, String call_id) {
        this.status = status;
        this.message = message;
        this.call_id = call_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCall_id() {
        return call_id;
    }

    public void setCall_id(String call_id) {
        this.call_id = call_id;
    }
}
