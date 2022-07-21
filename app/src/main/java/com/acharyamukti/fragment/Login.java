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
import com.acharyamukti.activity.DashBoard;
import com.acharyamukti.activity.Register;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.DataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends Fragment implements View.OnClickListener {
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
//        sp = view.getContext().getSharedPreferences("login", MODE_PRIVATE);
//        if (sp.contains("username") && sp.contains("password")) {
//            startActivity(new Intent(getContext(), DashBoard.class));
//        }
        return view;
//    }
//
//    void loginCheck() {
//        if (emailId.getText().toString().equals("programmer") && pass.getText().toString().equals("programmer")) {
//            SharedPreferences.Editor e = sp.edit();
//            e.putString("username", "programmer");
//            e.putString("password", "programmer");
//            e.commit();
//
//            Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_LONG).show();
//            startActivity(new Intent(getContext(), DashBoard.class));
//        } else {
//            Toast.makeText(getContext(), "Incorrect Login Details", Toast.LENGTH_LONG).show();

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
        Call<DataModel> call = RetrofitClient.getInstance().getApi().login(email, password);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                DataModel dataModel = response.body();
                if (response.isSuccessful()) {
                    if (dataModel.getMessage().equals("Login successfull")) {
                        Intent intent1 = new Intent(getActivity(), DashBoard.class);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(getActivity(), "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
                String userId = dataModel.getUserid();
                Backend.getInstance(getActivity()).saveUserId(userId);
            }
            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}