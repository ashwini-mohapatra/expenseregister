package com.ashwini.expenseregister.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashwini.expenseregister.Model.TransactionModel.TransactionShowModel;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    ArrayList<TransactionShowModel> tsm;
    Context context;

    public TransactionAdapter(ArrayList<TransactionShowModel> tsm, Context context) {
        this.tsm = tsm;
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tsm.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
