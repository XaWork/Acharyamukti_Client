package com.acharyamukti.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.acharyamukti.R;
import com.acharyamukti.activity.ArcheryLive;
import com.acharyamukti.adapter.ImageSliderAdapter;
import com.acharyamukti.adapter.ProfileAdapter;
import com.acharyamukti.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;
    Toolbar toolbar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        int image[] = {R.drawable.sliderimagegrils, R.drawable.sliderbanner1, R.drawable.bannerimage};
        ImageSliderAdapter imageSliderAdapter;
        ViewPager viewPager = root.findViewById(R.id.viewpager);
        imageSliderAdapter = new ImageSliderAdapter(getContext(), image);
        viewPager.setAdapter(imageSliderAdapter);
        ProfileAdapter profileAdapter;
        RecyclerView recyclerView;
        recyclerView = root.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        profileAdapter = new ProfileAdapter(getContext());
        recyclerView.setAdapter(profileAdapter);
        Button viewAll = root.findViewById(R.id.viewAll);
        viewAll.setOnClickListener(this);
        recyclerView.setHasFixedSize(true);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), ArcheryLive.class);
        startActivity(intent);
    }
}
