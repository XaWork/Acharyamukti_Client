package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.acharyamukti.R;
import com.acharyamukti.adapter.UserDetailsAdapter;

public class KundaliniMarriage extends AppCompatActivity {
    Toolbar toolbarKun;
    RecyclerView recyclerviewDetails;
    UserDetailsAdapter userDetailsAdapter;
    LinearLayoutManager linearLayoutManager;

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
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }
}