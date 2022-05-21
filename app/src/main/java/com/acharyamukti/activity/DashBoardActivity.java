package com.acharyamukti.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import androidx.appcompat.widget.Toolbar;
import com.acharyamukti.R;
import com.acharyamukti.databinding.ActivityDashBoardBinding;
import com.acharyamukti.fragment.FreeFragment;
import com.acharyamukti.fragment.Profile;
import com.acharyamukti.ui.home.HomeFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


public class DashBoardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemReselectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDashBoardBinding binding;
    private int currentPageBest = 0;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
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
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemReselectedListener(this);
        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dash_board);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemHome:
                toolbar.setTitle("Home");
                HomeFragment fragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout_dash, fragment, "");
                fragmentTransaction.commit();
                break;
            case R.id.itemFree:
                toolbar.setTitle("Free");
                FreeFragment fragment1 = new FreeFragment();
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

        }
    }
}