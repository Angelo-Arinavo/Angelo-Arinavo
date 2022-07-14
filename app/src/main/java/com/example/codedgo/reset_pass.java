package com.example.codedgo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class reset_pass extends AppCompatActivity{

    private ImageView banner;
    private TextInputEditText email_add;
    private Button reset;
    private ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        banner = (ImageView) findViewById(R.id.CodeDGo);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(reset_pass.this, login.class);
                startActivity(intent);
            }
        });

        email_add = (TextInputEditText) findViewById(R.id.email);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        reset = (Button) findViewById(R.id.resetBtn);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPass();
            }
        });
    }

    private void resetPass() {
        String email = email_add.getText().toString().trim();
        if(email.isEmpty()){
            email_add.setError("Email Address is required!");
            email_add.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_add.setError("Invalid Email Address!");
            email_add.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    startActivity(new Intent(reset_pass.this,login.class));
                    Toast.makeText(reset_pass.this, "Check your email!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    finish();
                }else{
                    Toast.makeText(reset_pass.this, "Try again!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

}