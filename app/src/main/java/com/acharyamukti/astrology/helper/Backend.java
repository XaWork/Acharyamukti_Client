package com.acharyamukti.astrology.helper;

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
        editor.clear();

    }

    public String getHoroscope() {
        return preferences.getString("horoscop_name", "");
    }

    public void saveHoroscope(String horoscop_name) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("horoscop_name", horoscop_name);
        editor.apply();
        editor.clear();

    }

    public void logOut(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public String getName() {
        return preferences.getString("name", "");
    }

    public void saveName(String name) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.apply();
        editor.clear();
    }

    public String getMobile() {
        return preferences.getString("mobile", "");
    }

    public void saveMobile(String mobile) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mobile", mobile);
        editor.apply();
        editor.clear();

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

    public String getWalletBalance() {
        return preferences.getString("wallet", "");
    }

    public void saveWalletBalance(String wallet) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("wallet", wallet);
        editor.apply();
    }

    public void saveAstroMobile(String astro_mobile) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("astro_moble", astro_mobile);
        editor.apply();
    }

    public String getAstroMobile() {
        return preferences.getString("astro_moble", "");
    }

    public void saveReg_id(String reg_id) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("reg_id", reg_id);
        editor.apply();
    }

    public String getReg_id() {
        return preferences.getString("reg_id", "");
    }

    public void saveCallDuration(String callDuration) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("callDurationTime", callDuration);
        editor.apply();
    }

    public String getCallDuration() {
        return preferences.getString("callDurationTime", "");
    }

    public String getStatus() {
        return preferences.getString("status", "");
    }

    public void saveStatus(String status) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("status", status);
        editor.apply();
    }
}
