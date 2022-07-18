package com.acharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;
import com.acharyamukti.R;
import com.acharyamukti.adapter.UserDetailsAdapter;
import com.acharyamukti.model.AstroProfileModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchView searchView;
    RecyclerView recyclerViewSearch;
    List<AstroProfileModel> astroProfileModels = new ArrayList<>();
    UserDetailsAdapter userDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(this);
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSearch.setLayoutManager(layoutManager);
//        ArrayAdapter<String> adapter;
//        ArrayList<String> myList;
//        myList = new ArrayList<>();
//        myList.add("C");
//        myList.add("C++");
//        myList.add("C#");
//        myList.add("Java");
//        myList.add("Advanced java");
//        myList.add("Interview prep with c++");
//        myList.add("Interview prep with java");
//        myList.add("data structures with c");
//        myList.add("data structures with java");
//
//        adapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                myList);
//        listView.setAdapter(adapter);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        getSearchingData();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void getSearchingData() {
        String searchUrl = "https://theacharyamukti.com/clientapi/astro-search.php";
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, searchUrl, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("body");
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
                            jb.getString("avgrating1"));
                    astroProfileModels.add(astro);
                }
                UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter(getApplicationContext(), astroProfileModels);
                userDetailsAdapter.notifyDataSetChanged();
                recyclerViewSearch.setAdapter(userDetailsAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Search.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.add(stringRequest);
    }
}