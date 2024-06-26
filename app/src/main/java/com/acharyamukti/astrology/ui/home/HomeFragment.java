package com.acharyamukti.astrology.ui.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.R;
import com.acharyamukti.astrology.activity.ConsultNow;
import com.acharyamukti.astrology.activity.KundaliniMarriage;
import com.acharyamukti.astrology.activity.Horoscope;
import com.acharyamukti.astrology.adapter.AstroProfileAdapter;
import com.acharyamukti.astrology.adapter.LiveAdapter;
import com.acharyamukti.astrology.adapter.NewsAdapter;
import com.acharyamukti.astrology.api.RetrofitClient;
import com.acharyamukti.astrology.fragment.Free;
import com.acharyamukti.astrology.helper.Backend;
import com.acharyamukti.astrology.model.AstroProfileModel;
import com.acharyamukti.astrology.model.BlogModel;
import com.acharyamukti.astrology.model.DataModel;
import com.acharyamukti.databinding.FragmentHomeBinding;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements View.OnClickListener {
    ImageView relationShip, marriage, career, money, horoscope;
    FragmentHomeBinding binding;
    LiveAdapter liveAdapter;
    AstroProfileAdapter astroProfile;
    RecyclerView recyclerView, recyclerView1, recyclerViewBlogDetails;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    TextView viewAll, viewAll2;
    List<BlogModel> blogModels = new ArrayList<>();
    NewsAdapter newsAdapter;
    TextView feedback, viewAllBlog;
    ImageView consultNow, h_image;
    Button sendFeedback, chat, call;
    Bundle bundle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getProfileData();
        getLiveData();
        recyclerViewData(root);
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
        viewAllBlog = root.findViewById(R.id.viewAllBlog);
        viewAllBlog.setOnClickListener(this);
        consultNow = root.findViewById(R.id.banner1);
        consultNow.setOnClickListener(this);
        // h_image = root.findViewById(R.id.h_image);
        //    Glide.with(getActivity()).load(R.drawable.horoscope1).into(h_image);
//        chat = root.findViewById(R.id.chat_astrologer);
//        call = root.findViewById(R.id.call_astrologer);
//        call.setOnClickListener(this);
//        chat.setOnClickListener(this);
        String blog = "Blog";
        bundle = new Bundle();
        bundle.putString("title", blog);
//        progress = new ProgressDialog(getActivity());
//        progress.setMessage("Please wait....... ");
//        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progress.setIndeterminate(true);
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
            case R.id.viewAllBlog:
                Free fragment4 = new Free();
                FragmentTransaction fragmentTransaction4 = getParentFragmentManager().beginTransaction();
                fragmentTransaction4.replace(R.id.frame_layout_home, fragment4, "");
                fragmentTransaction4.commit();
                fragment4.setArguments(bundle);
                break;
            case R.id.banner1:
                Intent intent = new Intent(getActivity(), ConsultNow.class);
                startActivity(intent);
                break;
//            case R.id.call_astrologer:
//                Intent call = new Intent(getActivity(), KundaliniMarriage.class);
//                startActivity(call);
//                break;
//            case R.id.chat_astrologer:
//                Intent chat = new Intent(getActivity(), KundaliniMarriage.class);
//                chat.putExtra("1", "");
//                startActivity(chat);
//                break;
        }
    }

    private void getProfileData() {
        ProgressDialog progress = ProgressDialog.show(getActivity(), "Data Loading..",
                "Please wait......", true);
        final List<AstroProfileModel> astroProfileModels = new ArrayList<>();
        String url = "https://theacharyamukti.com/clientapi/online-astro.php";
        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("body");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jb = jsonArray.getJSONObject(i);
                    AstroProfileModel astro = new AstroProfileModel(
                            jb.getString("image"),
                            jb.getString("name"),
                            jb.getString("regid"),
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
                progress.dismiss();
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
                            jb.getString("regid"),
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
        ProgressDialog progress = ProgressDialog.show(getActivity(), "Data is Loading..",
                "Please wait......", true);
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
                progress.dismiss();
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

        Log.e("home", "user id : " + userId);

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, @NonNull Response<DataModel> response) {
                DataModel dataModel = response.body();
                if (response.isSuccessful()) {
                    if (dataModel.getError().equals("false")) {
                        Toast.makeText(getActivity(), "Feedback Send Successfully..", Toast.LENGTH_SHORT).show();
                        feedback.setText("");
                        //messageAlert();
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

//    private void getDataProfile() {
//        final List<AstroProfileModel> astroProfileModels = new ArrayList<>();
//        Call<List<AstroProfileModel>> call = RetrofitClient.getInstance().getApi().getProfileData();
//        call.enqueue(new Callback<List<AstroProfileModel>>() {
//            @Override
//            public void onResponse(Call<List<AstroProfileModel>> call, Response<List<AstroProfileModel>> response) {
//                if (response.isSuccessful()) {
//                    astroProfile = new AstroProfileAdapter(getContext(), astroProfileModels);
//                    recyclerView1.setAdapter(astroProfile);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<AstroProfileModel>> call, Throwable t) {
//
//            }
//        });
    //   }

}
