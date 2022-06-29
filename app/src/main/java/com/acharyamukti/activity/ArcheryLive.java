package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.MenuItem;
import com.acharyamukti.R;
import com.acharyamukti.adapter.AstroProfile;

public class ArcheryLive extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AstroProfile astroProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acharyamukti_live);
        toolbar = findViewById(R.id.toolbarLive);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Acharyamukti Live");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recyclerViewLive);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        astroProfile = new AstroProfile(getApplicationContext());
        recyclerView.setAdapter(astroProfile);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        this.finish();
        return super.onOptionsItemSelected(item);
    }
}