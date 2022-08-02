package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);
        Button loginbtn = (Button) findViewById(R.id.loginbtn);

        //login function
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("admin") && password.getText().toString().equals("admin123")){
                    {  openHomepage(); } //direct to homepage
                }else
                    Toast.makeText( login.this, "Login failed", Toast.LENGTH_SHORT).show(); //shows error message
                }
            });
        }
        //function to direct to homepage
            public void openHomepage() {
                Intent intent = new Intent(this, homepage.class);
                startActivity(intent);
        }

    }
