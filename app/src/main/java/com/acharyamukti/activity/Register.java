package com.acharyamukti.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.acharyamukti.R;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.model.DataModel;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Register extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister;
    EditText first_name, last_name, mobileNumber, password, emailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        first_name = findViewById(R.id.f_name);
        last_name = findViewById(R.id.l_name);
        mobileNumber = findViewById(R.id.reg_mobile);
        password = findViewById(R.id.reg_pass);
        emailId = findViewById(R.id.reg_email);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        registerUser();
    }

    private void registerUser() {
        String fName = first_name.getText().toString();
        String lName = last_name.getText().toString();
        String email = emailId.getText().toString();
        String pass = password.getText().toString();
        String mobile = mobileNumber.getText().toString();

        if (fName.isEmpty()) {
            first_name.requestFocus();
            first_name.setError("Please Enter First Name");
            return;
        }
        if (lName.isEmpty()) {
            last_name.requestFocus();
            last_name.setError("Please Enter Last Name");
            return;
        }
        if (email.isEmpty()) {
            emailId.requestFocus();
            emailId.setError("Please Enter Email");
            return;
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailId.requestFocus();
            emailId.setError("Please Enter correct Email");
            return;
        }
        if (pass.isEmpty()) {
            password.requestFocus();
            password.setError("Please Enter Password");
            return;
        }
        if (pass.length() < 8) {
            password.requestFocus();
            password.setError("Please Enter Password");
            return;

        }
        if (mobile.isEmpty()) {
            mobileNumber.requestFocus();
            mobileNumber.setError("Please Enter Password");
            return;
        }
        if (mobile.length() == 9) {
            mobileNumber.requestFocus();
            mobileNumber.setError("Please Enter Password");
            return;
        }
        Call<DataModel> call = RetrofitClient
                .getInstance()
                .getApi()
                .register(fName, lName, email, pass, mobile);

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                DataModel dataModel = response.body();
                try {

                    if (response.isSuccessful()) {
                        Intent intent = new Intent(Register.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "successFul", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Register.this, dataModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}