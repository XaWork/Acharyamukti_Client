package com.acharyamukti.model;


public class CallDataModel {
    String k_number;
    String agent_number;
    String customer_number;
    String caller_id;
    String status;
    String message;

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

}
