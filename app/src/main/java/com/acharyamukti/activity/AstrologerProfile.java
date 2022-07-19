package com.acharyamukti.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.adapter.ReviewAdapter;
import com.acharyamukti.databinding.ActivityAstrologerProfileBinding;
import com.acharyamukti.model.ReviewModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class AstrologerProfile extends AppCompatActivity {
    RecyclerView recyclerView;
    ReviewAdapter reviewAdapter;
    LinearLayoutManager linearLayoutManager;
    ImageView profileImage;
    TextView profileName, designation,
            status, rating, txtExperience2,
            txtMin, txtSpoken, txtExp1,
            txtSummary_long;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.acharyamukti.databinding.ActivityAstrologerProfileBinding binding =
                ActivityAstrologerProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar = binding.toolbarAstro;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
        profileImage = findViewById(R.id.profileImageSingle);
        profileName = findViewById(R.id.profileNameS);
        designation = findViewById(R.id.designation);
        status = findViewById(R.id.user_online_status);
        rating = findViewById(R.id.total_rating);
        txtExperience2 = findViewById(R.id.txtExperience2);
        txtMin = findViewById(R.id.txtMin);
        txtSpoken = findViewById(R.id.txtSpoken);
        txtExp1 = findViewById(R.id.txtExp1);
        txtSummary_long = findViewById(R.id.txtSummary_long);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.ratingRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Intent intent = getIntent();
        String userid = intent.getStringExtra("reg_id");
        getProfileData(userid);
        getClientReview(userid);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void getProfileData(String userId) {
        String url = "https://theacharyamukti.com/clientapi/single-astro.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONObject jb = new JSONObject(response);
                String image = jb.getString("image");
                String mobile = jb.getString("astro_moble");
                String name = jb.getString("name");
                String userStatus = jb.getString("status");
                jb.getString("reg_id");
                String exp = jb.getString("experience");
                jb.getString("callrate");
                String language = jb.getString("language");
                String astroType = jb.getString("asttype");
                String ratingTotal = jb.getString("avgrating1");
                String summary = jb.getString("biographic");
                Glide.with(this).load(image).into(profileImage);
                profileName.setText(name);
                status.setText(userStatus);
                txtSpoken.setText(language);
                txtExp1.setText(exp);
                txtSummary_long.setText(summary);
                rating.setText(ratingTotal);
                designation.setText(astroType);
                toolbar.setTitle(name);
                if (userStatus.equals("Online")) {
                    status.setBackgroundResource(R.drawable.green_conner_bg);
                } else {
                    status.setBackgroundResource(R.drawable.red_without_conner_bg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(AstrologerProfile.this, error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("reg_id", userId);
                return params;
            }
        };
        requestQueue.add(request);
    }

    private void getClientReview(String userid) {
        List<ReviewModel> reviewModels = new ArrayList<>();
        String url = "https://theacharyamukti.com/clientapi/review.php";
        RequestQueue request = Volley.newRequestQueue(this);
        @SuppressLint("NotifyDataSetChanged") StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("body");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jb = jsonArray.getJSONObject(i);
                    ReviewModel reviewModel = new ReviewModel(
                            jb.getString("rating"),
                            jb.getString("reg_id"),
                            jb.getString("commente"),
                            jb.getString("date"),
                            jb.getString("usernme")
                    );
                    reviewModels.add(reviewModel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            reviewAdapter = new ReviewAdapter(getApplicationContext(), reviewModels);
            reviewAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(reviewAdapter);
        }, error -> Toast.makeText(AstrologerProfile.this, error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("reg_id", userid);
                return params;
            }
        };
        request.add(stringRequest);
    }
}