package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.acharyamukti.R;
import com.acharyamukti.adapter.UserDetailsAdapter;

public class KundaliniMarriage extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbarKun;
    RecyclerView recyclerviewDetails;
    UserDetailsAdapter userDetailsAdapter;
    LinearLayoutManager linearLayoutManager;
    CheckBox search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundali_marige);
        recyclerviewDetails = findViewById(R.id.recyclerviewDetails);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewDetails.setLayoutManager(linearLayoutManager);
        userDetailsAdapter = new UserDetailsAdapter(getApplicationContext());
        recyclerviewDetails.setAdapter(userDetailsAdapter);
        toolbarKun = findViewById(R.id.toolbarKun);
        setSupportActionBar(toolbarKun);
        toolbarKun.setTitle("Call with Astrologer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Intent intent = new Intent();
//        String title = intent.getStringExtra("title");
//        if (title.equals("Love & Relationship")) {
//            toolbarKun.setTitle(title);
//        }
//        toolbarKun.setTitle("");
        search = findViewById(R.id.checkbox3);
        search.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent search = new Intent(getApplicationContext(), Search.class);
        startActivity(search);
    }
}