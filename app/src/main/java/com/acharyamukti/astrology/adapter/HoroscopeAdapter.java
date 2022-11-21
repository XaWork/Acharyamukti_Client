package com.acharyamukti.astrology.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.R;
import com.acharyamukti.astrology.model.ImageModel;
import com.bumptech.glide.Glide;

import java.util.List;


public class HoroscopeAdapter extends RecyclerView.Adapter<HoroscopeAdapter.ViewHolder> {
    Context context;
    private final List<ImageModel> imageModels;
    private OnPageItemClickListener onPageItemClickListener;


    public HoroscopeAdapter(Context context, List<ImageModel> imageModels) {
        this.context = context;
        this.imageModels = imageModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_horoscope_icon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoroscopeAdapter.ViewHolder holder, int position) {
        ImageModel data = imageModels.get(position);
        holder.txtName.setText(data.getHoroscop_name());
        Glide.with(context).load(data.getHoroscop_icon()).into(holder.profile_Image);
        holder.itemView.setOnClickListener(view -> HoroscopeAdapter.this.onPageItemClickListener.onPageItemClick(holder.getAdapterPosition(),data.getHoroscop_id()));
    }

    public void setOnPageItemClickListener(OnPageItemClickListener onPageItemClickListener) {
        this.onPageItemClickListener = onPageItemClickListener;
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

    public interface OnPageItemClickListener {
        void onPageItemClick(int position, String title);
    }
}
