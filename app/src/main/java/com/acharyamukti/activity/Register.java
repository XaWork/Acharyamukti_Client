package com.acharyamukti.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.acharyamukti.R;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.helper.DBHelper;
import com.acharyamukti.model.DataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Register extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister;
    EditText first_name, last_name, mobileNumber, password, emailId;
    ProgressBar progressBar;
    DBHelper dbHelper;
    String fName, lName, email, mobile, pass;

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
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        dbHelper = new DBHelper(this);


    }

    @Override
    public void onClick(View view) {
        fName = first_name.getText().toString();
        lName = last_name.getText().toString();
        email = emailId.getText().toString();
        pass = password.getText().toString();
        mobile = mobileNumber.getText().toString();
        if (fName.isEmpty() || lName.isEmpty() || mobile.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Table not create", Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.insertData(fName, lName, mobile, email, pass);
            Toast.makeText(this, "Table are create", Toast.LENGTH_SHORT).show();
            registerUser();

        }
    }

    private void registerUser() {
        fName = first_name.getText().toString();
        lName = last_name.getText().toString();
        email = emailId.getText().toString();
        pass = password.getText().toString();
        mobile = mobileNumber.getText().toString();
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
            public void onResponse(@NonNull Call<DataModel> call, @NonNull Response<DataModel> response) {
                DataModel dataModel = response.body();
                try {
                    if (response.isSuccessful()) {
                        Backend.getInstance(getApplicationContext()).saveName(fName);
                        Backend.getInstance(getApplicationContext()).saveEmail(email);
                        Backend.getInstance(getApplicationContext()).saveLastname(lName);
                        Intent intent = new Intent(Register.this, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        assert dataModel != null;
                        Toast.makeText(getApplicationContext(), dataModel.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        progressBar.setVisibility(View.VISIBLE);
                        assert dataModel != null;
                        Toast.makeText(Register.this, dataModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataModel> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}