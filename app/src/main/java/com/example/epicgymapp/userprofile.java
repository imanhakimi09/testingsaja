package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.w3c.dom.Text;

public class userprofile extends AppCompatActivity {
private FirebaseUser user;
private DatabaseReference reference;
private String userId;
private Button updatebtn, logoutBtn;
String _name, _email, _phone, _password;
private TextView membership;

//    //keep login
//    SharedPreferences sharedPreferences;
//    private static final String SHARED_PREF_NAME = "mypreference";
//    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("New Users");
        userId = user.getUid();

         EditText displayName = (EditText) findViewById(R.id.viewUsername);
         EditText displayEmail = (EditText) findViewById(R.id.viewEmail);
         EditText displayPhone = (EditText) findViewById(R.id.viewPhone);
         EditText displayPassword = (EditText) findViewById(R.id.viewPassword);
        updatebtn = (Button) findViewById(R.id.updateProfile);
        logoutBtn = (Button) findViewById(R.id.logout);
        membership = (TextView) findViewById(R.id.membership);

//        //keep login
//        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//        String email = sharedPreferences.getString(KEY_EMAIL, null);
//        if(email != null){
//            displayEmail.setText(email);
//        }
        //membership register
        membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMembership();
            }
        });
        //logout function
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //alert user before logout
                AlertDialog.Builder builder = new AlertDialog.Builder(userprofile.this);
                builder.setTitle("Logout");
                builder.setMessage("Confirm?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

//                        //clear sharedPreference data
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.clear();
//                        editor.commit();
//                        finish();

                        //logout user
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(userprofile.this, login.class));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //cancel logout
                        Toast.makeText(userprofile.this, "Cancel Logout", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                //retrieve and display user profile
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
//                 if(updateName() || updateEmail()){
//                     Toast.makeText(userprofile.this, "Profile has been updated", Toast.LENGTH_LONG).show(); //show msg if profile updated
//                    }else{
//                     Toast.makeText(userprofile.this, "Profile is still the same", Toast.LENGTH_LONG).show(); //show msg if data still the same
//                 }
                 //alert user before updating
                 AlertDialog.Builder builder = new AlertDialog.Builder(userprofile.this);
                 builder.setTitle("Update profile");
                 builder.setMessage("Proceed with update profile?");
                 builder.setCancelable(false);
                 builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         Toast.makeText(userprofile.this, "Profile has been updated", Toast.LENGTH_LONG).show();
                     }
                 });
                 builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         Toast.makeText(userprofile.this, "Cancel update", Toast.LENGTH_LONG).show();
                     }
                 });
                 AlertDialog dialog = builder.create();
                 dialog.show();
             }
         });
    }

    private void openMembership() {
        Intent intent = new Intent(this, membershippage.class);
        startActivity(intent);
    }
//    //update name function
//    private boolean updateName() {
//        if(!_name.equals(displayName.getEditText().getText().toString())){
//            reference.child(_name).child("name").setValue(displayName.getEditText().getText().toString());
//            _name = displayName.getEditText().getText.toString();
//            return true;
//        }else{
//            return false;
//        }
//    }
//    //update email function
//    private boolean updateEmail() {
//        if(!_email.equals(displayEmail.getEditText().getText().toString())){
//            reference.child(_email).child("email").setValue(displayEmail.getEditText().getText().toString());
//            _email = displayEmail.getEditText().getText.toString();
//            return true;
//        }else{
//            return false;
//        }
//    }
}