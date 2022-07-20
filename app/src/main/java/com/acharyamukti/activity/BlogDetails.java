package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.model.BlogModel;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogDetails extends AppCompatActivity implements View.OnClickListener {
    ImageView sharing, blogImage;
    Toolbar toolbar;
    String id;
    TextView textDate, blogTitle, blogDes;
    CollapsingToolbarLayout layoutImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scroller_layout);
        sharing = findViewById(R.id.someImage);
        sharing.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        id = intent.getStringExtra("blog_id");
        textDate = findViewById(R.id.textDate);
        blogTitle = findViewById(R.id.blogTitle);
        blogDes = findViewById(R.id.blogDes);
        layoutImage = findViewById(R.id.toolbar_layout);
        blogImage = findViewById(R.id.blogImage);
        getBlogDetails();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        shareViaWhatsApp();
    }

    public void shareViaWhatsApp() {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Application of social rating share with your friend");
        try {
            Objects.requireNonNull(getApplicationContext()).startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://theacharyamukti.com/blog"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
            this.finish();
        }
    }

    private void getBlogDetails() {
        Call<BlogModel> call = RetrofitClient.getInstance().getApi().getBlogDetails(id);
        call.enqueue(new Callback<BlogModel>() {
            @Override
            public void onResponse(Call<BlogModel> call, Response<BlogModel> response) {
                BlogModel blogModel = response.body();
                if (response.isSuccessful()) {
                    blogTitle.setText(blogModel.getName());
                    blogDes.setText(blogModel.getDescription());
                    textDate.setText(blogModel.getDate());
                    String url = "https://theacharyamukti.com/image/product/" + blogModel.getImage();
                    Glide.with(getApplicationContext()).load(url).into(blogImage);
                }
            }

            @Override
            public void onFailure(Call<BlogModel> call, Throwable t) {
                Toast.makeText(BlogDetails.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}