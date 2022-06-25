package com.acharyamukti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.acharyamukti.R;
import com.acharyamukti.model.ImageModel;
import com.bumptech.glide.Glide;
import java.util.List;


public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {
    Context context;
    private final List<ImageModel>imageModels;
    private final int layoutResourceId;


    public LiveAdapter(Context context, int layoutResourceId, List<ImageModel> imageModels) {
        this.context = context;
        this.imageModels = imageModels;
        this.layoutResourceId = layoutResourceId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LiveAdapter.ViewHolder(LayoutInflater.from(context).inflate(layoutResourceId, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LiveAdapter.ViewHolder holder, int position) {
        ImageModel data = imageModels.get(position);
        holder.txtName.setText(data.getHoroscop_name());
        Glide.with(context).load(data.getHoroscop_icon()).into(holder.profile_Image);
    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_Image;
        TextView txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_Image = itemView.findViewById(R.id.profile_Image);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }

}
