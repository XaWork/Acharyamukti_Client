package com.acharyamukti.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.acharyamukti.R;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.fragment.EmailLogin;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.DataModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layout, navigationBar;
    EditText mobileNumber;
    EditText etOTP;
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;

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
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        mobileNumber = findViewById(R.id.getOtpMobile);
        SharedPreferences sharedPreferences = getSharedPreferences("MyLogin.txt", Context.MODE_PRIVATE);
        boolean loginCheck = sharedPreferences.getBoolean("FirstLogin", false);
        if (loginCheck) {
            Intent intent = new Intent(getApplicationContext(), DashBoard.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
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
                getOtp();
                SharedPreferences sharedPreferences = getSharedPreferences("MyLogin.txt", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("FirstLogin", true);
                editor.commit();
                break;
            case R.id.loginToEmail:
                layout.setVisibility(View.GONE);
                navigationBar.setVisibility(View.INVISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment, new EmailLogin()).commit();
                break;
        }
    }

    public void dialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.verify_otp);
        Button btnVerify = dialog.findViewById(R.id.btnVerify);
        etOTP = dialog.findViewById(R.id.etOTP);
        btnVerify.setOnClickListener(view -> verifyOTP());
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    private void getOtp() {
        String mobile = mobileNumber.getText().toString();
        Backend.getInstance(this).saveUserId(mobile);
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
                        if (dataModel.getUserid() == null) {
                            shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
                        }
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

    private void verifyOTP() {
        String mobile = Backend.getInstance(this).getUserId();
        String otp = etOTP.getText().toString();
        if (otp.isEmpty()) {
            etOTP.requestFocus();
            etOTP.setError("Please Entre given OTP");
            return;
        }
        if (otp.length() == 5) {
            etOTP.requestFocus();
            etOTP.setError("Incorrect OTP");
            return;
        }
        Call<DataModel> call = RetrofitClient.getInstance().getApi().verifyOTP(otp, mobile);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                DataModel data = response.body();
                if (response.isSuccessful()) {
                    if (data.getError().equals("false")) {
                        Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                        startActivity(intent);
                        String userid = data.getUserid();
                        Backend.getInstance(getApplicationContext()).saveUserId(userid);
                    } else {
                        Toast.makeText(Login.this, "OTP is not correct", Toast.LENGTH_SHORT).show();
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