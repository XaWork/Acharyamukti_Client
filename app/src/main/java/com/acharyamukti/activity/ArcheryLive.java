package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.adapter.AstroProfile;
import com.acharyamukti.model.AstroProfileModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArcheryLive extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AstroProfile astroProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acharyamukti_live);
        toolbar = findViewById(R.id.toolbarLive);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Acharyamukti Live");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recyclerViewLive);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        getProfileData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        this.finish();
        return super.onOptionsItemSelected(item);
    }

    private void getProfileData() {
        final List<AstroProfileModel> astroProfileModels = new ArrayList<>();
        String url = "https://theacharyamukti.com/clientapi/online-astro.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arr = response.getJSONArray("body");
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jb = arr.getJSONObject(i);
                        AstroProfileModel astro = new AstroProfileModel(
                                jb.getString("name"),
                                jb.getString("reg_id"),
                                jb.getString("experience"),
                                jb.getString("callrate"),
                                jb.getString("language"),
                                jb.getString("asttype"),
                                jb.getString("avgrating1"), jb.getString("avgrating1"));
                        astroProfileModels.add(astro);
                    }
                    astroProfile = new AstroProfile(getApplicationContext(),astroProfileModels);
                    astroProfile.notifyDataSetChanged();
                    recyclerView.setAdapter(astroProfile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(objectRequest);
    }

}