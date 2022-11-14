package com.acharyamukti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.acharyamukti.R;
import com.acharyamukti.model.CallingModel;
import java.util.List;


public class CallAdapter extends RecyclerView.Adapter<CallAdapter.ViewHolder> {
    Context context;
    List<CallingModel> calling;

    public CallAdapter(Context context, List<CallingModel> calling) {
        this.context = context;
        this.calling = calling;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_calling_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CallingModel call = calling.get(position);
        holder.name.setText(call.getName());
        holder.call_duration.setText(call.getDuration());
       // holder.minute.setText(call.getMinute());
        holder.call_date.setText(call.getCalldate());
        holder.call_rate.setText(call.getCallrat());
        holder.amount.setText(call.getCallamount());
    }

    @Override
    public int getItemCount() {
        return calling.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, call_rate, call_duration, call_date, amount, minute;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.call_name);
            call_rate = itemView.findViewById(R.id.call_rate);
            call_duration = itemView.findViewById(R.id.call_duration);
            call_date = itemView.findViewById(R.id.call_date);
            amount = itemView.findViewById(R.id.call_amount);
            //minute = itemView.findViewById(R.id.minutes);
        }
    }
}
