package com.acharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.acharyamukti.R;

import java.util.ArrayList;

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchView searchView;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(this);
        listView = findViewById(R.id.listView);
        myList = new ArrayList<>();
        myList.add("C");
        myList.add("C++");
        myList.add("C#");
        myList.add("Java");
        myList.add("Advanced java");
        myList.add("Interview prep with c++");
        myList.add("Interview prep with java");
        myList.add("data structures with c");
        myList.add("data structures with java");

        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myList);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        if (myList.contains(query)) {
            adapter.getFilter().filter(query);
        } else {
            Toast.makeText(this, "Not found", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }
}