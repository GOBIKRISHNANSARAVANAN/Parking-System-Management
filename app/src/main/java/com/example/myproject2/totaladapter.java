package com.example.myproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class totaladapter extends RecyclerView.Adapter<totaladapter.MyViewHolder>{

    Context context;
    ArrayList<total> totaladapterList;
    public totaladapter(Context context, ArrayList<total> totaladapterList) {
        this.context = context;
        this.totaladapterList = totaladapterList;
    }
    @NonNull
    @Override
    public totaladapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.totallist, parent, false);
        return new totaladapter.MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull totaladapter.MyViewHolder holder, int position) {
        total total = totaladapterList.get(position);
        holder.txt1.setText(total.getDatein());
        holder.txt2.setText(total.getSlotno());
        holder.txt3.setText(total.getVehicleno());
        holder.txt4.setText(total.getTimein());
        holder.txt5.setText(total.getType());
        holder.txt6.setText(total.getDateandtimeout());
    }

    @Override
    public int getItemCount() {
        return totaladapterList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        EditText txt1, txt2, txt3, txt4, txt5,txt6;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.text1);
            txt2 = itemView.findViewById(R.id.text2);
            txt3 = itemView.findViewById(R.id.text3);
            txt4 = itemView.findViewById(R.id.text4);
            txt5 = itemView.findViewById(R.id.text5);
            txt6=itemView.findViewById(R.id.text6);
            txt1.setEnabled(false);
            txt2.setEnabled(false);
            txt3.setEnabled(false);
            txt4.setEnabled(false);
            txt5.setEnabled(false);
            txt6.setEnabled(false);
        }
    }

}
