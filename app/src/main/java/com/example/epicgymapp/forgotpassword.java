package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class forgotpassword extends AppCompatActivity {
private TextView backLogin;
private Button resetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        TextView backLogin = (TextView) findViewById(R.id.backToLogin);
        Button resetpassword = (Button) findViewById(R.id.resetpwd);

        //reset password function
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast.makeText( forgotpassword.this, "Please enter your credentials", Toast.LENGTH_SHORT).show(); //shows error message
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
}