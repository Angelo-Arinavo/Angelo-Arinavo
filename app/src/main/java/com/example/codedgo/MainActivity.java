package com.example.codedgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity{

    private Button play, logout;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    private TextView playerName, easyS, mediumS, hardS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        easyS = findViewById(R.id.easyScoreLB);
        hardS = findViewById(R.id.hardScoreLB);

        playerName = (TextView)findViewById(R.id.playername);
        playerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, userAccount.class));
            }
        });

        play = findViewById(R.id.playBtn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, modeSelection.class));
            }
        });

        logout = findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });


        if (currentUser == null) {
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
            finish();
            return;
        }

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users user_name = snapshot.getValue(users.class);
                users easy_S = snapshot.getValue(users.class);
                users hard_S = snapshot.getValue(users.class);

                if(user_name != null){
                    String username = user_name.username;
                    Integer easy = easy_S.easyscore;
                    Integer hard = hard_S.hardscore;


                    playerName.setText(username);
                    easyS.setText(easy.toString());
                    hardS.setText(hard.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Please try to re-login your account!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
        Toast.makeText(this, "Logged Out!", Toast.LENGTH_SHORT).show();
        finish();
    }

}