package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.adapter.UserDetailsAdapter;
import com.acharyamukti.model.AstroProfileModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class FilterActivity extends AppCompatActivity implements View.OnClickListener {

    List<AstroProfileModel> astroProfile = new ArrayList<>();
    RecyclerView recyclerViewFilter;
    UserDetailsAdapter userDetailsAdapter;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5,
            checkBox6, checkBox7, checkBox8, checkBox9, checkBox10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        recyclerViewFilter = findViewById(R.id.recyclerViewFilter);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);
        checkBox5 = findViewById(R.id.checkbox5);
        checkBox6 = findViewById(R.id.checkbox6);
        checkBox7 = findViewById(R.id.checkbox7);
        checkBox8 = findViewById(R.id.checkbox8);
        checkBox9 = findViewById(R.id.checkbox9);
        checkBox10 = findViewById(R.id.checkbox10);
        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);
        checkBox4.setOnClickListener(this);
        checkBox5.setOnClickListener(this);
        checkBox6.setOnClickListener(this);
        checkBox7.setOnClickListener(this);
        checkBox8.setOnClickListener(this);
        checkBox9.setOnClickListener(this);
        checkBox10.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewFilter.setLayoutManager(linearLayoutManager);
//        String type = "Vedic";
//        getFilterData(type);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        String type = null;
        switch (view.getId()) {
            case R.id.checkbox1:
                type = "Vedic";
                break;
            case R.id.checkbox2:
                type = "Tarot";
                break;
            case R.id.checkbox3:
                type = "Numerology";
                break;
            case R.id.checkbox4:
                type = "Vastu";
                break;
            case R.id.checkbox5:
                type = "Fengshui";
                break;
            case R.id.checkbox6:
                type = "KP";
                break;
            case R.id.checkbox7:
                type = "Prashna";
                break;
            case R.id.checkbox8:
                type = "Reiki Healing";
                break;
            case R.id.checkbox9:
                type = "Yoga/meditation";
                break;
            case R.id.checkbox10:
                type = "Pranic Healing";
                break;
        }
        getFilterData(type);
    }

    private void getFilterData(String type) {
        String url = "https://theacharyamukti.com/clientapi/astro-filtter.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        @SuppressLint("NotifyDataSetChanged") StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
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
                    astroProfile.add(astro);
                }
                userDetailsAdapter = new UserDetailsAdapter(getApplicationContext(), astroProfile);
                userDetailsAdapter.notifyDataSetChanged();
                recyclerViewFilter.setAdapter(userDetailsAdapter);
                recyclerViewFilter.setNestedScrollingEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(FilterActivity.this, error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("astype", type);
                return params;
            }
        };
        requestQueue.add(request);
    }
}