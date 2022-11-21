package com.acharyamukti.astrology.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.R;
import com.acharyamukti.astrology.model.CallingModel;

import java.util.List;


public class TransactionLogAdapter extends RecyclerView.Adapter<TransactionLogAdapter.ViewHolder> {
    Context context;
    List<CallingModel> callingModel;

    public TransactionLogAdapter(Context context, List<CallingModel> callingModels) {
        this.context = context;
        this.callingModel = callingModels;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_log_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CallingModel call = callingModel.get(position);
        holder.orderId.setText(call.getOrderID());
        holder.amount_credit.setText(call.getAmount_credited());
        holder.amount_paid.setText(call.getAmount_paid());
        holder.call_pack.setText(call.getCall_pack());
        holder.oder_date.setText(call.getOrder_date());
    }

    @Override
    public int getItemCount() {
        return callingModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, oder_date, amount_paid, amount_credit, call_pack;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            oder_date = itemView.findViewById(R.id.order_date);
            amount_paid = itemView.findViewById(R.id.amount_paid);
            amount_credit = itemView.findViewById(R.id.amount_credit);
            call_pack = itemView.findViewById(R.id.call_pack);
        }
    }
}
