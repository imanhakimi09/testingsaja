package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
private EditText email;
private EditText password;
private Button loginbtn;
private TextView forgotpwdBtn;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        forgotpwdBtn = (TextView) findViewById(R.id.forgotpwd);
        mAuth = FirebaseAuth.getInstance();

//        //keep login
//        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//        if(email != null){
//            {  openHomepage(); } //direct to homepage
//        }

        //login function
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                //form validation
                if(userEmail.isEmpty()){
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    email.setError("Please provide valid email");
                    email.requestFocus();
                    return;
                }
                if(userPassword.isEmpty()){
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }
                if(userPassword.length() < 6){
                    password.setError("Password length must be more than 6 characters");
                    password.requestFocus();
                    return;
                }

//                //keep login
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(KEY_EMAIL,email.getText().toString());
//                editor.apply();

                mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if(user.isEmailVerified()){
                                {  openHomepage(); } //direct to homepage
                            }else{
                                user.sendEmailVerification();
                                Toast.makeText(login.this, "Check email to verify account", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(login.this, "Failed to login user. Try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                }
            });
        //forgot pwd function
        forgotpwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                { openForgotPwd();}
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
        //direct to forgotpassword
        public void openForgotPwd(){
            Intent intent = new Intent(this, forgotpassword.class);
            startActivity(intent);
        }

    }
