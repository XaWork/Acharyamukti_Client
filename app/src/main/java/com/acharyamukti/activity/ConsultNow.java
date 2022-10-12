package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
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

public class ConsultNow extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerViewConsult;
    Button book_now_pack1, book_now_pack2, book_now_pack3, book_now_pack4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_now);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerViewConsult = findViewById(R.id.recyclerViewConsult);
        recyclerViewConsult.setLayoutManager(layout);
        book_now_pack1 = findViewById(R.id.book_now_pack);
        book_now_pack2 = findViewById(R.id.pack2);
        book_now_pack3 = findViewById(R.id.pack3);
        book_now_pack4 = findViewById(R.id.pack4);
        book_now_pack1.setOnClickListener(this);
        book_now_pack2.setOnClickListener(this);
        book_now_pack3.setOnClickListener(this);
        book_now_pack4.setOnClickListener(this);
        getOnlineAstro();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void getOnlineAstro() {
        ProgressDialog progress = ProgressDialog.show(getApplicationContext(), "Blog is Loading..",
                "Please wait......", true);
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
                progress.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(ConsultNow.this, error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(request);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.book_now_pack:
                buyNowDialog();
                break;
            case R.id.pack2:
                buyNowDialog();
                break;
            case R.id.pack3:
                buyNowDialog();
                break;
            case R.id.pack4:
                buyNowDialog();
                break;
        }
        buyNowDialog();
    }

    private void buyNowDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.book_now_custom_layout);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        ImageView cancel_icon = dialog.findViewById(R.id.cancel_icon);
        cancel_icon.setOnClickListener(view -> dialog.dismiss());
    }
}