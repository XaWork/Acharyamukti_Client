package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.acharyamukti.R;

import java.util.Objects;

public class ConsultNowActivity extends AppCompatActivity {
    TextView discount_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_now);
        discount_price = findViewById(R.id.Original_price);
        discount_price.setPaintFlags(discount_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }
}