package com.acharyamukti.fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.activity.DashBoard;
import com.acharyamukti.activity.Login;
import com.acharyamukti.activity.Register;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.DataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EmailLogin extends Fragment implements View.OnClickListener {
    Button login;
    EditText emailId, pass;
    TextView forgotPass, etEmail;
    Dialog dialog;

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
        forgotPass = view.findViewById(R.id.forgotPass);
        forgotPass.setOnClickListener(this);
        return view;
    }

    private void dialog() {
         dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_forgot_pass);
        ImageView cancelImage = dialog.findViewById(R.id.cancelImage);
        cancelImage.setOnClickListener(view -> dialog.dismiss());
        etEmail = dialog.findViewById(R.id.etEmail);
        String sendUrl = etEmail.getText().toString();
        Button send = dialog.findViewById(R.id.btnSendLink);
        send.setOnClickListener(view ->
        {
            if (sendUrl == null) {
                Toast.makeText(getActivity(), "Please Enter the email Id", Toast.LENGTH_SHORT).show();
            } else {
                forgotPass(sendUrl);
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    private void getEmailDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_check_mail);
        ImageView close = dialog.findViewById(R.id.close);
        close.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);


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
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Login.PRES_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("hasLoggedIn", true);
                editor.commit();
                userLogin();
                break;
            case R.id.forgotPass:
                dialog();
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

    private void forgotPass(String sendUrl) {
        Call<DataModel> call = RetrofitClient.getInstance().getApi().postPasswordLink(sendUrl);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                DataModel dataModel = response.body();
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (dataModel.getError().equals("false")) {
                        getEmailDialog();
                        Toast.makeText(getContext(), "Check your mail for forgot password link", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        getEmailDialog();
                    }
                }
            }
            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}