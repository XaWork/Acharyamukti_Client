package com.acharyamukti.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
        new Timer().schedule(new TimerTask() {
            public void run() {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                if (userId==null) {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                    startActivity(intent);
                }
            }
        }, 3000);
    }
}
