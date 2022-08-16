package com.acharyamukti.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.activity.Login;
import com.acharyamukti.activity.ProfileUpdate;
import com.acharyamukti.activity.Wallet;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.DataModel;
import com.acharyamukti.ui.slideshow.SlideshowFragment;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profile extends Fragment implements View.OnClickListener {
    TextView wallet, history, payment, about,
            profile, name, email, mobile, logout;
    String profileName, emailId, mobileNumber;
    ImageView txtEditImage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        txtEditImage = view.findViewById(R.id.txtEditImage);
        name = view.findViewById(R.id.profileUsername);
        email = view.findViewById(R.id.txtEmail);
        mobile = view.findViewById(R.id.txtMobile);
        name.setText(profileName);
        email.setText(emailId);
        mobile.setText(mobileNumber);
        txtEditImage.setOnClickListener(this);
        viewProfile();
        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent image = new Intent(getContext(), ProfileUpdate.class);
        startActivity(image);
    }

    private void viewProfile() {
        String user_id = Backend.getInstance(getContext()).getUserId();
        Call<DataModel> call = RetrofitClient.getInstance().getApi().viewProfile(user_id);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                DataModel dataModel = response.body();
                if (response.isSuccessful()) {
                    name.setText(Objects.requireNonNull(dataModel).getName());
                    email.setText(dataModel.getEmail());
                    mobile.setText(dataModel.getMobile());
                } else {
                    Toast.makeText(getContext(), Objects.requireNonNull(dataModel).getError(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}