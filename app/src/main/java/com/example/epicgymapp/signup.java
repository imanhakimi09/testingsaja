package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText username = (EditText) findViewById(R.id.username);
        EditText email = (EditText) findViewById(R.id.email);
        EditText phone = (EditText) findViewById(R.id.phone);
        EditText password = (EditText) findViewById(R.id.password);

        Button signupbtn = (Button) findViewById(R.id.signupbtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTxt = username.getText().toString();
                String emailTxt = email.getText().toString();
                String phoneTxt = phone.getText().toString();
                String passwordTxt = password.getText().toString();

                if(usernameTxt.isEmpty() || emailTxt.isEmpty() || phoneTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText( signup.this, "Incomplete form", Toast.LENGTH_SHORT).show(); //shows error message
                }else{
                    {  openHomepage(); }
                }
            }
        });
    }

    //function to direct to homepage
    public void openHomepage() {
        Intent intent = new Intent(this, homepage.class);
        startActivity(intent);
    }

}