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

import com.acharyamukti.R;
import com.acharyamukti.activity.Login;
import com.acharyamukti.activity.ProfileUpdate;
import com.acharyamukti.activity.Wallet;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.ui.slideshow.SlideshowFragment;


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
        profileName = Backend.getInstance(getContext()).getName();
        emailId = Backend.getInstance(getContext()).getEmail();
        mobileNumber = Backend.getInstance(getContext()).getMobile();
        name.setText(profileName);
        email.setText(emailId);
        mobile.setText(mobileNumber);
        txtEditImage.setOnClickListener(this);
//        logout = view.findViewById(R.id.logout);
//        wallet = view.findViewById(R.id.txtWallet);
//        history = view.findViewById(R.id.txtConsultation);
//        history.setOnClickListener(this);
//        wallet.setOnClickListener(this);
//        logout.setOnClickListener(this);
        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.txtWallet:
//                Intent intent = new Intent(getContext(), Wallet.class);
//                startActivity(intent);
//                break;
//            case R.id.txtEditImage:
//                Intent image = new Intent(getContext(), ProfileUpdate.class);
//                startActivity(image);
//                break;
//            case R.id.logout:
//                email.setText("");
//                name.setText("");
//                mobile.setText("");
//                Intent login_screen = new Intent(getContext(), Login.class);
//                login_screen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(login_screen);
//                break;
//            case R.id.txtConsultation:
//                SlideshowFragment fragment4 = new SlideshowFragment();
//                FragmentTransaction fragmentTransaction4 = getParentFragmentManager().beginTransaction();
//                fragmentTransaction4.replace(R.id.profile_fragment, fragment4, "");
//                fragmentTransaction4.commit();
//                break;
        }

    }
}