package com.acharyamukti.astrology.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.astrology.adapter.ReviewAdapter;
import com.acharyamukti.astrology.api.ApiInterface;
import com.acharyamukti.astrology.api.RetrofitClient;
import com.acharyamukti.databinding.ActivityAstrologerProfileBinding;
import com.acharyamukti.astrology.helper.Backend;
import com.acharyamukti.astrology.model.CallDataModel;
import com.acharyamukti.astrology.model.DataModel;
import com.acharyamukti.astrology.model.ReviewModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AstrologerProfile extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    ReviewAdapter reviewAdapter;
    LinearLayoutManager linearLayoutManager;
    ImageView profileImage, calling;
    TextView profileName, designation,
            status, rating, txtExperience2,
            txtMin, txtSpoken, txtExp1,
            txtSummary_long, calling_number;
    Toolbar toolbar;
    String userid, reg_id, callRate;
    ProgressBar progressBar;
    int callDuration;


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
        calling = binding.calling;
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
        userid = intent.getStringExtra("reg_id");
        getProfileData(userid);
        getClientReview(userid);
        calling = findViewById(R.id.calling);
        calling.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void dialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.calling_dialog_layout);
        Button goBack = dialog.findViewById(R.id.goBack);
        calling_number = dialog.findViewById(R.id.calling_number);
        String mobile = Backend.getInstance(this).getMobile();
        calling_number.setText(mobile);
        goBack.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DashBoard.class);
            startActivity(intent);
            dialog.dismiss();
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    private void getProfileData(String userId) {

        String url = "https://theacharyamukti.com/clientapi/single-astro.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            try {
                progressBar.setVisibility(View.INVISIBLE);
                JSONObject jb = new JSONObject(response);
                String image = jb.getString("image");
                String mobile = jb.getString("astro_moble");
                String name = jb.getString("name");
                String userStatus = jb.getString("status");
                reg_id = jb.getString("reg_id");

                Log.e("astro profile", "Get Astro Profile reg id : " + jb.getString("reg_id"));

                String exp = jb.getString("experience");
                String expertise = jb.getString("expertise");
                callRate = jb.getString("callrate");
                Log.e("astro profile", "Call rate " + jb.getString("callrate"));
                String language = jb.getString("language");
                String astroType = jb.getString("asttype");
                String ratingTotal = jb.getString("avgrating1");

                String summary = jb.getString("biographic");
                Glide.with(this).load(image).into(profileImage);
                Backend.getInstance(this).saveAstroMobile(mobile);
                profileName.setText(name);
                status.setText(userStatus);
                txtSpoken.setText(language);
                txtExp1.setText(expertise);
                txtExperience2.setText(exp);
                txtMin.setText(callRate);
                txtSummary_long.setText(summary);
                rating.setText(ratingTotal);
                designation.setText(astroType);
                toolbar.setTitle(name);
                Backend.getInstance(this).saveStatus(userStatus);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    txtSummary_long.setText(Html.fromHtml(summary, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    txtSummary_long.setText(Html.fromHtml(summary));
                }
                if (userStatus.equals("Online")) {
                    status.setBackgroundResource(R.drawable.green_conner_bg);
                } else {
                    status.setBackgroundResource(R.drawable.red_without_conner_bg);
                }

                getCallDuration();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(AstrologerProfile.this, error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("reg_id", userId);

                Log.e("astro profile", "User id : " + userId);

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
            progressBar.setVisibility(View.INVISIBLE);
            try {
                JSONObject jsonObject = new JSONObject(response);

                JSONArray jsonArray = jsonObject.getJSONArray("body");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jb = jsonArray.getJSONObject(i);

                    Log.e("home", jb.toString());

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

    @Override
    public void onClick(View view) {
        getCallDuration();
        String userid = Backend.getInstance(this).getUserId();
        String balance = Backend.getInstance(this).getWalletBalance();

        Log.e("astro profile", "user id " + userid.length()
                + "\nbalance " + balance +
                "\ncall duration " + callDuration+
        "\ncall rate : "+callRate);

        if (userid.length() != 0 && balance.length() != 0 && callDuration != 0 && Integer.parseInt(callRate) <= Integer.parseInt(balance)) {
            getCallForAstrologer();
        } else if (userid.length() == 0) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }/*else if(callDuration == null){
            Intent intent = new Intent(this, Wallet.class);
            startActivity(intent);
            //Toast.makeText(this, "Please recharge now ", Toast.LENGTH_SHORT).show();
        }*/ else {
            Intent intent = new Intent(this, Wallet.class);
            startActivity(intent);
            Toast.makeText(this, "Please recharge now ", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCallDuration() {
        String userId = Backend.getInstance(this).getUserId();
        Call<DataModel> call = RetrofitClient.getInstance().getApi().getCallDurations(userId, reg_id);

        Log.e("astro profile", "user id : " + userId + "\nreg id : " + reg_id);

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NonNull Call<DataModel> call, @NonNull Response<DataModel> response) {
                progressBar.setVisibility(View.INVISIBLE);
                DataModel data = response.body();
                if (response.isSuccessful()) {
                    assert data != null;
                    callDuration = Integer.parseInt(data.getCallDurationTime());
                    if (callDuration != 0) {
                        Backend.getInstance(getApplicationContext()).saveCallDuration(String.valueOf(callDuration));
                    } else {
                        Toast.makeText(AstrologerProfile.this, data.getStatus(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataModel> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(AstrologerProfile.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCallForAstrologer() {
        String userid = Backend.getInstance(this).getUserId();
        String balance = Backend.getInstance(this).getWalletBalance();

        Log.e("astro profile", "user id " + userid.length()
                + "balance" + balance.length() + "car duration" + callDuration);

        String k_number = "+919513632690";
        String agentNumber = Backend.getInstance(this).getAstroMobile();
        String agent_number = "+91" + agentNumber;
        String customerNumber = Backend.getInstance(this).getMobile();
        String customer_number = "+91" + customerNumber;
        String caller_id = "+918035338348";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kpi.knowlarity.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        CallDataModel callDataModel = new CallDataModel(k_number, agent_number, customer_number, caller_id, callDuration);
        Call<CallDataModel> dataModelCall = apiInterface.getCalling(callDataModel);
        dataModelCall.enqueue(new Callback<CallDataModel>() {
            @Override
            public void onResponse(@NonNull Call<CallDataModel> call, @NonNull Response<CallDataModel> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    dialog();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CallDataModel> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(AstrologerProfile.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}