package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signup extends AppCompatActivity {
//    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private Button createNewUser;
    private EditText addName;
    private EditText addEmail;
    private EditText addPhone;
    private EditText addPassword;
    private CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
//        mDatabase = FirebaseDatabase.getInstance().getReference("New User");
        mAuth = FirebaseAuth.getInstance();
        addName = (EditText) findViewById(R.id.username);
        addEmail = (EditText) findViewById(R.id.email);
        addPhone = (EditText) findViewById(R.id.phone);
        addPassword = (EditText) findViewById(R.id.password);
        checkbox = (CheckBox) findViewById(R.id.checkboxPolicy);
        createNewUser = (Button) findViewById(R.id.firebase_btn);

        //firebase button
        createNewUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = addName.getText().toString().trim();
                String email = addEmail.getText().toString().trim();
                String phone = addPhone.getText().toString().trim();
                String password = addPassword.getText().toString().trim();

                //add form validation
                if(name.isEmpty()){
                    addName.setError("Username is required");
                    addName.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    addEmail.setError("Email is required");
                    addEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    addEmail.setError("Please provide valid email");
                    addEmail.requestFocus();
                    return;
                }
                if(phone.isEmpty()){
                    addPhone.setError("Phone number is required");
                    addPhone.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    addPassword.setError("Password is required");
                    addPassword.requestFocus();
                    return;
                }
                if(password.length() < 6){
                    addPassword.setError("Password length must be more than 6 characters");
                    addPassword.requestFocus();
                    return;
                }
                if(!checkbox.isChecked()){
                    checkbox.setError("Please agree to the terms and policy");
                    checkbox.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user = new User(name, email, phone, password);

                                FirebaseDatabase.getInstance().getReference("New Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(signup.this, "User has been successfully created!", Toast.LENGTH_LONG).show();
                                                }else{
                                                    Toast.makeText(signup.this, "Failed to create user. Try again!", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                        {  openHomepage(); }
                            }else{
                                Toast.makeText(signup.this, "Failed to create user. Try again!", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
            }
        });

    }

    //function to direct to homepage
    public void openHomepage() {
        Intent intent = new Intent(this, homepage.class);
        startActivity(intent);
    }

}