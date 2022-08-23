package com.acharyamukti.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.R;
import com.acharyamukti.activity.BlogDetails;
import com.acharyamukti.model.BlogModel;
import com.bumptech.glide.Glide;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final Context context;
    private final int layoutResourceId;
    List<BlogModel> blogModels;
    private OnItemClickListener onItemClickListener;

    public NewsAdapter(Context context, int layoutResourceId, List<BlogModel> blogModels) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.blogModels = blogModels;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(layoutResourceId, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        BlogModel blogModel = blogModels.get(position);
       // holder.titleView.setText(blogModel.getName());
        holder.subTitle.setText(blogModel.getDescription());
        holder.date.setText(blogModel.getDate());
        String url = "https://theacharyamukti.com/image/product/" + blogModel.getImage();
        Glide.with(context).load(url).into(holder.imageView);
        Log.d("image_url",url);
        holder.itemView.setOnClickListener(view -> {
   //         NewsAdapter.this.onItemClickListener.onItemClick(holder.getAdapterPosition(), blogModel);
            Intent intent = new Intent(context, BlogDetails.class);
            intent.putExtra("blog_id", blogModel.getBlog_id());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return blogModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView, subTitle, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.blog_image);
            titleView = itemView.findViewById(R.id.txtTitle);
            date = itemView.findViewById(R.id.txtDate);
            subTitle = itemView.findViewById(R.id.subTitle);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, BlogModel blogModel);

    }
}
