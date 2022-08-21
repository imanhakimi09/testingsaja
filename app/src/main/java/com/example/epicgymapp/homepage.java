package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //direct to homepage button function
        ImageButton directHome = (ImageButton)findViewById(R.id.directHome);
        directHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText( homepage.this, "This is Homepage", Toast.LENGTH_SHORT).show();
            }
        });
        //direct to gympage button function
        ImageButton directGym = (ImageButton)findViewById(R.id.directGym);
        directGym.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openGympage(); }
            }
        });
        //direct to QR page button function
        ImageButton directQR = (ImageButton)findViewById(R.id.directQR);
        directQR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openUserProfile(); }
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
}
