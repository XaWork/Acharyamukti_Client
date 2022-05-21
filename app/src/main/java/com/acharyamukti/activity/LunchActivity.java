package com.acharyamukti.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.acharyamukti.R;

import java.util.Timer;
import java.util.TimerTask;

public class LunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace);
        new Timer().schedule(new TimerTask() {
            public void run() {
                startActivity(new Intent(LunchActivity.this, LoginActivity.class));
            }
        }, 3000);
    }
}
