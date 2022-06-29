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
import com.acharyamukti.model.NewsModel;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final Context context;
    private final int layoutResourceId;
    List<NewsModel> newsModels;
    private OnPageItemClickListener onPageItemClickListener;

    public NewsAdapter(Context context, int layoutResourceId, List<NewsModel> newsModels) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(layoutResourceId, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,int position) {
        //    holder.titleView.setText(newsModel.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NewsModel newsModel=holder.getAdapterPosition();
//                if (onPageItemClickListener != null) {
//                    NewsAdapter.this.onPageItemClickListener.onPageItemClick(newsModels);
//                    notifyDataSetChanged();
//                }

            }
        });
    }

    public void setOnPageItemClickListener(OnPageItemClickListener onPageItemClickListener) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView, subTitle, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.newsImage);
            titleView = itemView.findViewById(R.id.txtTitle);
            date = itemView.findViewById(R.id.txtDate);
            subTitle = itemView.findViewById(R.id.subTitle);
        }
    }

    public interface OnPageItemClickListener {
        void onPageItemClick(String title, NewsModel newsModel);
    }
}
