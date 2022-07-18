package com.acharyamukti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.R;
import com.acharyamukti.model.ReviewModel;

import java.util.List;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context context;
    List<ReviewModel> reviewModels;

    public ReviewAdapter(Context context, List<ReviewModel> reviewModels) {
        this.context = context;
        this.reviewModels = reviewModels;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_review_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        ReviewModel model=reviewModels.get(position);
        holder.userName.setText(model.getUsernme());
        holder.rating.setText(model.getRating());
        holder.date.setText(model.getDate());
        holder.comment.setText(model.getCommente());
    }

    @Override
    public int getItemCount() {
        return reviewModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView userName;
        private final TextView rating;
        private final TextView comment;
        private final TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName=itemView.findViewById(R.id.userName);
            rating=itemView.findViewById(R.id.extRating);
            comment=itemView.findViewById(R.id.comment);
            date=itemView.findViewById(R.id.date);
        }
    }
}
