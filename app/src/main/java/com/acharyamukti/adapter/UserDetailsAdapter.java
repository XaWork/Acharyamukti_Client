package com.acharyamukti.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.R;
import com.acharyamukti.activity.AstrologerProfile;
import com.acharyamukti.chat.UserDetailsForm;
import com.acharyamukti.helper.Backend;
import com.acharyamukti.model.AstroProfileModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.ViewHolder> {
    Context context;
    List<AstroProfileModel> astroProfileModels;

    @SuppressLint("NotifyDataSetChanged")
    public UserDetailsAdapter(Context context, List<AstroProfileModel> astroProfileModels) {
        this.context = context;
        this.astroProfileModels = astroProfileModels;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_user_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AstroProfileModel model = astroProfileModels.get(position);
        holder.profile_name.setText(model.getName());
        holder.vedic.setText(model.getAsttype());
        holder.charge.setText(model.getCallrate());
        holder.exp.setText(model.getExperience());
        String reg_id = model.getReg_id();
        Backend.getInstance(context).saveReg_id(reg_id);
        String rating = model.getAvgrating1();
        holder.ratingBar.setText(rating);
//        Float f = Float.parseFloat(rating);
//        holder.ratingBar.setRating(f);
        Picasso.with(context).load(model.getImage()).into(holder.imageView);
        if (model.getStatus().equals("Online")) {
            holder.call.setBackgroundResource(R.drawable.call);
        } else {
            holder.call.setBackgroundResource(R.drawable.redicon);
        }
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, AstrologerProfile.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("reg_id", reg_id);
            context.startActivity(intent);
        });
        holder.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.getStatus().equals("Online")) {
                    Intent intent = new Intent(context, UserDetailsForm.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return astroProfileModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView profile_name, charge, vedic,exp;
        ImageView imageView,call,chat;
        TextView ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_name = itemView.findViewById(R.id.ProfileName);
            charge = itemView.findViewById(R.id.chargeOnline);
            vedic = itemView.findViewById(R.id.exptz);
            call = itemView.findViewById(R.id.call);
            exp = itemView.findViewById(R.id.exp);
            chat=itemView.findViewById(R.id.chat);
            imageView = itemView.findViewById(R.id.imageViewAstro);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
