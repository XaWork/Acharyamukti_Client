package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.DataModel;
import com.acharyamukti.model.WalletModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wallet extends AppCompatActivity implements View.OnClickListener {
    TextView text0, text1, text2, text3, text4, text5, text6, text7, text8, totalBalance;
    CardView card1;
    String userId;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        text1 = findViewById(R.id.txtRs1);
        text2 = findViewById(R.id.txtRs2);
        text3 = findViewById(R.id.txtRs3);
        text4 = findViewById(R.id.txtRs4);
        text5 = findViewById(R.id.txtRs5);
        text6 = findViewById(R.id.txtRs6);
        card1 = findViewById(R.id.card1);
        text7 = findViewById(R.id.txtRs7);
        text8 = findViewById(R.id.txtRs8);
        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
        text4.setOnClickListener(this);
        text5.setOnClickListener(this);
        text6.setOnClickListener(this);
        text7.setOnClickListener(this);
        text8.setOnClickListener(this);
        totalBalance = findViewById(R.id.totalBalance);
        Intent intent = getIntent();
        String balance = intent.getStringExtra("total");
        totalBalance.setText(balance);
        userId = Backend.getInstance(this).getUserId();
        if (userId != null) {
            getProfile();
            getTotalBalance(userId);
        } else {
            Toast.makeText(this, "User are not login", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        finish();
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.txtRs0:
//                Intent txt1 = new Intent(getApplicationContext(), PaymentInformation.class);
//                txt1.putExtra("balance", "1");
//                startActivity(txt1);
//                break;
            case R.id.txtRs1:
                Intent intent = new Intent(getApplicationContext(), PaymentInformation.class);
                intent.putExtra("balance", "1");
                startActivity(intent);
                break;
            case R.id.txtRs2:
                Intent txt2 = new Intent(getApplicationContext(), PaymentInformation.class);
                txt2.putExtra("balance", "100");
                startActivity(txt2);
                break;
            case R.id.txtRs3:
                Intent txt3 = new Intent(getApplicationContext(), PaymentInformation.class);
                txt3.putExtra("balance", "200");
                startActivity(txt3);
                break;
            case R.id.txtRs4:
                Intent txt4 = new Intent(getApplicationContext(), PaymentInformation.class);
                txt4.putExtra("balance", "500");
                startActivity(txt4);
                break;
            case R.id.txtRs5:
                Intent txt5 = new Intent(getApplicationContext(), PaymentInformation.class);
                txt5.putExtra("balance", "1000");
                startActivity(txt5);
                break;
            case R.id.txtRs6:
                Intent txt6 = new Intent(getApplicationContext(), PaymentInformation.class);
                txt6.putExtra("balance", "1500");
                startActivity(txt6);
                break;
            case R.id.txtRs7:
                Intent txt7 = new Intent(getApplicationContext(), PaymentInformation.class);
                txt7.putExtra("balance", "2000");
                startActivity(txt7);
                break;
            case R.id.txtRs8:
                Intent txt8 = new Intent(getApplicationContext(), PaymentInformation.class);
                txt8.putExtra("balance", "2500");
                startActivity(txt8);
                break;
        }
    }

    private void getTotalBalance(String userId) {
        Call<DataModel> call = RetrofitClient.getInstance().getApi().getTotalBalance(userId);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NonNull Call<DataModel> call, @NonNull Response<DataModel> response) {
                DataModel dataModel = response.body();
                assert dataModel != null;
                //Log.e("wallet", dataModel.getWallet());

                if (response.isSuccessful()) {
                    if (dataModel.getError().equals("false")) {
                        String total = dataModel.getWallet();
                        totalBalance.setText(total);
                        Backend.getInstance(getApplicationContext()).saveWalletBalance(total);
                    } else {
                        Log.e("wallet", "data model error");
                        Toast.makeText(Wallet.this, "Failed to load wallet balance", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataModel> call, @NonNull Throwable t) {
                Toast.makeText(Wallet.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProfile() {
        String user_id = Backend.getInstance(this).getUserId();
        Call<DataModel> call = RetrofitClient.getInstance().getApi().viewProfile(user_id);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NonNull Call<DataModel> call, @NonNull Response<DataModel> response) {
                DataModel dataModel = response.body();
                if (response.isSuccessful()) {
                    Log.e("wallet", "Can use promotional pack : "+dataModel.getCanUsPromotionalPack());
                    //dataModel.setCanUsPromotionalPack(true);
                    if(!dataModel.getCanUsPromotionalPack()){
                        card1.setVisibility(View.GONE);
                    }else
                        card1.setVisibility(View.VISIBLE);
                } else {
                    finish();
                }
            }
            @Override
            public void onFailure(@NonNull Call<DataModel> call, @NonNull Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}