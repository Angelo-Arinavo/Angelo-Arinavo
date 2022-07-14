package com.example.codedgo;


import static java.util.Arrays.sort;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;

public class Leaderboards extends AppCompatActivity {

    RecyclerView leaderB;
    DatabaseReference ref;

    Adapter adapter;
    ArrayList <users> list;

    TextView easyl, hardl;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        ref = FirebaseDatabase.getInstance().getReference().child("users");

        leaderB = findViewById(R.id.leaderboardList);
        leaderB.setHasFixedSize(true);
        leaderB.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));

        list = new ArrayList<>();
        adapter = new Adapter(this,list);
        leaderB.setAdapter(adapter);

        easyl = findViewById(R.id.easyList);
        easyl.setTextColor(Color.parseColor("#FFDB58"));

        hardl = findViewById(R.id.hardList);
        hardl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Leaderboards.this, Leaderboard2.class));
                finish();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        ref.orderByChild("easyscore").limitToLast(10).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    users user = dataSnapshot.getValue(users.class);

                    list.add(user);
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });

    }
}