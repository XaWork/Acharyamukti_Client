package com.acharyamukti.model;

public class WalletModel {
    private String error;
    private String wallet;

    public WalletModel(String error, String wallet){
        this.error = error;
        this.wallet = wallet;
    }

    public String getWallet(){
        return wallet;
    }

    public void setWallet(String wallet){
        this.wallet = wallet;
    }

    public String getError(){
        return error;
    }

    public void setError(String error){
        this.wallet = error;
    }

}
