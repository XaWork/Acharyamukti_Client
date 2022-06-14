package com.acharyamukti.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.acharyamukti.R;
import com.acharyamukti.activity.LoginActivity;
import com.acharyamukti.activity.Register;


public class BlankFragment extends Fragment implements View.OnClickListener {
    Button login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        login = view.findViewById(R.id.btnLogin);
        login.setOnClickListener(this);
        Button signup = view.findViewById(R.id.signup);
        signup.setOnClickListener(this);
        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup:
                Intent intent = new Intent(getActivity(), Register.class);
                startActivity(intent);
                break;
            case R.id.btnLogin:
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
        }

    }
}