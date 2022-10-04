package com.acharyamukti.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acharyamukti.R;

import java.util.ArrayList;
import java.util.Collections;

public class ChatRequest extends AppCompatActivity implements View.OnClickListener {
    LinearLayout startChat;
    Toolbar toolbarChat;
    Dialog dialog;

    @SuppressLint("UseSupportActionBar")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_request);
        startChat = findViewById(R.id.startChat);
        startChat.setOnClickListener(this);
        toolbarChat = findViewById(R.id.toolbarChat);
        setSupportActionBar(toolbarChat);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        getChatDetails();
    }

    private void getChatDetails() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_chat_request_layout);
        dialog.setCancelable(false);
        TextView startChat = dialog.findViewById(R.id.startChat);
        TextView cancel = dialog.findViewById(R.id.cancel);
        dialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
            }
        });
        startChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        dialog.setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onDestroy() {
        try {
            if (dialog != null) {
                dialog.dismiss();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}