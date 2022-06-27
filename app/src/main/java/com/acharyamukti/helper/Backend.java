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
}
