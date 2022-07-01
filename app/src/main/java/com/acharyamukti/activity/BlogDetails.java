package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.acharyamukti.R;

public class BlogDetails extends AppCompatActivity implements View.OnClickListener {
    ImageView sharing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_details);
        sharing = findViewById(R.id.sharing);
        sharing.setOnClickListener(this);
        //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://theacharyamukti.com/blog");
//        sendIntent.setType("text/plain");
//        sendIntent.setPackage("com.whatsapp");
//        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(sendIntent);
    }
}