package com.acharyamukti.astrology.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();
    private static final String PREF_NAME = "test";
    private static final String KET_IS_LOG_IN = "IS_LOGGED_IN";
    int PRIVATE_MODE = 0;
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
        editor.commit();
        editor.clear();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KET_IS_LOG_IN, isLoggedIn);
        //editor.clear();
        editor.commit();
//        Toast.makeText(context, "User login session modified", Toast.LENGTH_SHORT).show();
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(KET_IS_LOG_IN, false);
    }
}
