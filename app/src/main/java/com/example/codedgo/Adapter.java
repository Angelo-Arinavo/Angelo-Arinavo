package com.example.codedgo;

import static java.util.Arrays.sort;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    Context context;

    ArrayList<users> list;

    public Adapter(Context context, ArrayList<users> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.leaderb_user,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        users user = list.get(position);
        holder.username.setText(user.getUsername());
        holder.easyscore.setText(user.getEasyscore().toString());
        holder.hardscore.setText(user.getHardscore().toString());
        holder.rank.setText(String.valueOf(list.size()-position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView username, easyscore, hardscore, rank;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.usernameLB);
            easyscore = itemView.findViewById(R.id.easyScoreLB);
            hardscore = itemView.findViewById(R.id.hardScoreLB);
            rank = itemView.findViewById(R.id.rankLB);


        }


    }


}
