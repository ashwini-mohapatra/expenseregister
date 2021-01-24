package com.ashwini.expenseregister.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashwini.expenseregister.Model.TransactionModel.TransactionShowModel;
import com.ashwini.expenseregister.R;

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
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.transaction_layout , parent,false );
        MyViewHolder viewHolder = new MyViewHolder(  view );
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.MyViewHolder holder, int position) {
        holder.t1.setText(tsm.get(position).getTransaction_type());
        holder.t2.setText(String.valueOf(tsm.get(position).getTransaction_amt()));
        holder.t3.setText(tsm.get(position).getTransaction_name());
        holder.t4.setText(String.valueOf(tsm.get(position).getTransaction_desc()));
        holder.t5.setText(tsm.get(position).getTransaction_date());
    }

    @Override
    public int getItemCount() {
        return tsm.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.textView4);
            t2=itemView.findViewById(R.id.textView5);
            t3=itemView.findViewById(R.id.textView2);
            t4=itemView.findViewById(R.id.textView3);
            t5=itemView.findViewById(R.id.textView6);
        }
    }
}
