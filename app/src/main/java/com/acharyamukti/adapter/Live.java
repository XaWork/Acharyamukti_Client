package com.acharyamukti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.R;
import com.acharyamukti.model.ImageModel;

import java.util.List;

public class Live extends RecyclerView.Adapter<Live.ViewHolder> {
    Context context;
    List<ImageModel> imageModels;

    public Live(Context context, List<ImageModel> imageModels) {
        this.context = context;
        this.imageModels = imageModels;
    }

    @NonNull
    @Override
    public Live.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_profile_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Live.ViewHolder holder, int position) {

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
