package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.UserProfileModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileUpdate extends AppCompatActivity implements View.OnClickListener {
    EditText name, l_name, mobile, email;
    String fName, lName, emailId, mobileNumber;
    Button updateDetails;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        name = findViewById(R.id.fName);
        l_name = findViewById(R.id.lName);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        fName = Backend.getInstance(this).getName();
        name.setText(fName);
        lName = Backend.getInstance(this).getLastName();
        l_name.setText(lName);
        emailId = Backend.getInstance(this).getEmail();
        email.setText(emailId);
        mobileNumber = Backend.getInstance(this).getMobile();
        mobile.setText(mobileNumber);
        updateDetails = findViewById(R.id.updateDetails);
        updateDetails.setOnClickListener(this);
        userId = Backend.getInstance(this).getUserId();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void updateProfile() {
        fName = name.getText().toString().trim();
        lName = l_name.getText().toString().trim();
        emailId = email.getText().toString().trim();
        mobileNumber = mobile.getText().toString().trim();
        Call<UserProfileModel> call = RetrofitClient.getInstance().getApi().updateProfile(
                userId, fName, lName, emailId, mobileNumber);
        call.enqueue(new Callback<UserProfileModel>() {
            @Override
            public void onResponse(Call<UserProfileModel> call, Response<UserProfileModel> response) {
                UserProfileModel userModel = response.body();
                try {
                    if (userModel.getMsg().equals("Profile Updated Successfully..")) {
                        Backend.getInstance(getApplicationContext()).saveName(fName);
                        Backend.getInstance(getApplicationContext()).saveLastname(lName);
                        Backend.getInstance(getApplicationContext()).saveEmail(emailId);
                        Backend.getInstance(getApplicationContext()).saveMobile(mobileNumber);
                        Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                        startActivity(intent);
                        Toast.makeText(ProfileUpdate.this, "Profile Updated Successfully..", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProfileUpdate.this, "Profile not Updated", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ProfileUpdate.this, userModel.getError(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserProfileModel> call, @NonNull Throwable t) {
                Toast.makeText(ProfileUpdate.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (userId != null) {
            updateProfile();
        } else {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }

    }
}