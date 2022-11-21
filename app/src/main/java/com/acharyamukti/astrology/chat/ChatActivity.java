package com.acharyamukti.astrology.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.acharyamukti.R;
import com.acharyamukti.astrology.activity.KundaliniMarriage;


public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView chat_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chat_end = findViewById(R.id.end_chat);
        chat_end.setOnClickListener(this);
        Toolbar toolbar=findViewById(R.id.toolbarChat);
        ActionBar actionBar=getSupportActionBar();
        setSupportActionBar(toolbar);
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
        dialog.setContentView(R.layout.dialog_chat_end_layout);
        dialog.setCancelable(false);
        TextView endChat = dialog.findViewById(R.id.end);
        TextView continueChat = dialog.findViewById(R.id.cont_chat);
        dialog.show();
        endChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), KundaliniMarriage.class);
                startActivity(intent);
            }
        });
        continueChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        dialog.setCanceledOnTouchOutside(true);
    }
}