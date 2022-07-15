package com.acharyamukti.adapter;

import android.content.Context;
import android.content.Intent;
import android.hardware.lights.LightState;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.R;
import com.acharyamukti.activity.AstrologerProfile;
import com.acharyamukti.model.AstroProfileModel;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AstroProfile extends RecyclerView.Adapter<AstroProfile.ViewHolder> {
    Context context;
    List<AstroProfileModel> astroProfileModels;

    public AstroProfile(Context context, List<AstroProfileModel> astroProfileModels) {
        this.context = context;
        this.astroProfileModels = astroProfileModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_custom_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AstroProfileModel astroModel = astroProfileModels.get(position);
        holder.name.setText(astroModel.getName());
        holder.charge.setText(astroModel.getCallrate());
        Picasso.with(context).load(astroModel.getImage()).into(holder.circleImageView);

    }

    @Override
    public int getItemCount() {
        return astroProfileModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView name, charge;
        TextView connect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.astroImage);
            name = itemView.findViewById(R.id.profileName);
            charge = itemView.findViewById(R.id.charge);
            connect = itemView.findViewById(R.id.connect);
            connect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AstrologerProfile.class);
                    context.startActivity(intent);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
            });
        }
    }
}
