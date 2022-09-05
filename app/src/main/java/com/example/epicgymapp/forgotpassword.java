package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class forgotpassword extends AppCompatActivity {
private TextView backLogin;
private Button resetpassword;
private EditText takeUsername;
private EditText takeEmail;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        TextView backLogin = (TextView) findViewById(R.id.backToLogin);
        Button resetpassword = (Button) findViewById(R.id.resetpwd);
        EditText takeUsername = (EditText) findViewById(R.id.username);
        EditText takeEmail = (EditText) findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();

        //reset password function
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = takeUsername.getText().toString();
                String email = takeEmail.getText().toString();

                if(name.isEmpty()){
                    takeUsername.setError("Username is required");
                    takeUsername.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    takeEmail.setError("Email is required");
                    takeEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    takeEmail.setError("Invalid email!");
                    takeEmail.requestFocus();
                    return;
                }
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText( forgotpassword.this, "Check email to reset password", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText( forgotpassword.this, "Try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                    { resetPwd();}
            }
        });

        //back to login function
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                { directLogin();}
            }
        });

    }
        //function to direct to login
        public void directLogin() {
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
        }
        //function to reset password
        public void resetPwd() {
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
        }
}