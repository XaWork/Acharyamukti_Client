package com.acharyamukti.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.acharyamukti.R;
import com.acharyamukti.activity.BlogDetails;
import com.acharyamukti.adapter.NewsAdapter;
import com.acharyamukti.model.BlogModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;



public class Free extends Fragment implements View.OnClickListener {
     List<BlogModel> blogModels = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_free, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewNews);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        Button viewAllData = view.findViewById(R.id.viewAllBlogData);
        viewAllData.setOnClickListener(this);
        getBloData();
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), BlogDetails.class);
        startActivity(intent);
    }

    private void getBloData() {
        String url = "https://theacharyamukti.com/clientapi/blog.php";
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        @SuppressLint("NotifyDataSetChanged") StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("body");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jb = jsonArray.getJSONObject(i);
                    BlogModel blogModel = new BlogModel(
                            jb.getString("date"),
                            jb.getString("name"),
                            jb.getString("blog_id"),
                            jb.getString("description"),
                            jb.getString("image")
                    );
                    blogModels.add(blogModel);
                }
                newsAdapter = new NewsAdapter(getContext(), R.layout.custom_news_layout, blogModels);
                newsAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(newsAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(stringRequest);
    }
}