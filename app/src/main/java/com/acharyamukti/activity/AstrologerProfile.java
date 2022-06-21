package com.acharyamukti.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.acharyamukti.R;
import com.acharyamukti.adapter.ReviewAdapter;
import com.acharyamukti.databinding.ActivityAstrologerProfileBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class AstrologerProfile extends AppCompatActivity {
    RecyclerView recyclerView;
    ReviewAdapter reviewAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.acharyamukti.databinding.ActivityAstrologerProfileBinding binding = ActivityAstrologerProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.ratingRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        reviewAdapter = new ReviewAdapter(getApplicationContext());
        recyclerView.setAdapter(reviewAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }
}