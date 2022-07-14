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

public class easyMode9 extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;


    private int maxPressCount = 4;
    private String textAnswer = "LOOP";

    private String ans = "";
    private Button L, O, O2, P, V, S, J, K,  res, exit;
    private EditText editT;
    private Integer coins, getValInt;
    private TextView userCoins, userScore, timer;
    private CountDownTimer Ctimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_mode9);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();


        userScore = findViewById(R.id.scoreText);
        Intent getIntent = getIntent();
        String getVal = getIntent.getStringExtra("KEY_SENDER");
        getValInt = Integer.parseInt(getVal);
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

        L = findViewById(R.id.LBtn);
        L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("L");
                L.setEnabled(false);
                L.setBackgroundColor(Color.parseColor("#FFDB58"));
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
        O2 = findViewById(R.id.O2Btn);
        O2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("O");
                O2.setEnabled(false);
                O2.setBackgroundColor(Color.parseColor("#FFDB58"));
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

        J = findViewById(R.id.JBtn);
        J.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("J");
                J.setEnabled(false);
                J.setBackgroundColor(Color.parseColor("#FFDB58"));
            }
        });
        V = findViewById(R.id.VBtn);
        V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("V");
                V.setEnabled(false);
                V.setBackgroundColor(Color.parseColor("#FFDB58"));
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
        K = findViewById(R.id.KBtn);
        K.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDisplay("K");
                K.setEnabled(false);
                K.setBackgroundColor(Color.parseColor("#FFDB58"));
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
                    editT.setTextColor(Color.parseColor("#FFFFFF"));

                    L.setBackgroundColor(Color.parseColor("#FF212121"));
                    O.setBackgroundColor(Color.parseColor("#FF212121"));
                    O2.setBackgroundColor(Color.parseColor("#FF212121"));
                    P.setBackgroundColor(Color.parseColor("#FF212121"));

                    J.setBackgroundColor(Color.parseColor("#FF212121"));
                    V.setBackgroundColor(Color.parseColor("#FF212121"));
                    S.setBackgroundColor(Color.parseColor("#FF212121"));
                    K.setBackgroundColor(Color.parseColor("#FF212121"));


                    L.setEnabled(true);
                    O.setEnabled(true);
                    O2.setEnabled(true);
                    P.setEnabled(true);

                    J.setEnabled(true);
                    V.setEnabled(true);
                    S.setEnabled(true);
                    K.setEnabled(true);

                    coins -= 2;
                    userCoins.setText(String.valueOf(coins));
                    update();
                }
                else{
                    Toast.makeText(easyMode9.this, "Insufficient Coins",Toast.LENGTH_LONG).show();
                }
            }
        });

        exit = findViewById(R.id.exitBtn);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bestScore();
                update();
                startActivity(new Intent(easyMode9.this, MainActivity.class));
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
                Toast.makeText(easyMode9.this, "Please try to re-login your account!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doValidate(){
        EditText editText = findViewById(R.id.editText);
        userCoins = findViewById(R.id.coinsText);
        if(editText.getText().toString().equals(textAnswer)){
            coins = Integer.parseInt(userCoins.getText().toString());
            coins+=1;
            Toast.makeText(easyMode9.this, "CORRECT",Toast.LENGTH_LONG).show();
            String coinStr = String.valueOf(coins);
            userCoins.setText(coinStr);
            getValInt += 2;
            userScore.setText(getValInt.toString());
            update();
            ans = "";
            bestScore();
            Intent intent = new Intent(this , easyMode10.class);
            intent.putExtra("KEY_SENDER", userScore.getText().toString());
            startActivity(intent);
            finish();
            Ctimer.cancel();
        }else{
            editText.setTextColor(Color.parseColor("#D50000"));
        }

    }

    private void startTimer() {
        Ctimer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                update();
                bestScore();
                startActivity(new Intent(easyMode9.this, MainActivity.class));
                Toast.makeText(easyMode9.this, "GAME OVER!", Toast.LENGTH_LONG).show();
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
                    Integer score_ = _score.easyscore;

                    Integer inscoreInt = Integer.valueOf(userScore.getText().toString());

                    if(score_ < inscoreInt){
                        reference.child(user.getUid()).child("easyscore").setValue(inscoreInt);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(easyMode9.this, "Please try to re-login your account!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


