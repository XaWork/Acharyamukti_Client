package com.acharyamukti.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;

public class Backend {
    AssetManager assetManager;
    SharedPreferences preferences;
    public static Backend instance;

    public Backend(Context context) {
        assetManager = context.getAssets();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Backend getInstance(Context context) {
        return instance == null ? instance = new Backend(context) : instance;
    }

    public String getUserId() {
        return preferences.getString("userid", "");
    }

    public void saveUserId(String userId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userid", userId);
        editor.apply();
    }

    public String getHoroscope() {
        return preferences.getString("horoscop_name", "");
    }

    public void saveHoroscope(String horoscop_name) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("horoscop_name", horoscop_name);
        editor.apply();
    }

    public String getName() {
        return preferences.getString("name", "");
    }

    public void saveName(String name) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.apply();
    }

    public String getMobile() {
        return preferences.getString("mobile", "");
    }

    public void saveMobile(String mobile) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mobile", mobile);
        editor.apply();
    }

    public String getEmail() {
        return preferences.getString("email", "");
    }

    public void saveEmail(String email) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.apply();
    }

    public String getLastName() {
        return preferences.getString("lName", "");
    }

    public void saveLastname(String lName) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("lName", lName);
        editor.apply();
    }

    public String getAddress() {
        return preferences.getString("address", "");
    }

    public void saveAddress(String address) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("address", address);
        editor.apply();
    }

    public String getWalletBalance() {
        return preferences.getString("wallet", "");
    }

    public void saveWalletBalance(String wallet) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("wallet", wallet);
        editor.apply();
    }
}
