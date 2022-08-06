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
                if(username.getText().toString().equals("iman")
                        && email.getText().toString().equals("iman.com")
                        && phone.getText().toString().equals("1234")
                        && password.getText().toString().equals("iman123")){
                    {  openHomepage(); }
                }else{
                    Toast.makeText( signup.this, "Incomplete form", Toast.LENGTH_SHORT).show(); //shows error message
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