package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.adapter.NewsAdapter;
import com.acharyamukti.adapter.ProfileAdapter;
import com.acharyamukti.model.NewsModel;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerViewNews, recyclerViewHoroscope;
    List<NewsModel> newsModels = new ArrayList<>();
    LinearLayoutManager layout, linearLayoutManager;
    NewsAdapter newsAdapter;
    ProfileAdapter profileAdapter;
    Toolbar toolbar;
    RelativeLayout share;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        share = findViewById(R.id.share);
        share.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        shareAppLink();
    }

    private void shareAppLink() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.astroacharyamukti.app");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
    }
}