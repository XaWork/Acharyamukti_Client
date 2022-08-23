package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
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
import java.util.List;
import java.util.Objects;

public class ConsultNowActivity extends AppCompatActivity implements View.OnClickListener {
    TextView Original_price, Original_price1, Original_price2, Original_price3;
    RecyclerView recyclerViewConsult;
    Button book_now_pack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_now);
        Original_price = findViewById(R.id.Original_price);
        Original_price1 = findViewById(R.id.Original_price1);
        Original_price2 = findViewById(R.id.Original_price2);
        Original_price3 = findViewById(R.id.Original_price3);
        Original_price.setPaintFlags(Original_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Original_price1.setPaintFlags(Original_price1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Original_price2.setPaintFlags(Original_price2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Original_price3.setPaintFlags(Original_price3.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerViewConsult = findViewById(R.id.recyclerViewConsult);
        recyclerViewConsult.setLayoutManager(layout);
        book_now_pack = findViewById(R.id.book_now_pack);
        book_now_pack.setOnClickListener(this);
        getOnlineAstro();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void getOnlineAstro() {
        final List<AstroProfileModel> astroProfileModels = new ArrayList<>();
        String url = "https://theacharyamukti.com/clientapi/online-astro.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        @SuppressLint("NotifyDataSetChanged") StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
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
                UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter(getApplicationContext(), astroProfileModels);
                userDetailsAdapter.notifyDataSetChanged();
                recyclerViewConsult.setAdapter(userDetailsAdapter);
                recyclerViewConsult.setNestedScrollingEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(ConsultNowActivity.this, error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(request);
    }

    @Override
    public void onClick(View view) {
        buyNowDialog();
    }

    private void buyNowDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.book_now_custom_layout);
        dialog.setCanceledOnTouchOutside(true);
    }
}