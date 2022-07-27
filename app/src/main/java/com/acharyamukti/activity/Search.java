package com.acharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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


public class Search extends AppCompatActivity implements TextWatcher {
    EditText searchView;
    RecyclerView recyclerViewSearch;
    List<AstroProfileModel> astroProfileModels = new ArrayList<>();
    UserDetailsAdapter userDetailsAdapter;
    String textName;
    List<AstroProfileModel> list = new ArrayList<>();
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.search_bar);
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewSearch.setLayoutManager(layoutManager);
        recyclerViewSearch.setHasFixedSize(true);
        searchView.addTextChangedListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void afterTextChanged(Editable postText) {
        if (postText.toString().isEmpty()) {
            recyclerViewSearch.setAdapter(new UserDetailsAdapter(getApplicationContext(), astroProfileModels));
            userDetailsAdapter.notifyDataSetChanged();
        } else {
            Filter(postText.toString());
        }
        getSearchingData();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void Filter(String text) {
        for (AstroProfileModel astroProfileModel : astroProfileModels) {
            if (astroProfileModel.getName().equals(text)) {
                list.add(astroProfileModel);
                textName = astroProfileModel.getName();
            }
        }
        recyclerViewSearch.setAdapter(new UserDetailsAdapter(getApplicationContext(), astroProfileModels));
        userDetailsAdapter.notifyDataSetChanged();

    }

    private void getSearchingData() {
        String searchUrl = "https://theacharyamukti.com/clientapi/astro-search.php";
        RequestQueue request = Volley.newRequestQueue(this);
        @SuppressLint("NotifyDataSetChanged") StringRequest stringRequest = new StringRequest(Request.Method.POST, searchUrl, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("body");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jb = jsonArray.getJSONObject(i);
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
                userDetailsAdapter = new UserDetailsAdapter(getApplicationContext(), astroProfileModels);
                userDetailsAdapter.notifyDataSetChanged();
                recyclerViewSearch.setAdapter(userDetailsAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(Search.this, error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("search", textName);
                return params;
            }
        };
        request.add(stringRequest);
    }
}