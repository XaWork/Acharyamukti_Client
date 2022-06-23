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
import android.widget.RelativeLayout;
import com.acharyamukti.R;
import com.acharyamukti.adapter.NewsAdapter;
import com.acharyamukti.adapter.LiveAdapter;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.model.ImageModel;
import com.acharyamukti.model.NewsModel;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Horoscope extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerViewNews, recyclerViewHoroscope;
    List<NewsModel> newsModels = new ArrayList<>();
    LinearLayoutManager layout, linearLayoutManager;
    NewsAdapter newsAdapter;
    LiveAdapter liveAdapter;
    Toolbar toolbar;
    RelativeLayout share;
    List<ImageModel> imageModels ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        recyclerViewNews = findViewById(R.id.recyclerViewNews);
        layout = new LinearLayoutManager(this);
        recyclerViewNews.setLayoutManager(layout);
        newsAdapter = new NewsAdapter(getApplicationContext(), R.layout.custom_horoscope_layout, newsModels);
        recyclerViewNews.setAdapter(newsAdapter);
        toolbar = findViewById(R.id.toolbarNews);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        share = findViewById(R.id.share);
        share.setOnClickListener(this);
        getHoroscope();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setDataInRecyclerView() {
        recyclerViewHoroscope = findViewById(R.id.recyclerViewHoroscope);
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerViewHoroscope.setLayoutManager(linearLayoutManager);
        liveAdapter = new LiveAdapter(getApplicationContext(), R.layout.custom_horoscope_icon, imageModels);
        recyclerViewHoroscope.setAdapter(liveAdapter);
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

    private void getHoroscope() {
        Call<ImageModel> imageModelCall = RetrofitClient.getInstance().getApi().getHoroscope();
        imageModelCall.enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                ImageModel imageModel = response.body();
                if (response.isSuccessful()) {
                    setDataInRecyclerView();
                }
            }
            @Override
            public void onFailure(Call<ImageModel> call, Throwable t) {

            }
        });
    }
}