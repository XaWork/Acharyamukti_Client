package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.acharyamukti.R;

public class Wallet extends AppCompatActivity implements View.OnClickListener {
    TextView text1, text2, text3, text4, text5, text6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
    //    getActionBar().setDisplayHomeAsUpEnabled(true);
        text1 = findViewById(R.id.txtRs1);
        text2 = findViewById(R.id.txtRs2);
        text3 = findViewById(R.id.txtRs3);
        text4 = findViewById(R.id.txtRs4);
        text5 = findViewById(R.id.txtRs5);
        text6 = findViewById(R.id.txtRs6);
        text1.setOnClickListener(this);
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
            case R.id.txtRs1:
                Intent intent = new Intent(getApplicationContext(), PaymentInformation.class);
                intent.putExtra("balance", "100");
                startActivity(intent);
                break;
            case R.id.txtRs2:
                Intent txt2 = new Intent(getApplicationContext(), PaymentInformation.class);
                txt2.putExtra("balance", "200");
                startActivity(txt2);
                break;
            case R.id.txtRs3:
                Intent txt3 = new Intent(getApplicationContext(), PaymentInformation.class);
                txt3.putExtra("balance", "500");
                startActivity(txt3);
                break;
            case R.id.txtRs4:
                Intent txt4 = new Intent(getApplicationContext(), PaymentInformation.class);
                txt4.putExtra("balance", "1000");
                startActivity(txt4);
                break;
            case R.id.txtRs5:
                Intent txt5 = new Intent(getApplicationContext(), PaymentInformation.class);
                txt5.putExtra("balance", "2000");
                startActivity(txt5);
                break;
            case R.id.txtRs6:
                Intent txt6 = new Intent(getApplicationContext(), PaymentInformation.class);
                txt6.putExtra("balance", "5000");
                startActivity(txt6);
                break;
        }
    }
}