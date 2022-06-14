package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.acharyamukti.R;
import com.acharyamukti.fragment.BlankFragment;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layout, navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = findViewById(R.id.btnOtp);
        login.setOnClickListener(this);
        Button loginToEmail = findViewById(R.id.loginToEmail);
        loginToEmail.setOnClickListener(this);
        layout = findViewById(R.id.linearLayout);
        navigationBar = findViewById(R.id.navigationBar);
        Toolbar toolbar = findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOtp:
                dialog();
                break;
            case R.id.loginToEmail:
                layout.setVisibility(View.GONE);
                navigationBar.setVisibility(View.INVISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment, new BlankFragment()).commit();
                break;
        }

    }

    public void dialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.verify_otp);
        Button btnVerify = dialog.findViewById(R.id.btnVerify);
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }
}