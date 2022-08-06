package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        Button loginbtn = (Button) findViewById(R.id.loginbtn);

        //login function
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTxt = email.getText().toString();
                String passwordTxt = password.getText().toString();

                if(emailTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText( login.this, "Login failed", Toast.LENGTH_SHORT).show(); //shows error message
                }else
                    {  openHomepage(); } //direct to homepage
                }
            });

        //direct to signup button function
        Button directSignup = (Button)findViewById(R.id.directSignup);
        directSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openSignup(); }
            }
        });
        }

        //function to direct to homepage
            public void openHomepage() {
                Intent intent = new Intent(this, homepage.class);
                startActivity(intent);
        }

        //direct to signup
        public void openSignup(){
            Intent intent = new Intent(this, signup.class);
            startActivity(intent);
        }

    }
