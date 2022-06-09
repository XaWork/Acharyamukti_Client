package com.acharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

import com.acharyamukti.R;

public class KundaliniMarriage extends AppCompatActivity {
    Toolbar toolbarKun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundali_marige);
        toolbarKun = findViewById(R.id.toolbarKun);
        setActionBar(toolbarKun);
        Intent intent = new Intent();
        String title = intent.getStringExtra("title");
        if (title.equals("Love & Relationship")) {
            toolbarKun.setTitle(title);
        }
        toolbarKun.setTitle("");
    }
}