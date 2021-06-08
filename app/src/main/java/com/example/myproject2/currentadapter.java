package com.example.myproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class currentadapter extends RecyclerView.Adapter<currentadapter.MyViewHolder>{
    Context context;
    ArrayList<Current> currentadapterList;

    public currentadapter(Context context, ArrayList<Current> currentadapterList) {
        this.context = context;
        this.currentadapterList = currentadapterList;
    }


    @NonNull
    @Override
    public currentadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.currentlist, parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull currentadapter.MyViewHolder holder, int position) {
        Current current = currentadapterList.get(position);
        holder.txt1.setText(current.getDate());
        holder.txt2.setText(current.getId());
        holder.txt3.setText(current.getNo());
        holder.txt4.setText(current.getTime());
        holder.txt5.setText(current.getType());
    }

    @Override
    public int getItemCount() {
        return currentadapterList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        EditText txt1, txt2, txt3, txt4, txt5;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.text1);
            txt2 = itemView.findViewById(R.id.text2);
            txt3 = itemView.findViewById(R.id.text3);
            txt4 = itemView.findViewById(R.id.text4);
            txt5 = itemView.findViewById(R.id.text5);
            txt1.setEnabled(false);
            txt2.setEnabled(false);
            txt3.setEnabled(false);
            txt4.setEnabled(false);
            txt5.setEnabled(false);
        }
    }
}
