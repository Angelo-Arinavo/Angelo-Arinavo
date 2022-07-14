package com.example.codedgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login extends AppCompatActivity{

    private TextInputEditText email_add, pass;
    private TextView forgot, register;
    private Button login;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email_add = (TextInputEditText) findViewById(R.id.email);
        pass = (TextInputEditText) findViewById(R.id.password);

        forgot = (TextView) findViewById(R.id.forgotPassword);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, reset_pass.class));
            }
        });

        login = (Button) findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAdmin();
            }
        });

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, register.class));
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void loginAdmin() {
        String email = email_add.getText().toString().trim();
        String password = pass.getText().toString().trim();

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

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(login.this,MainActivity.class);
                            startActivity(intent);
                            progressBar.setVisibility(View.GONE);
                            finish();
                        }else{
                            Toast.makeText(login.this,"Failed to Login!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}