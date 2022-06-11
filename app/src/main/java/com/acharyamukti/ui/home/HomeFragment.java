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
import androidx.viewpager.widget.ViewPager;
import com.acharyamukti.R;
import com.acharyamukti.activity.ArcheryLive;
import com.acharyamukti.activity.KundaliniMarriage;
import com.acharyamukti.adapter.DataLiveAdapter;
import com.acharyamukti.adapter.ImageSliderAdapter;
import com.acharyamukti.adapter.ProfileAdapter;
import com.acharyamukti.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment implements View.OnClickListener {
    LinearLayout relationShip, marriage, career, money, business, everyDayLife;
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        int image[] = {R.drawable.adsimage, R.drawable.sliderbanner1, R.drawable.bannerimage};
//        ImageSliderAdapter imageSliderAdapter;
//        ViewPager viewPager = root.findViewById(R.id.viewpager);
//        imageSliderAdapter = new ImageSliderAdapter(getContext(), image);
//        viewPager.setAdapter(imageSliderAdapter);
        ProfileAdapter profileAdapter;
        RecyclerView recyclerView;
        recyclerView = root.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        profileAdapter = new ProfileAdapter(getContext());
        recyclerView.setAdapter(profileAdapter);
        TextView viewAll = root.findViewById(R.id.viewAll);
        viewAll.setOnClickListener(this);
        recyclerView.setHasFixedSize(true);
        RecyclerView recyclerView1 = root.findViewById(R.id.recyclerViewLive);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        DataLiveAdapter dataLiveAdapter = new DataLiveAdapter(getActivity());
        recyclerView1.setAdapter(dataLiveAdapter);
        relationShip = root.findViewById(R.id.loveRelationShip);
        relationShip.setOnClickListener(this);
        marriage = root.findViewById(R.id.marriageKundly);
        marriage.setOnClickListener(this);
        career = root.findViewById(R.id.career);
        career.setOnClickListener(this);
        money = root.findViewById(R.id.money);
        money.setOnClickListener(this);
//        business = root.findViewById(R.id.business);
//        business.setOnClickListener(this);
//        everyDayLife = root.findViewById(R.id.everyDayLife);
//        everyDayLife.setOnClickListener(this);
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
//            case R.id.business:
//                Intent business = new Intent(getContext(), KundaliniMarriage.class);
//                business.putExtra("title", "Business");
//                startActivity(business);
//                break;
//            case R.id.everyDayLife:
//                Intent every = new Intent(getContext(), KundaliniMarriage.class);
//                every.putExtra("title", "EveryDay Life");
//                startActivity(every);
//                break;
            case R.id.viewAll:
                Intent live = new Intent(getContext(), ArcheryLive.class);
                startActivity(live);
                break;
        }

    }
}
