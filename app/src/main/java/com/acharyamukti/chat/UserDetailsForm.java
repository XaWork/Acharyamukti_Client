package com.acharyamukti.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.activity.DashBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDetailsForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Spinner spinner;
    EditText firstName, lastName, mobile, dob, birthPlace, birthTime, typeOfConcern;
    RadioButton male, female, unmarried, married;
    String fName, lName, mobileN, dateOfBirth, birthP, birthTim, typeConcern;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_form);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getTextViewByID();
        getSpinnerData();
    }

    private void getSpinnerData() {
        spinner = findViewById(R.id.spinner);
        List<String> dataList = new ArrayList<>();
        dataList.add("Self Employee");
        dataList.add("Student");
        dataList.add("Business");
        dataList.add("Private sector");
        dataList.add("Government sector");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, dataList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void getTextViewByID() {
        firstName = findViewById(R.id.etChatFName);
        lastName = findViewById(R.id.etChatLName);
        mobile = findViewById(R.id.etChatMobile);
        dob = findViewById(R.id.date_of_birth);
        birthPlace = findViewById(R.id.birth_place);
        birthTime = findViewById(R.id.timeOfBirth);
        typeOfConcern = findViewById(R.id.topicOfConcern);
        married = findViewById(R.id.married);
        unmarried = findViewById(R.id.unMarried);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
        fName = firstName.getText().toString();
        lName = lastName.getText().toString();
        mobileN = mobile.getText().toString();
        dateOfBirth = dob.getText().toString();
        birthTim = birthTime.getText().toString();
        birthP = birthPlace.getText().toString();
        typeConcern = typeOfConcern.getText().toString();
        validation();
    }

    private void validation() {
        if (fName.length() > 0) {
            firstName.requestFocus();
            firstName.setError("Enter the first name");
            return;
        }
        if (mobileN.length() > 0) {
            mobile.requestFocus();
            mobile.setError("Enter your mobile Number");
            return;
        }
        if (dateOfBirth.length() > 0) {
            dob.requestFocus();
            dob.setError("Enter your Date of Birth");
            return;
        }
        if (birthP.length() > 0) {
            birthPlace.requestFocus();
            birthPlace.setError("Enter your birth place");
            return;

        }
        if (birthTim.length() > 0) {
            birthTime.requestFocus();
            birthTime.setError("Enter your birthday time");
            return;
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if ((male.isChecked() || female.isChecked()) || (unmarried.isChecked() || married.isChecked())) {
            Intent intent = new Intent(this, ChatRequest.class);
            startActivity(intent);
        } else {
            validation();
            Toast.makeText(this, "Enter the proper details", Toast.LENGTH_SHORT).show();
        }
    }
}