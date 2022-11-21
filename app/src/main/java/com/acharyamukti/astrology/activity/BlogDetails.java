package com.acharyamukti.astrology.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.astrology.api.RetrofitClient;
import com.acharyamukti.astrology.model.BlogModel;
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
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://theacharyamukti.com/blog");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
    }
    private void getBlogDetails() {
        Call<BlogModel> call = RetrofitClient.getInstance().getApi().getBlogDetails(id);
        call.enqueue(new Callback<BlogModel>() {
            @Override
            public void onResponse(@NonNull Call<BlogModel> call, @NonNull Response<BlogModel> response) {
                BlogModel blogModel = response.body();
                if (response.isSuccessful()) {
                    assert blogModel != null;
                    blogTitle.setText(blogModel.getName());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        blogDes.setText(Html.fromHtml(blogModel.getDescription(), Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        blogDes.setText(Html.fromHtml(blogModel.getDescription()));
                    }
                    textDate.setText(blogModel.getDate());
                    String url = "https://theacharyamukti.com/image/product/" + blogModel.getImage();
                    Glide.with(getApplicationContext()).load(url).into(blogImage);
                }
            }
            @Override
            public void onFailure(@NonNull Call<BlogModel> call, @NonNull Throwable t) {
                Toast.makeText(BlogDetails.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}