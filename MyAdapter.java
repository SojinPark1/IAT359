package com.example.assignment2final;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public ArrayList<String> list;
    Context context;

    public MyAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        //WL. SHOTS, Saves
        String[]  results = (list.get(position).toString()).split(",");
        holder.textWinLoss.setText(results[0]);
        holder.textViewShots.setText(results[1]);
        holder.textViewSaves.setText(results[2]);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TextView textWinLoss;
        public TextView textViewSaves;
        public TextView textViewShots;

        public LinearLayout myLayout;

        Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            myLayout = (LinearLayout) itemView;

            textWinLoss = (TextView) itemView.findViewById(R.id.textWinLoss);
            textViewSaves = (TextView) itemView.findViewById(R.id.textViewSaves);
            textViewShots = (TextView) itemView.findViewById(R.id.textViewShots);

            itemView.setOnClickListener(this);
            context = itemView.getContext();

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,
                    "You have clicked " + ((TextView)view.findViewById(R.id.textWinLoss)).getText().toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
