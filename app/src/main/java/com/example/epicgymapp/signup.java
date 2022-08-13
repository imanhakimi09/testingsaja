package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signup extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private Button createNewUser;
    private EditText addName;
    private EditText addEmail;
    private EditText addPhone;
    private EditText addPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mDatabase = FirebaseDatabase.getInstance().getReference("New User");
        addName = (EditText) findViewById(R.id.username);
        addEmail = (EditText) findViewById(R.id.email);
        addPhone = (EditText) findViewById(R.id.phone);
        addPassword = (EditText) findViewById(R.id.password);
        createNewUser = (Button) findViewById(R.id.firebase_btn);

        //firebase button
        createNewUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = addName.getText().toString().trim();
                String email = addEmail.getText().toString().trim();
                String phone = addPhone.getText().toString().trim();
                String password = addPassword.getText().toString().trim();

                HashMap<String, String > dataMap = new HashMap<String, String>();
                dataMap.put("Name", name);
                dataMap.put("Email", email);
                dataMap.put("Phone", phone);
                dataMap.put("Password", password);

                mDatabase.push().setValue(dataMap);
                if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()){
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