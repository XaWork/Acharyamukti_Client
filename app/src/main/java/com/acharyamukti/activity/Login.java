package com.acharyamukti.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.acharyamukti.R;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.fragment.EmailLogin;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.helper.SessionManager;
import com.acharyamukti.model.DataModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layout;
    EditText mobileNumber;
    EditText etOTP;
    String Otp;
    SharedPreferences shp;
    public static String PRES_NAME = "profile";
    ProgressBar progressBar;
    ImageView close;
    CheckBox check_box_condition;
    TextView termAndCondition;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = findViewById(R.id.btnOtp);
        login.setOnClickListener(this);
        check_box_condition = findViewById(R.id.check_box_condition);
        layout = findViewById(R.id.linearLayout);
        Toolbar toolbar = findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        mobileNumber = findViewById(R.id.getOtpMobile);
        close = findViewById(R.id.icon_close);
        close.setOnClickListener(this);
        termAndCondition = findViewById(R.id.termAndCondition);
        termAndCondition.setOnClickListener(this);
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
                if (check_box_condition.isChecked()) {
                    SessionManager sessionManager = new SessionManager(getApplicationContext());
                    sessionManager.setLogin(true);
                    getOtp();
                } else {
                    Toast.makeText(getApplicationContext(), "Please accepts terms and condition", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.termAndCondition:
//                layout.setVisibility(View.GONE);
////                getSupportFragmentManager().beginTransaction().add(R.id.fragment, new EmailLogin()).commit();
                String termConditionUrl = "https://theacharyamukti.com/terms-and-conditions.php";
                Intent terms = new Intent(Intent.ACTION_VIEW);
                terms.putExtra("", "1");
                terms.setData(Uri.parse(termConditionUrl));
                startActivity(terms);
                break;
            case R.id.icon_close:
                Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(intent);
                break;
        }
    }

    public void dialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.verify_otp);
        Button btnVerify = dialog.findViewById(R.id.btnVerify);
        etOTP = dialog.findViewById(R.id.etOTP);
        Otp = etOTP.getText().toString();
        btnVerify.setOnClickListener(view -> verifyOTP());
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    private void getOtp() {
        String mobile = mobileNumber.getText().toString();
        Backend.getInstance(this).saveMobile(mobile);
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
            public void onResponse(@NonNull Call<DataModel> call, @NonNull Response<DataModel> response) {
                DataModel dataModel = response.body();
                if (response.isSuccessful()) {
                  dialog();
                } else {
                    Toast.makeText(Login.this, "Enter valid mobile number", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<DataModel> call, @NonNull Throwable t) {
                Toast.makeText(Login.this, t.toString(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void verifyOTP() {
        String mobile = Backend.getInstance(this).getMobile();
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
            public void onResponse(@NonNull Call<DataModel> call, @NonNull Response<DataModel> response) {
                DataModel data = response.body();
                if (response.isSuccessful()) {
                    assert data != null;
                    if (data.getError().equals("false")) {
                        Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                        intent.putExtra("clam_now", "clamNow");
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
            public void onFailure(@NonNull Call<DataModel> call, @NonNull Throwable t) {
                Toast.makeText(Login.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        try {
            if (dialog != null) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}