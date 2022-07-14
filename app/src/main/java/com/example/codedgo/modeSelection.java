package com.example.codedgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class modeSelection extends AppCompatActivity {

    Button easy, hard, leaderB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selection);

        easy = findViewById(R.id.easymBtn);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(modeSelection.this, easyMode.class));
            }
        });

        hard = findViewById(R.id.hardmBtn);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(modeSelection.this, hardMode.class));
            }
        });

        leaderB = findViewById(R.id.leaderBtn);
        leaderB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(modeSelection.this, Leaderboards.class));
            }
        });
    }
}