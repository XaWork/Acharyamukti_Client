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
import android.widget.TextView;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.adapter.NewsAdapter;
import com.acharyamukti.adapter.LiveAdapter;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.DataModel;
import com.acharyamukti.model.ImageModel;
import com.acharyamukti.model.NewsModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

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
    List<ImageModel> imageModels = new ArrayList<>();

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
        recyclerViewHoroscope = findViewById(R.id.recyclerViewHoroscope);
        share = findViewById(R.id.share);
        share.setOnClickListener(this);
        getData();
        getBannerData();
        //   getHoroscope();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
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

    private void getHoroscope() {
        Call<List<ImageModel>> call = RetrofitClient.getInstance().getApi().getHoroscope();
        call.enqueue(new Callback<List<ImageModel>>() {
            @Override
            public void onResponse(Call<List<ImageModel>> call, Response<List<ImageModel>> response) {
                if (response.isSuccessful()) {
                    imageModels = response.body();
                    try {
                        JSONObject jsonObject = new JSONObject();
                        JSONArray jsonArray = jsonObject.getJSONArray("body");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            ImageModel imageModel = new ImageModel(
                                    jo.getString("horoscop_id"),
                                    jo.getString("horoscop_name"),
                                    jo.getString("horoscop_icon"));
                            imageModels.add(imageModel);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //   for (int i = 0; i < imageModels.size(); i++) {
                    linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
                    recyclerViewHoroscope.setLayoutManager(linearLayoutManager);
                    liveAdapter = new LiveAdapter(getApplicationContext(), R.layout.custom_horoscope_icon, imageModels);
                    recyclerViewHoroscope.setAdapter(liveAdapter);
                    //     }
                } else {
                    Toast.makeText(Horoscope.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ImageModel>> call, Throwable t) {
                Toast.makeText(Horoscope.this, "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {
        String url = "https://theacharyamukti.com/clientapi/daily-horoscope.php";
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewHoroscope.setLayoutManager(linearLayoutManager);
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest request1 = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("body");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jo = jsonArray.getJSONObject(i);
                    ImageModel imageModel = new ImageModel(
                            jo.getString("horoscop_id"),
                            jo.getString("horoscop_name"),
                            jo.getString("horoscop_icon"));
                    imageModels.add(imageModel);
                    Backend.getInstance(getApplicationContext()).saveHoroscope(jo.getString("horoscop_name"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            liveAdapter = new LiveAdapter(getApplicationContext(), R.layout.custom_horoscope_icon, imageModels);
            liveAdapter.notifyDataSetChanged();
            recyclerViewHoroscope.setAdapter(liveAdapter);
        }, error -> {
        });
        request.add(request1);
    }

    private void getBannerData() {
        TextView bannerTitle = findViewById(R.id.bannerTitle);
        TextView desc = findViewById(R.id.bannerDescription);
        String title="horoscop_name";
       // String title = Backend.getInstance(this).getHoroscope();
        Call<DataModel> call = RetrofitClient.getInstance().getApi().getBannerData(title);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                DataModel dataModel = response.body();
                if (response.isSuccessful()) {
                    if (dataModel.getError().equals("false")) {
                        bannerTitle.setText(dataModel.getHeading());
                        desc.setText(dataModel.getToday_horo());
                    } else {
                        Toast.makeText(Horoscope.this, dataModel.getError(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Toast.makeText(Horoscope.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}