package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.acharyamukti.R;
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


public class FilterActivity extends AppCompatActivity {
    private final List<AstroProfileModel> astroProfile = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void getFilterData() {
        String url = "";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("Body");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jb = jsonArray.getJSONObject(i);
                    AstroProfileModel astro = new AstroProfileModel(
                            jb.getString("image"),
                            jb.getString("name"),
                            jb.getString("reg_id"),
                            jb.getString("experience"),
                            jb.getString("callrate"),
                            jb.getString("language"),
                            jb.getString("asttype"),
                            jb.getString("avgrating1")
                    );
                    astroProfile.add(astro);
                }
//                userDetailsAdapter = new UserDetailsAdapter(getApplicationContext(), astroProfileModels);
//                userDetailsAdapter.notifyDataSetChanged();
//                recyclerviewDetails.setAdapter(userDetailsAdapter);
//                recyclerviewDetails.setNestedScrollingEnabled(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(FilterActivity.this, error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("astype", "vedic");
                return params;
            }
        };
        requestQueue.add(request);
    }
}