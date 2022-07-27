package com.acharyamukti.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.acharyamukti.R;
import com.acharyamukti.fragment.Free;
import com.acharyamukti.fragment.Profile;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.ui.gallery.GalleryFragment;
import com.acharyamukti.ui.home.HomeFragment;
import com.acharyamukti.ui.slideshow.SlideshowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.acharyamukti.databinding.ActivityDashBoardBinding;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    Dialog dialog;
    LinearLayout walletLayout;
    TextView walletAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.acharyamukti.databinding.ActivityDashBoardBinding binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarDashBoard.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dash_board);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");
        walletLayout = findViewById(R.id.walletLayout);
        walletLayout.setOnClickListener(this);
        walletAmount = findViewById(R.id.walletAmount);
        String totalBalance = Backend.getInstance(this).getWalletBalance();
        walletAmount.setText(totalBalance);
        getDialog();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // close your dialog
            dialog.dismiss();
        }, 10000);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    public void getDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_dialog_layout);
        ImageView cancel = dialog.findViewById(R.id.cancel);
        TextView dialog_name = dialog.findViewById(R.id.dialog_name);
        String name = Backend.getInstance(this).getName();
        dialog_name.setText(name);
        cancel.setOnClickListener(view -> dialog.dismiss());
        Button claim = dialog.findViewById(R.id.claim);
        claim.setOnClickListener(view -> {
            Intent claim1 = new Intent(getApplicationContext(), Wallet.class);
            startActivity(claim1);
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dash_board);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itemHome:
                toolbar.setTitle("Home");
                HomeFragment fragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout_dash, fragment, "");
                fragmentTransaction.commit();
                break;
            case R.id.itemFree:
                toolbar.setTitle("Free");
                Free fragment1 = new Free();
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.frameLayout_dash, fragment1, "");
                fragmentTransaction1.commit();
                break;
            case R.id.itemProfile:
                toolbar.setTitle("Profile");
                Profile fragment2 = new Profile();
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.frameLayout_dash, fragment2, "");
                fragmentTransaction2.commit();
                break;
            case R.id.nav_gallery:
//                Intent about = new Intent(getApplicationContext(), TermAndCondition.class);
//                startActivity(about);
                toolbar.setTitle("About us");
                GalleryFragment fragment3 = new GalleryFragment();
                FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.frameLayout_dash, fragment3, "");
                fragmentTransaction3.commit();
                break;
            case R.id.nav_horoscope:
                Intent intent = new Intent(this, Horoscope.class);
                startActivity(intent);
                break;
            case R.id.nav_slideshow:
                toolbar.setTitle("Call history");
                SlideshowFragment fragment4 = new SlideshowFragment();
                FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction4.replace(R.id.frameLayout_dash, fragment4, "");
                fragmentTransaction4.commit();
                break;
            case R.id.can_ask:
                Intent ask = new Intent(this, WhatCanAsk.class);
                startActivity(ask);
                break;
            case R.id.nav_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Wallet.class);
        startActivity(intent);
    }
}