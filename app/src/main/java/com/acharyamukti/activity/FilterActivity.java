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
        switch (view.getId()) {
            case R.id.checkbox1:
                if (checkBox1.isChecked()) {
                    String type = "Vedic";
                    getFilterData(type);
                } else if (checkBox2.isChecked()) {
                    String type = "Vedic";

                } else if (checkBox3.isChecked()) {
                    String type = "Vedic";

                } else if (checkBox4.isChecked()) {
                    String type = "Vedic";

                } else if (checkBox5.isChecked()) {
                    String type = "Vedic";

                } else if (checkBox6.isChecked()) {
                    String type = "Vedic";

                } else if (checkBox7.isChecked()) {
                    String type = "Vedic";

                } else if (checkBox8.isChecked()) {
                    String type = "Vedic";

                } else if (checkBox9.isChecked()) {
                    String type = "Vedic";

                } else if (checkBox10.isChecked()) {
                    String type = "Vedic";
                }
                break;
        }
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