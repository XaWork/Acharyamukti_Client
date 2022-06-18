package com.acharyamukti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.acharyamukti.model.NewsModel;
import java.util.List;


public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {
    Context context;
    private List<NewsModel> newsModels;
    private int layoutResourceId;


    public LiveAdapter(Context context, int layoutResourceId, List<NewsModel> newsModels) {
        this.context = context;
        this.newsModels = newsModels;
        this.layoutResourceId = layoutResourceId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LiveAdapter.ViewHolder(LayoutInflater.from(context).inflate(layoutResourceId, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LiveAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
