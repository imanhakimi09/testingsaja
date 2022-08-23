package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
private Button updatebtn;
//String _name, _email, _phone, _password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("New Users");
        userId = user.getUid();

        final EditText displayName = (EditText) findViewById(R.id.viewUsername);
        final EditText displayEmail = (EditText) findViewById(R.id.viewEmail);
        final EditText displayPhone = (EditText) findViewById(R.id.viewPhone);
        final EditText displayPassword = (EditText) findViewById(R.id.viewPassword);
        updatebtn = (Button) findViewById(R.id.updateProfile);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String name = userProfile.name;
                    String email = userProfile.email;
                    String phone = userProfile.phone;
                    String password = userProfile.password;

                    displayName.setText("Username: " + name);
                    displayEmail.setText("Email: " + email);
                    displayPhone.setText("Phone Number: " + phone);
                    displayPassword.setText("Password: " + password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(userprofile.this, "Failed to retrieve data!", Toast.LENGTH_LONG).show();
            }
        });
        //update profile button
         updatebtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                    Toast.makeText(userprofile.this, "Credentials has been updated", Toast.LENGTH_LONG).show();
//                    if(updateName() || updateEmail()){
//                        Toast.makeText(userprofile.this, "Credentials has been updated", Toast.LENGTH_LONG).show(); //show msg if profile updated
//                    }
             }
         });
    }
//    //update name function
//    private boolean updateName() {
//        if(!_name.equals(displayName.getEditText().getText().toString())){
//            reference.child(_name).child("name").setValue(displayName.getEditText().getText().toString());
//            return true;
//        }else{
//            return false;
//        }
//    }
//    //update email function
//    private boolean updateEmail() {
//        if(!_email.equals(displayEmail.getEditText().getText().toString())){
//            reference.child(_email).child("email").setValue(displayEmail.getEditText().getText().toString());
//            return true;
//        }else{
//            return false;
//        }
//    }
}