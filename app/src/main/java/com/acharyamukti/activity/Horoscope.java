package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.adapter.LiveAdapter;
import com.acharyamukti.adapter.HoroscopeAdapter;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.AstroProfileModel;
import com.acharyamukti.model.HoroscopeModel;
import com.acharyamukti.model.ImageModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Horoscope extends AppCompatActivity implements View.OnClickListener {
    RecyclerView liveRecyclerView, recyclerViewHoroscope;
    LinearLayoutManager linearLayoutManager;
    HoroscopeAdapter horoscopeAdapter;
    LiveAdapter liveAdapter;
    Toolbar toolbar;
    RelativeLayout share;
    List<ImageModel> imageModels = new ArrayList<>();
    TextView love, career, money, health, travels, bannerTitle, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        liveRecyclerView = findViewById(R.id.liveRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        liveRecyclerView.setLayoutManager(linearLayoutManager);
        toolbar = findViewById(R.id.toolbarNews);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        recyclerViewHoroscope = findViewById(R.id.recyclerViewHoroscope);
        share = findViewById(R.id.share);
        share.setOnClickListener(this);
        love = findViewById(R.id.loveDesc);
        career = findViewById(R.id.careerDesc);
        money = findViewById(R.id.financeDesc);
        health = findViewById(R.id.healthDesc);
        travels = findViewById(R.id.travelDesc);
        bannerTitle = findViewById(R.id.bannerTitle);
        desc = findViewById(R.id.bannerDescription);
        getData();
        String title = "";
        getHoroscopeData(title);
        getLiveData();
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
    protected void onStart() {
        Log.d("Activity", "Stop");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("Activity", "Resume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d("Activity", "Restart");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d("Activity", "Stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("Activity", "Stop");
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
     //   shareAppLink();
    }

    private void shareAppLink() {
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.astroacharyamukti.app");
//        sendIntent.setType("text/plain");
//        sendIntent.setPackage("com.whatsapp");
//        startActivity(sendIntent);
    }

    private void getData() {
        String url = "https://theacharyamukti.com/clientapi/daily-horoscope.php";
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewHoroscope.setLayoutManager(linearLayoutManager);
        RequestQueue request = Volley.newRequestQueue(this);
        @SuppressLint("NotifyDataSetChanged") StringRequest request1 = new StringRequest(Request.Method.GET, url, response -> {
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
            horoscopeAdapter = new HoroscopeAdapter(getApplicationContext(), imageModels);
            horoscopeAdapter.notifyDataSetChanged();
            recyclerViewHoroscope.setAdapter(horoscopeAdapter);
            horoscopeAdapter.setOnPageItemClickListener((position, title) -> {
                switch (title) {
                    case "PISCES-Daily-Horoscope":
                        getHoroscopeData(title);
                        break;
                    case "AQUARIUS-Daily-Horoscope":
                        getHoroscopeData(title);
                        break;
                    case "CAPRICORN-Daily-Horoscope":
                        getHoroscopeData(title);
                        break;
                    case "SAGITTARIUS-Daily-Horoscope":
                        getHoroscopeData(title);
                        break;
                    case "SCORPIO-Daily-Horoscope":
                        getHoroscopeData(title);
                        break;
                    case "LIBRA-Daily-Horoscope":
                        getHoroscopeData(title);
                        break;
                    case "VIRGO-Daily-Horoscope":
                        getHoroscopeData(title);
                        break;
                    case "LEO-Daily-Horoscope":
                        getHoroscopeData(title);
                        break;
                    case "CANCER-Daily-Horoscope":
                        getHoroscopeData(title);
                        break;
                    case "Gemini-Daily-Horoscope":
                        getHoroscopeData(title);
                        break;
                    case "TAURUS-Daily-Horoscope":
                        getHoroscopeData(title);
                        break;
                    case "Aries-Daily-Horoscope":
                        getHoroscopeData(title);
                        break;
                }
            });
        }, error -> {
        });
        request.add(request1);
    }

    public void getHoroscopeData(String title) {
        Call<HoroscopeModel> call = RetrofitClient.getInstance().getApi().getBlog(title);
        call.enqueue(new Callback<HoroscopeModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NonNull Call<HoroscopeModel> call, @NonNull Response<HoroscopeModel> response) {
                HoroscopeModel model = response.body();
                if (response.isSuccessful()) {
                    assert model != null;
                    bannerTitle.setText(model.getHeading());
                    desc.setText(Html.fromHtml(model.getToday_horo(), Html.FROM_HTML_MODE_COMPACT));
                    desc.setText(model.getToday_horo());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        desc.setText(Html.fromHtml(model.getToday_horo(), Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        desc.setText(Html.fromHtml(model.getToday_horo()));
                    }
                    love.setText(model.getLove_horo_desc());
                    career.setText(model.getCareer_horo_desc());
                    health.setText(model.getHealth_horo_desc());
                    money.setText(model.getFinance_horo_desc());
                    travels.setText(model.getTravel_horo_desc());
                } else {
                    assert model != null;
                    Toast.makeText(Horoscope.this, model.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HoroscopeModel> call, Throwable t) {
                Toast.makeText(Horoscope.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getLiveData() {
        final List<AstroProfileModel> astroProfileModels = new ArrayList<>();
        String url = "https://theacharyamukti.com/clientapi/online-astro.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        @SuppressLint("NotifyDataSetChanged") StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray arr = obj.getJSONArray("body");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jb = arr.getJSONObject(i);
                    AstroProfileModel astro = new AstroProfileModel(
                            jb.getString("image"),
                            jb.getString("status"),
                            jb.getString("name"),
                            jb.getString("reg_id"),
                            jb.getString("experience"),
                            jb.getString("callrate"),
                            jb.getString("language"),
                            jb.getString("asttype"),
                            jb.getString("avgrating1"));

                    astroProfileModels.add(astro);
                }
                liveAdapter = new LiveAdapter(getApplicationContext(), astroProfileModels);
                liveAdapter.notifyDataSetChanged();
                liveRecyclerView.setAdapter(liveAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(request);
    }
}