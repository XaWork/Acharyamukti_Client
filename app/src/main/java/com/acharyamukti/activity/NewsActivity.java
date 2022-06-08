package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.MenuItem;
import com.acharyamukti.R;
import com.acharyamukti.adapter.NewsAdapter;
import com.acharyamukti.model.NewsModel;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    RecyclerView recyclerViewNews;
    List<NewsModel> newsModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        recyclerViewNews = findViewById(R.id.recyclerViewNews);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerViewNews.setLayoutManager(layout);
        NewsAdapter newsAdapter = new NewsAdapter(getApplicationContext(), R.layout.custom_horoscope_layout, newsModels);
        recyclerViewNews.setAdapter(newsAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }
}