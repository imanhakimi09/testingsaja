package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class homepage extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //retrieve data
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("New Users");
        userId = user.getUid();

        textView2 = (TextView) findViewById(R.id.textView2);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                //retrieve and display user profile
                if(userProfile != null){
                    String name = userProfile.name;

                    textView2.setText("Hello " + name + ",");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(homepage.this, "Failed to retrieve data!", Toast.LENGTH_LONG).show();
            }
        });

        //direct to homepage button function
        ImageButton directHome = (ImageButton)findViewById(R.id.directHome);
        directHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText( homepage.this, "This is Homepage", Toast.LENGTH_SHORT).show();
            }
        });
        //direct to userprofile button function
        ImageButton directGym = (ImageButton)findViewById(R.id.directGym);
        directGym.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openUserProfile(); }
            }
        });
        //direct to QR page button function
        ImageButton directQR = (ImageButton)findViewById(R.id.directQR);
        directQR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText( homepage.this, "Go to QRpage", Toast.LENGTH_SHORT).show();
            }
        });
        //direct to plan page button function
        ImageButton directPlan = (ImageButton)findViewById(R.id.directPlan);
        directPlan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openPlanpage(); }
            }
        });
        //direct to shop page button function
        ImageButton directShop = (ImageButton)findViewById(R.id.directShop);
        directShop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openshoppage(); }
            }
        });

//        //direct to equipments and machine page button function
//        ImageButton directEquip = (ImageButton)findViewById(R.id.machine);
//        directEquip.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                {  openequippage(); }
//            }
//        });



    }

    //direct to homepage
    public void openHomepage(){
        Intent intent = new Intent(this, homepage.class);
        startActivity(intent);
    }
    //direct to gympage
    public void openGympage(){
        Intent intent = new Intent(this, gympage.class);
        startActivity(intent);
    }
    //direct to userprofile
    public void openUserProfile(){
        Intent intent = new Intent(this, userprofile.class);
        startActivity(intent);
    }
    //direct to planner
    public void openPlanpage(){
        Intent intent = new Intent(this, planpage.class);
        startActivity(intent);
    }
    //direct to shopping
    public void openshoppage(){
        Intent intent = new Intent(this, shoppage.class);
        startActivity(intent);
    }

    //direct to equipments and machines
//    public void openequippage(){
//        Intent intent = new Intent(this, equippage.class);
//        startActivity(intent);
//    }
}
