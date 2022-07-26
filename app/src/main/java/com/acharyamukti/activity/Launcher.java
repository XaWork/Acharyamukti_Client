package com.acharyamukti.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.acharyamukti.R;
import com.acharyamukti.helper.Backend;
import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends AppCompatActivity {
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace);
        imageView = findViewById(R.id.lunchLogo);
        Glide.with(this).load(R.drawable.luncher_screen_new).into(imageView);
        String userId = Backend.getInstance(this).getUserId();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences=getSharedPreferences(Login.PRES_NAME,MODE_PRIVATE);
                boolean hasLoggedIn=sharedPreferences.getBoolean("hasLoggedIn",false);
                Intent intent;
                if (hasLoggedIn){
                    intent = new Intent(getApplicationContext(), DashBoard.class);
                }else {
                    intent = new Intent(getApplicationContext(), Login.class);
                }
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
