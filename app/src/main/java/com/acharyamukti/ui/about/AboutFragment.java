package com.acharyamukti.ui.about;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.adapter.TransactionLogAdapter;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.CallingModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AboutFragment extends Fragment {
    RecyclerView recyclerviewTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        recyclerviewTransaction = view.findViewById(R.id.recyclerviewTransaction);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        recyclerviewTransaction.setLayoutManager(layout);
        getTransaction();
        return view;

    }

    private void getTransaction() {
        List<CallingModel> callingModels = new ArrayList<>();
        String userid = Backend.getInstance(getActivity()).getUserId();
        String url = "https://theacharyamukti.com/clientapi/pack-history.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        @SuppressLint("NotifyDataSetChanged") StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("body");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jb = jsonArray.getJSONObject(i);
                    CallingModel callingModel = new CallingModel(
                            jb.getString("call_pack"),
                            jb.getString("amount_paid"),
                            jb.getString("amount_credited"),
                            jb.getString("orderID"),
                            jb.getString("order_date"));
                    callingModels.add(callingModel);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            TransactionLogAdapter logAdapter = new TransactionLogAdapter(getActivity(), callingModels);
            logAdapter.notifyDataSetChanged();
            recyclerviewTransaction.setAdapter(logAdapter);
            recyclerviewTransaction.setNestedScrollingEnabled(false);
        }, error -> Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userid);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}