package com.acharyamukti.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.acharyamukti.R;
import com.acharyamukti.activity.Login;
import com.acharyamukti.activity.ProfileUpdate;
import com.acharyamukti.helper.Backend;


public class Profile extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView txtEditImage = view.findViewById(R.id.txtEditImage);
        TextView name = view.findViewById(R.id.profileUsername);
        TextView email = view.findViewById(R.id.txtEmail);
        TextView mobile = view.findViewById(R.id.txtMobile);
        String profileName = Backend.getInstance(getContext()).getName();
        String emailId = Backend.getInstance(getContext()).getEmail();
        String mobileNumber = Backend.getInstance(getContext()).getMobile();
        name.setText(profileName);
        email.setText(emailId);
        mobile.setText(mobileNumber);
        txtEditImage.setOnClickListener(this);
        TextView logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), ProfileUpdate.class);
        startActivity(intent);
    }
}