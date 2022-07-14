package com.example.codedgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class userAccount extends AppCompatActivity {

    private Button updateAcc, deleteAcc, changePass;

    public TextInputEditText user_name, email_add;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        ImageView banner = (ImageView) findViewById(R.id.CodeDGo);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(userAccount.this, MainActivity.class));

            }
        });

        user_name = (TextInputEditText) findViewById(R.id.userName);
        email_add = (TextInputEditText) findViewById(R.id.email);

        updateAcc = findViewById(R.id.updateBtn);
        updateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        changePass = (Button) findViewById(R.id.changepassBtn);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(userAccount.this, reset_pass.class));
            }
        });

        deleteAcc = findViewById(R.id.deleteBtn);
        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users _user_name = snapshot.getValue(users.class);
                users _email_add = snapshot.getValue(users.class);

                if(_user_name != null && _email_add != null){
                    String username = _user_name.username;
                    String email = _email_add.email;

                    user_name.setText(""+username);
                    email_add.setText(""+email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(userAccount.this, "Please try to re-login your account!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void update() {
        reference.child(user.getUid()).child("username").setValue(user_name.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(userAccount.this, "SAVED!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(userAccount.this, MainActivity.class));
                finish();
            }
        });
    }

    private void deleteUser() {
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            reference.child(user.getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    FirebaseAuth.getInstance().signOut();
                                    Intent intent = new Intent(userAccount.this, login.class);
                                    startActivity(intent);
                                    Toast.makeText(userAccount.this, "Account Deleted!", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            });

                        }
                    }
                });
    }

}