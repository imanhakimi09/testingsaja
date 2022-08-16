package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userprofile extends AppCompatActivity {
private FirebaseUser user;
private DatabaseReference reference;
private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("New Users");
        userId = user.getUid();

        final TextView displayName = (TextView) findViewById(R.id.viewUsername);
        final TextView displayEmail = (TextView) findViewById(R.id.viewEmail);
        final TextView displayPhone = (TextView) findViewById(R.id.viewPhone);
        final TextView displayPassword = (TextView) findViewById(R.id.viewPassword);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String name = userProfile.name;
                    String email = userProfile.email;
                    String phone = userProfile.phone;
                    String password = userProfile.password;

                    displayName.setText(name);
                    displayEmail.setText(email);
                    displayPhone.setText(phone);
                    displayPassword.setText(password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(userprofile.this, "Failed to retrieve data!", Toast.LENGTH_LONG).show();
            }
        });
    }
}