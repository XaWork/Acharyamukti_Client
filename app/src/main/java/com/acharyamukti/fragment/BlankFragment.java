package com.acharyamukti.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.activity.LoginActivity;
import com.acharyamukti.activity.Register;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.model.DataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BlankFragment extends Fragment implements View.OnClickListener {
    Button login;
    EditText emailId, pass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        login = view.findViewById(R.id.btnLogin);
        emailId = view.findViewById(R.id.etEmail);
        pass = view.findViewById(R.id.etPassword);
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
                userLogin();
                break;
        }

    }

    private void userLogin() {
        String email = emailId.getText().toString();
        String password = pass.getText().toString();
        if (email.isEmpty()) {
            emailId.requestFocus();
            emailId.setError("Please Enter Email Id");
            return;
        }
        if (password.isEmpty()) {
            pass.requestFocus();
            pass.setError("Please Enter Password");
            return;
        }
        Call<DataModel> call = RetrofitClient
                .getInstance()
                .getApi()
                .login(email, password);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}