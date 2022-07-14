package com.example.codedgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class hardMode8 extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;


    private int maxPressCount = 10;
    private String textAnswer = "PROGRAMMER";

    private String ans = "";
    private Button P, R1, O, G, R2, A, M, M2, E, R3, S, T, res, exit;
    private EditText editT;
    private Integer coins, getValInt;
    private TextView userCoins, userScore, timer;
    private CountDownTimer Ctimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_mode8);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();


        userScore = findViewById(R.id.scoreText);
        Intent getIntent = getIntent();
        String getVal = getIntent.getStringExtra("KEY_SENDER");
        Integer getValInt = Integer.parseInt(getVal);
        userScore.setText(getValInt.toString());


        userCoins = findViewById(R.id.coinsText);
        timer = findViewById(R.id.timeText);

        startTimer();

        editT = findViewById(R.id.editText);
        editT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = editT.getText().toString();
                int Scount = string.length();
                if (Scount == maxPressCount){
                    doValidate();
                }
            }
        });


        P = findViewById(R.id.PBtn);
        P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("P");
                P.setEnabled(false);
                P.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });
        R1 = findViewById(R.id.RBtn);
        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("R");
                R1.setEnabled(false);
                R1.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });
        O = findViewById(R.id.OBtn);
        O.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("O");
                O.setEnabled(false);
                O.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });
        G = findViewById(R.id.GBtn);
        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("G");
                G.setEnabled(false);
                G.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });
        R2 = findViewById(R.id.R2Btn);
        R2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("R");
                R2.setEnabled(false);
                R2.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });
        A = findViewById(R.id.ABtn);
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("A");
                A.setEnabled(false);
                A.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });
        M = findViewById(R.id.MBtn);
        M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("M");
                M.setEnabled(false);
                M.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });
        M2 = findViewById(R.id.M2Btn);
        M2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("M");
                M2.setEnabled(false);
                M2.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });
        E = findViewById(R.id.EBtn);
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("E");
                E.setEnabled(false);
                E.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });
        R3 = findViewById(R.id.R3Btn);
        R3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("R");
                M2.setEnabled(false);
                M2.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });

        S = findViewById(R.id.SBtn);
        S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("S");
                S.setEnabled(false);
                S.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });
        T = findViewById(R.id.TBtn);
        T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("T");
                T.setEnabled(false);
                T.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });





        res = findViewById(R.id.resetBtn);
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coins = Integer.parseInt(userCoins.getText().toString());
                if(coins != 0) {
                ans = "";
                editT.setText("");

                P.setBackgroundColor(Color.parseColor("#FF212121"));
                R1.setBackgroundColor(Color.parseColor("#FF212121"));
                O.setBackgroundColor(Color.parseColor("#FF212121"));
                G.setBackgroundColor(Color.parseColor("#FF212121"));
                R2.setBackgroundColor(Color.parseColor("#FF212121"));
                A.setBackgroundColor(Color.parseColor("#FF212121"));
                M.setBackgroundColor(Color.parseColor("#FF212121"));
                M2.setBackgroundColor(Color.parseColor("#FF212121"));
                E.setBackgroundColor(Color.parseColor("#FF212121"));
                R3.setBackgroundColor(Color.parseColor("#FF212121"));
                S.setBackgroundColor(Color.parseColor("#FF212121"));
                T.setBackgroundColor(Color.parseColor("#FF212121"));


                P.setEnabled(true);
                R1.setEnabled(true);
                O.setEnabled(true);
                G.setEnabled(true);
                R2.setEnabled(true);
                A.setEnabled(true);
                M.setEnabled(true);
                M2.setEnabled(true);
                E.setEnabled(true);
                R3.setEnabled(true);
                S.setEnabled(true);
                T.setEnabled(true);

                coins-=2;
                userCoins.setText(String.valueOf(coins));
                res.setEnabled(false);
                res.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
                    else{
                Toast.makeText(hardMode8.this, "Insufficient Coins",Toast.LENGTH_LONG).show();
            }
            }
        });

        exit = findViewById(R.id.exitBtn);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bestScore();
                update();
                startActivity(new Intent(hardMode8.this, MainActivity.class));
                Ctimer.cancel();
                finish();
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
                users _coins = snapshot.getValue(users.class);

                if(_coins != null){
                    String coins_ = _coins.coins;
                    userCoins.setText(coins_);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(hardMode8.this, "Please try to re-login your account!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doValidate(){
        EditText editText = findViewById(R.id.editText);
        userCoins = findViewById(R.id.coinsText);
        if(editText.getText().toString().equals(textAnswer)){
            coins = Integer.parseInt(userCoins.getText().toString());
            coins+=1;
            Toast.makeText(hardMode8.this, "CORRECT",Toast.LENGTH_LONG).show();
            String coinStr = String.valueOf(coins);
            userCoins.setText(coinStr);
            getValInt += 2;
            userScore.setText(getValInt.toString());
            update();
            ans = "";
            bestScore();
            Intent intent = new Intent(this , hardMode9.class);
            intent.putExtra("KEY_SENDER", userScore.getText().toString());
            startActivity(intent);
            finish();
            Ctimer.cancel();
        }else{
            Toast.makeText(hardMode8.this, "WRONG",Toast.LENGTH_LONG).show();
            editText.setText("");
            ans = "";
        }

    }

    private void startTimer() {
        Ctimer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                bestScore();
                update();
                startActivity(new Intent(hardMode8.this, MainActivity.class));
                Toast.makeText(hardMode8.this, "GAME OVER!", Toast.LENGTH_LONG).show();
                finish();
            }

        }.start();
    }

    private void setDisplay(String givenValue) {
        ans = ans + givenValue;
        editT.setText(ans);
    }

    private void update() {
        reference.child(user.getUid()).child("coins").setValue(userCoins.getText().toString());
    }

    private void bestScore(){
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users _score = snapshot.getValue(users.class);

                if(_score != null){
                    Integer score_ = _score.hardscore;

                    Integer inscoreInt = Integer.valueOf(userScore.getText().toString());

                    if(score_ < inscoreInt){
                        reference.child(user.getUid()).child("hardscore").setValue(inscoreInt);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(hardMode8.this, "Please try to re-login your account!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


