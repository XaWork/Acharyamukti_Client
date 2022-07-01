package com.acharyamukti.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.acharyamukti.R;
import com.acharyamukti.activity.BlogDetails;
import com.acharyamukti.adapter.NewsAdapter;
import com.acharyamukti.model.NewsModel;

import java.util.List;

public class Free extends Fragment implements View.OnClickListener {
    private List<NewsModel> newsModels;
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
        newsAdapter = new NewsAdapter(getContext(), R.layout.custom_news_layout, newsModels);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        Button viewAllData = view.findViewById(R.id.viewAllBlogData);
        viewAllData.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), BlogDetails.class);
        startActivity(intent);
    }
}