package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.acharyamukti.R;
import com.acharyamukti.helper.Backend;

public class ProfileUpdate extends AppCompatActivity {
    EditText name, l_name, mobile, email;
    String fName, lName, emailId, mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
//        fName = name.getText().toString();
//        lName = l_name.getText().toString();
//        emailId = email.getText().toString();
//        mobileNumber = mobile.getText().toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }
}