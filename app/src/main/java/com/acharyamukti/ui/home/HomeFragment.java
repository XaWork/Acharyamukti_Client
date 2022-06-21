package com.acharyamukti.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.R;
import com.acharyamukti.activity.KundaliniMarriage;
import com.acharyamukti.activity.Horoscope;
import com.acharyamukti.adapter.DataLiveAdapter;
import com.acharyamukti.adapter.LiveAdapter;
import com.acharyamukti.databinding.FragmentHomeBinding;
import com.acharyamukti.model.NewsModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener {
    LinearLayout relationShip, marriage, career, money, horoscope;
    FragmentHomeBinding binding;
    LiveAdapter liveAdapter;
    DataLiveAdapter dataLiveAdapter;
    RecyclerView recyclerView, recyclerView1;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    TextView viewAll, viewAll2;
    List<NewsModel> newsModels = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        recyclerView = root.findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        liveAdapter = new LiveAdapter(getActivity(), R.layout.custom_profile_layout, newsModels);
        recyclerView.setAdapter(liveAdapter);
        viewAll = root.findViewById(R.id.viewAll);
        viewAll.setOnClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView1 = root.findViewById(R.id.recyclerViewLive);
        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        dataLiveAdapter = new DataLiveAdapter(getActivity());
        recyclerView1.setAdapter(dataLiveAdapter);
        relationShip = root.findViewById(R.id.loveRelationShip);
        relationShip.setOnClickListener(this);
        marriage = root.findViewById(R.id.marriageKundly);
        marriage.setOnClickListener(this);
        career = root.findViewById(R.id.career);
        career.setOnClickListener(this);
        money = root.findViewById(R.id.money);
        money.setOnClickListener(this);
        horoscope = root.findViewById(R.id.horoscope);
        horoscope.setOnClickListener(this);
        viewAll2 = root.findViewById(R.id.viewAll2);
        viewAll2.setOnClickListener(this);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loveRelationShip:
                Intent love = new Intent(getContext(), KundaliniMarriage.class);
                love.putExtra("title", "Love & Relationship");
                startActivity(love);
                break;
            case R.id.marriageKundly:
                Intent marriage = new Intent(getContext(), KundaliniMarriage.class);
                marriage.putExtra("title", "Kundali & Marriage");
                startActivity(marriage);
                break;
            case R.id.career:
                Intent career = new Intent(getContext(), KundaliniMarriage.class);
                career.putExtra("title", "Career");
                startActivity(career);
                break;
            case R.id.money:
                Intent money = new Intent(getContext(), KundaliniMarriage.class);
                money.putExtra("title", "Money & Investments");
                startActivity(money);
                break;
            case R.id.viewAll:
                Intent live = new Intent(getContext(), KundaliniMarriage.class);
                startActivity(live);
                break;
            case R.id.viewAll2:
                Intent viewAll = new Intent(getContext(), KundaliniMarriage.class);
                viewAll.putExtra("title", "");
                startActivity(viewAll);
                break;
            case R.id.horoscope:
                Intent horoscope = new Intent(getContext(), Horoscope.class);
                horoscope.putExtra("title", "horoscope");
                startActivity(horoscope);
                break;
        }

    }
}
