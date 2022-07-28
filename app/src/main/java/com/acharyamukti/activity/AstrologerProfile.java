package com.acharyamukti.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.adapter.ReviewAdapter;
import com.acharyamukti.api.ApiInterface;
import com.acharyamukti.databinding.ActivityAstrologerProfileBinding;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.CallDataModel;
import com.acharyamukti.model.ReviewModel;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AstrologerProfile extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    ReviewAdapter reviewAdapter;
    LinearLayoutManager linearLayoutManager;
    ImageView profileImage;
    TextView profileName, designation,
            status, rating, txtExperience2,
            txtMin, txtSpoken, txtExp1,
            txtSummary_long, calling;
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
        calling = findViewById(R.id.calling);
        calling.setOnClickListener(this);
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
                Backend.getInstance(this).saveMobile(mobile);
                profileName.setText(name);
                status.setText(userStatus);
                calling.setText(userStatus);
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

    private void getCallForAstrologer() {
        String k_number = "9513632690";
        String agent_number = "8010104747";
        String customer_number = "7330004646";
        String caller_id = "8035338348";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kpi.knowlarity.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        final CallDataModel callDataModel = new CallDataModel(k_number, agent_number, customer_number, caller_id);
        Call<CallDataModel> dataModelCall = apiInterface.getCalling(callDataModel);
        dataModelCall.enqueue(new Callback<CallDataModel>() {
            @Override
            public void onResponse(@NonNull Call<CallDataModel> call, @NonNull Response<CallDataModel> response) {
                CallDataModel dataModel = response.body();
                if (response.isSuccessful()) {
                    assert dataModel != null;
                    Toast.makeText(AstrologerProfile.this, dataModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CallDataModel> call, @NonNull Throwable t) {
                Toast.makeText(AstrologerProfile.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        getCall();
    }

    private void getCall() {
        String url = "https://kpi.knowlarity.com/Basic/v1/account/call/makecall";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("success")) {
                        Toast.makeText(AstrologerProfile.this, "Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AstrologerProfile.this, "error", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
            Toast.makeText(AstrologerProfile.this, error.toString(), Toast.LENGTH_SHORT).show();

        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Content-Length", "<calculated when request is sent>");
                headers.put("Host", "<calculated when request is sent>");
                headers.put("User-Agent", "PostmanRuntime/7.29.2");
                headers.put("Accept", "*/*");
                headers.put("Accept-Encoding", "gzip, deflate, br");
                headers.put("Authorization", "bearer"+"800333b2-405d-4947-899f-f7686663d30f");
                headers.put("x-api-key", "6m9Ux0on1k1opZ1qyEZMr4cl29UfAPqK2rryZCZR");
                return headers;
            }


            @Nullable
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("k_number", "+919513632690");
                params.put("agent_number", "+918010104747");
                params.put("customer_number", "+917330004646");
                params.put("caller_id", "+918035338348");
                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        DiskBasedCache cache = new
                DiskBasedCache(getApplicationContext().getCacheDir(), 500 * 1024 * 1024);
        requestQueue = new RequestQueue(cache, new BasicNetwork(new
                HurlStack()));
        requestQueue.start();
    }
}