package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.acharyamukti.R;
import com.acharyamukti.adapter.NewsAdapter;
import com.acharyamukti.adapter.ProfileAdapter;
import com.acharyamukti.model.NewsModel;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends AppCompatActivity {
    RecyclerView recyclerViewNews, recyclerViewHoroscope;
    List<NewsModel> newsModels = new ArrayList<>();
    LinearLayoutManager layout, linearLayoutManager;
    NewsAdapter newsAdapter;
    ProfileAdapter profileAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        recyclerViewNews = findViewById(R.id.recyclerViewNews);
        layout = new LinearLayoutManager(this);
        recyclerViewNews.setLayoutManager(layout);
        newsAdapter = new NewsAdapter(getApplicationContext(), R.layout.custom_horoscope_layout, newsModels);
        recyclerViewNews.setAdapter(newsAdapter);
        recyclerViewHoroscope = findViewById(R.id.recyclerViewHoroscope);
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerViewHoroscope.setLayoutManager(linearLayoutManager);
        profileAdapter = new ProfileAdapter(getApplicationContext(), R.layout.custom_horoscope_icon, newsModels);
        recyclerViewHoroscope.setAdapter(profileAdapter);
        toolbar = findViewById(R.id.toolbarNews);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }
}