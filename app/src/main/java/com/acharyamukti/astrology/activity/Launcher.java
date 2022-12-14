package com.acharyamukti.astrology.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.acharyamukti.R;
import com.acharyamukti.astrology.helper.Backend;


public class Launcher extends AppCompatActivity {
    ImageView imageView, profileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace);
        imageView = findViewById(R.id.lunchLogo);
        profileImage = findViewById(R.id.profileImage);
        final ValueAnimator anim = ValueAnimator.ofFloat(1f, 1.5f);
        anim.setDuration(1000);
        anim.addUpdateListener(animation -> {
            profileImage.setScaleX((Float) animation.getAnimatedValue());
            profileImage.setScaleY((Float) animation.getAnimatedValue());
        });
        anim.setRepeatCount(1);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.start();
        String userid = Backend.getInstance(this).getUserId();

        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences(Login.PRES_NAME, MODE_PRIVATE);
            boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false);
            Intent intent;
            if (hasLoggedIn || userid.length() != 0) {
                intent = new Intent(getApplicationContext(), DashBoard.class);
            } else {
                intent = new Intent(getApplicationContext(), Login.class);
            }
            startActivity(intent);
            finish();

        }, 3000);
    }
}
