package com.acharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import com.acharyamukti.R;

public class TermAndCondition extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_and_condition);
//        WebView view = new WebView(this);
//        view.getSettings().setJavaScriptEnabled(true);
//        view.loadUrl("file:///android_asset/about.html");
//        setContentView(view);
    }
}