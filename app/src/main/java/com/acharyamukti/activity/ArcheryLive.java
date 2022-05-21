package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.acharyamukti.R;
import com.acharyamukti.adapter.DataLiveAdapter;

public class ArcheryLive extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acharyamukti_live);
        toolbar = findViewById(R.id.toolbarLive);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Acharyamukti Live");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recyclerViewLive);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DataLiveAdapter dataLiveAdapter = new DataLiveAdapter(getApplicationContext());
        recyclerView.setAdapter(dataLiveAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        this.finish();
        return super.onOptionsItemSelected(item);
    }
}