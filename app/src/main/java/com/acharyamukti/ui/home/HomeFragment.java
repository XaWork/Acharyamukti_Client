package com.acharyamukti.ui.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.R;
import com.acharyamukti.activity.KundaliniMarriage;
import com.acharyamukti.activity.Horoscope;
import com.acharyamukti.adapter.AstroProfileAdapter;
import com.acharyamukti.adapter.LiveAdapter;
import com.acharyamukti.adapter.NewsAdapter;
import com.acharyamukti.api.RetrofitClient;
import com.acharyamukti.databinding.FragmentHomeBinding;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.AstroProfileModel;
import com.acharyamukti.model.BlogModel;
import com.acharyamukti.model.DataModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements View.OnClickListener {
    LinearLayout relationShip, marriage, career, money, horoscope;
    FragmentHomeBinding binding;
    LiveAdapter liveAdapter;
    AstroProfileAdapter astroProfile;
    RecyclerView recyclerView, recyclerView1, recyclerViewBlogDetails;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    TextView viewAll, viewAll2;
    List<BlogModel> blogModels = new ArrayList<>();
    NewsAdapter newsAdapter;
    TextView feedback;
    Button sendFeedback;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        homeViewModel.getText().
                observe(getViewLifecycleOwner(),
                        textView::setText);
        viewAll = root.findViewById(R.id.viewAll);
        viewAll.setOnClickListener(this);
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
        feedback = root.findViewById(R.id.feedback);
        sendFeedback = root.findViewById(R.id.sendFeedback);
        sendFeedback.setOnClickListener(this);
        getProfileData();
        getLiveData();
        recyclerViewData(root);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void messageAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Notice");
        builder.setMessage("Launching this missile will destroy the entire universe. Is this what you intended to do?");
        builder.setPositiveButton("Launch missile", null);
        builder.setNeutralButton("Remind me later", null);
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void recyclerViewData(View root) {
        recyclerView = root.findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getContext(), 1,
                GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView1 = root.findViewById(R.id.recyclerViewLive);
        linearLayoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerViewBlogDetails = root.findViewById(R.id.recyclerViewBlogDetails);
        GridLayoutManager linearLayoutManager =
                new GridLayoutManager(getContext(), 1,
                        RecyclerView.HORIZONTAL, false);
        recyclerViewBlogDetails.setLayoutManager(linearLayoutManager);
        recyclerViewBlogDetails.setNestedScrollingEnabled(false);
        getBloData();
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
            case R.id.sendFeedback:
                postFeedBackData();
                break;
        }
    }

    private void getProfileData() {
        final List<AstroProfileModel> astroProfileModels = new ArrayList<>();
        String url = "https://theacharyamukti.com/clientapi/online-astro.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("body");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jb = jsonArray.getJSONObject(i);
                    AstroProfileModel astro = new AstroProfileModel(
                            jb.getString("image"),
                            jb.getString("name"),
                            jb.getString("reg_id"),
                            jb.getString("experience"),
                            jb.getString("callrate"),
                            jb.getString("language"),
                            jb.getString("asttype"),
                            jb.getString("avgrating1"));
                    astroProfileModels.add(astro);
                }
                astroProfile = new AstroProfileAdapter(getContext(), astroProfileModels);
                astroProfile.notifyDataSetChanged();
                recyclerView1.setAdapter(astroProfile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error ->
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show());
        requestQueue.add(objectRequest);
    }

    private void getLiveData() {
        final List<AstroProfileModel> astroProfileModels = new ArrayList<>();
        String url = "https://theacharyamukti.com/clientapi/online-astro.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray arr = obj.getJSONArray("body");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jb = arr.getJSONObject(i);
                    AstroProfileModel astro = new AstroProfileModel(
                            jb.getString("image"),
                            jb.getString("status"),
                            jb.getString("name"),
                            jb.getString("reg_id"),
                            jb.getString("experience"),
                            jb.getString("callrate"),
                            jb.getString("language"),
                            jb.getString("asttype"),
                            jb.getString("avgrating1"));
                    astroProfileModels.add(astro);
                }
                liveAdapter = new LiveAdapter(getActivity(), astroProfileModels);
                recyclerView.setAdapter(liveAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(request);
    }

    private void getBloData() {
        String url = "https://theacharyamukti.com/clientapi/blog.php";
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        @SuppressLint("NotifyDataSetChanged") StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("body");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jb = jsonArray.getJSONObject(i);
                    BlogModel blogModel = new BlogModel(
                            jb.getString("date"),
                            jb.getString("name"),
                            jb.getString("blog_id"),
                            jb.getString("description"),
                            jb.getString("image")
                    );
                    blogModels.add(blogModel);
                }
                newsAdapter = new NewsAdapter(getContext(), R.layout.lasted_custom_blog, blogModels);
                newsAdapter.notifyDataSetChanged();
                recyclerViewBlogDetails.setAdapter(newsAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show());
        requestQueue.add(stringRequest);
    }

    private void postFeedBackData() {
        String content = feedback.getText().toString();
        String userId = Backend.getInstance(getContext()).getUserId();
        Call<DataModel> call = RetrofitClient.getInstance().getApi().postFeedBack(userId, content);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                DataModel dataModel = response.body();
                if (response.isSuccessful()) {
                    if (dataModel.getError().equals("false")) {
                        Toast.makeText(getContext(), dataModel.getMessage(), Toast.LENGTH_SHORT).show();
                        messageAlert();
                    }
                } else {
                    Toast.makeText(getActivity(), dataModel.getError(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
