package com.acharyamukti.model;


public class CallDataModel {
    String k_number;
    String agent_number;
    String customer_number;
    String caller_id;
    String max_call_duration;
    String status;
    String message;
    String call_id;

    public CallDataModel(String k_number, String agent_number, String customer_number, String caller_id, String max_call_duration, String status, String message, String call_id) {
        this.k_number = k_number;
        this.agent_number = agent_number;
        this.customer_number = customer_number;
        this.caller_id = caller_id;
        this.max_call_duration = max_call_duration;
        this.status = status;
        this.message = message;
        this.call_id = call_id;
    }

    public CallDataModel(String k_number, String agent_number, String customer_number, String caller_id) {
        this.k_number = k_number;
        this.agent_number = agent_number;
        this.customer_number = customer_number;
        this.caller_id = caller_id;
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
