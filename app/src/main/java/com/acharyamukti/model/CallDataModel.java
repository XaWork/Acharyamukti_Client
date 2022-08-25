package com.acharyamukti.model;


import java.util.HashMap;

public class CallDataModel {
    String k_number;
    String agent_number;
    String customer_number;
    String caller_id;
    String status;
    String message;
    HashMap<String, Integer> additional_params = new HashMap<>();

    public CallDataModel(String k_number, String agent_number, String customer_number, String caller_id,int duration) {
        this.k_number = k_number;
        this.agent_number = agent_number;
        this.customer_number = customer_number;
        this.caller_id = caller_id;
        this.additional_params.put("max_call_duration",duration);
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

    public String getK_number() {
        return k_number;
    }

    public void setK_number(String k_number) {
        this.k_number = k_number;
    }

    public String getAgent_number() {
        return agent_number;
    }

    public void setAgent_number(String agent_number) {
        this.agent_number = agent_number;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public String getCaller_id() {
        return caller_id;
    }

    public void setCaller_id(String caller_id) {
        this.caller_id = caller_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, Integer> getAdditional_params() {
        return additional_params;
    }

    public void setAdditional_params(HashMap<String, Integer> additional_params) {
        this.additional_params = additional_params;
    }
}
