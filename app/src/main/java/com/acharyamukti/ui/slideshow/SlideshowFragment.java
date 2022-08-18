package com.acharyamukti.ui.slideshow;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.R;
import com.acharyamukti.adapter.CallAdapter;
import com.acharyamukti.databinding.FragmentSlideshowBinding;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.CallingModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SlideshowFragment extends Fragment {
    RecyclerView rvCallHistory;
    CallAdapter callAdapter;
    List<CallingModel> calling = new ArrayList<>();
    FragmentSlideshowBinding binding;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        rvCallHistory = binding.recyclerViewCallHistory;
        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        rvCallHistory.setLayoutManager(linear);
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        getCallData();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getCallData() {
        String userid = Backend.getInstance(getActivity()).getUserId();
        String url = "https://theacharyamukti.com/clientapi/call-history.php";
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        @SuppressLint("NotifyDataSetChanged") StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            progressBar.setVisibility(View.INVISIBLE);
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray arr = obj.getJSONArray("body");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jb = arr.getJSONObject(i);
                    CallingModel call = new CallingModel(
                            jb.getString("name"),
                            jb.getString("duration"),
                            jb.getString("minute"),
                            jb.getString("callrat"),
                            jb.getString("callamount"),
                            jb.optString("calldate")
                    );
                    calling.add(call);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            callAdapter = new CallAdapter(getActivity(), calling);
            callAdapter.notifyDataSetChanged();
            rvCallHistory.setAdapter(callAdapter);
        }, error -> {
            progressBar.setVisibility(View.INVISIBLE);
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("reg_id", userid);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}