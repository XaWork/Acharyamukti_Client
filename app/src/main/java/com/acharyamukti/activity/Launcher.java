package com.acharyamukti.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.acharyamukti.R;
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
        new Timer().schedule(new TimerTask() {
            public void run() {
                startActivity(new Intent(Launcher.this, Login.class));
            }
        }, 3000);
    }
}
