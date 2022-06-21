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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.model.DataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layout, navigationBar;
    EditText mobileNumber;

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
        mobileNumber = findViewById(R.id.getOtpMobile);
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
                getSupportFragmentManager().beginTransaction().add(R.id.fragment, new com.acharyamukti.fragment.Login()).commit();
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
                Intent intent = new Intent(Login.this, DashBoard.class);
                startActivity(intent);
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    private void getOtp() {
        String mobile = mobileNumber.getText().toString();
        if (mobile.isEmpty()) {
            mobileNumber.requestFocus();
            mobileNumber.setError("Please Enter your Mobile No.");
            return;
        }
        if (mobile.length() == 9) {
            mobileNumber.requestFocus();
            mobileNumber.setError("Mobile number Invalid");
            return;
        }
        Call<DataModel> call = RetrofitClient
                .getInstance()
                .getApi()
                .getOTP(mobile);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                DataModel dataModel = response.body();
                if (response.isSuccessful()) {
                    if (dataModel.getMessage().equals("Check OTP Your Mobile No")) {
                        dialog();
                        Toast.makeText(Login.this, dataModel.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Enter valid mobile number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Toast.makeText(Login.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}