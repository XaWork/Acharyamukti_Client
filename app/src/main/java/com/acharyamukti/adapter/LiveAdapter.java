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
import com.acharyamukti.model.AstroProfileModel;
import com.squareup.picasso.Picasso;
import java.util.List;

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {
    Context context;
    List<AstroProfileModel> astroProfileModels;

    public LiveAdapter(Context context, List<AstroProfileModel> astroProfileModels) {
        this.context = context;
        this.astroProfileModels = astroProfileModels;
    }

    @NonNull
    @Override
    public LiveAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_profile_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LiveAdapter.ViewHolder holder, int position) {
        AstroProfileModel astro = astroProfileModels.get(position);
        holder.status.setText(astro.getStatus());
        holder.name.setText(astro.getName());
        Picasso.with(context).load(astro.getImage()).into(holder.profile_Image);

    }

    @Override
    public int getItemCount() {
        return astroProfileModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_Image;
        TextView name, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_Image = itemView.findViewById(R.id.profile_Image1);
            name = itemView.findViewById(R.id.name1);
            status = itemView.findViewById(R.id.status1);
        }
    }
}
