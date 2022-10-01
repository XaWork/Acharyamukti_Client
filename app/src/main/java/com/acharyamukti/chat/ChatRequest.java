package com.acharyamukti.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import com.acharyamukti.R;
import java.util.Objects;

public class ChatRequest extends AppCompatActivity implements View.OnClickListener {
    LinearLayout startChat;
    Toolbar toolbarChat;

    @SuppressLint("UseSupportActionBar")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_request);
        startChat = findViewById(R.id.startChat);
        startChat.setOnClickListener(this);
        toolbarChat = findViewById(R.id.toolbarChat);
        setSupportActionBar(toolbarChat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        startActivity(intent);
    }
}