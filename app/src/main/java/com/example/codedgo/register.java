package com.example.codedgo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity{

    private TextInputEditText user_name, email_add, pass;
    private Button register;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        user_name = (TextInputEditText) findViewById(R.id.usernameLB);
        email_add = (TextInputEditText) findViewById(R.id.email);
        pass = (TextInputEditText) findViewById(R.id.password);

        register = (Button) findViewById(R.id.registerBtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    private void registerUser() {
        String username = user_name.getText().toString().trim();
        String email = email_add.getText().toString().trim();
        String password = pass.getText().toString().trim();
        Integer easyS = 0;
        Integer hardS = 0;
        String coins = "20";


        if(username.isEmpty()){
            user_name.setError("Username is required!");
            user_name.requestFocus();
            return;
        }
        if(username.length()<5){
            user_name.setError("Min username is 5 characters!");
            user_name.requestFocus();
            return;
        }

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

        if(password.isEmpty()){
            pass.setError("Password is required!");
            pass.requestFocus();
            return;
        }
        if(password.length()<6){
            pass.setError("Min password is 6 characters!");
            pass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            users user = new users(username,email,easyS,hardS,coins);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Intent intent=new Intent(register.this,MainActivity.class);
                                                startActivity(intent);
                                                progressBar.setVisibility(View.GONE);
                                            }
                                            else {
                                                Toast.makeText(register.this, "Register Failed! Try Again!", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(register.this, "Try Again!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}