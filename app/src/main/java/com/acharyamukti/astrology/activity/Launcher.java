package com.acharyamukti.astrology.activity;

import android.animation.ValueAnimator;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.acharyamukti.BuildConfig;
import com.acharyamukti.R;
import com.acharyamukti.astrology.api.RetrofitClient;
import com.acharyamukti.astrology.helper.Backend;
import com.acharyamukti.astrology.model.AppVersionModel;
import com.acharyamukti.astrology.notification.MyFirebaseMessagingService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        /*
        First we have to check the version of our app,
        IF play store version and build version is same then user can move forward to next screen,
        other wise send user to play store to update the app
        */

        getAppVersion();
        //firebaseToken();
    }

    private void firebaseToken() {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {
            if (!TextUtils.isEmpty(token)) {
                Log.e("launcher", "retrieve token successful : " + token);
                Backend.getInstance(this).saveToken(token);
            } else {
                Log.e("launcher", "token should not be null...");
            }
        }).addOnFailureListener(e -> {
            e.printStackTrace();
            Log.e("launcher", "fail to get");
        }).addOnCanceledListener(() -> {
            Log.e("launcher", "cancel");
            //handle cancel
        }).addOnCompleteListener(task -> {
                    Log.e("launcher", "This is the token : " + task.getResult());
                    Backend.getInstance(this).saveToken(task.getResult());
                }
        );
    }

    private void getAppVersion() {
        Call<AppVersionModel> call = RetrofitClient.getInstance().getApi().getAppVersion();

        call.enqueue(new Callback<AppVersionModel>() {
            @Override
            public void onResponse(@NonNull Call<AppVersionModel> call, @NonNull Response<AppVersionModel> response) {
                AppVersionModel appVersionModel = response.body();

                if (response.isSuccessful()) {
                    assert appVersionModel != null;
                    checkAppVersion(appVersionModel.getUser_app_version());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AppVersionModel> call, @NonNull Throwable t) {
                Toast.makeText(Launcher.this, t.toString(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void checkAppVersion(String user_app_version) {
        String buildVersion = BuildConfig.VERSION_NAME;

        Log.e("Launcher", "checkAppVersion: User app version " + user_app_version + "\nBuild Version : " + buildVersion);

        if (Objects.equals(user_app_version, buildVersion)) {
            moveToNextScreen();
        } else {
            moveToPlayStore();
        }
    }

    private void moveToPlayStore() {
        Toast.makeText(Launcher.this, "Update your app.", Toast.LENGTH_SHORT).show();

        String appPackageName = getPackageName();
        Log.e("Launcher", "Package Name : " + appPackageName);

        try {
            startActivity(
                    new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + appPackageName)
                    )
            );
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            startActivity(
                    new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)
                    )
            );
        }
    }

    private void moveToNextScreen() {
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
