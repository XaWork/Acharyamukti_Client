package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.adapter.AstroProfile;
import com.acharyamukti.adapter.UserDetailsAdapter;
import com.acharyamukti.model.AstroProfileModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KundaliniMarriage extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbarKun;
    RecyclerView recyclerviewDetails;
    UserDetailsAdapter userDetailsAdapter;
    LinearLayoutManager linearLayoutManager;
    CheckBox search, filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundali_marige);
        recyclerviewDetails = findViewById(R.id.recyclerviewDetails);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewDetails.setLayoutManager(linearLayoutManager);
//        userDetailsAdapter = new UserDetailsAdapter(getApplicationContext());
//        recyclerviewDetails.setAdapter(userDetailsAdapter);
        toolbarKun = findViewById(R.id.toolbarKun);
        setSupportActionBar(toolbarKun);
        toolbarKun.setTitle("Call with Astrologer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        search = findViewById(R.id.checkbox3);
        search.setOnClickListener(this);
        filter = findViewById(R.id.checkbox2);
        filter.setOnClickListener(this);
        getProfileDta();
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
        switch (view.getId()) {
            case R.id.checkbox2:
                Intent search = new Intent(getApplicationContext(), FilterActivity.class);
                startActivity(search);
                break;
            case R.id.checkbox3:
                Intent search1 = new Intent(getApplicationContext(), Search.class);
                startActivity(search1);
                break;
        }

    }

    private void getProfileDta() {
        final List<AstroProfileModel> astroProfileModels = new ArrayList<>();
        String url = "https://theacharyamukti.com/clientapi/avl-astro.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                    userDetailsAdapter = new UserDetailsAdapter(getApplicationContext(), astroProfileModels);
                    userDetailsAdapter.notifyDataSetChanged();
                    recyclerviewDetails.setAdapter(userDetailsAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KundaliniMarriage.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }
}