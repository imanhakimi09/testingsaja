package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class shoppage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppage);

        //direct to homepage function
        ImageButton directHome = (ImageButton)findViewById(R.id.directHome);
        directHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openHomepage(); }
            }
        });
        //direct to gympage function
        ImageButton directGym = (ImageButton)findViewById(R.id.directGym);
        directGym.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openGympage(); }
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
                Toast.makeText( shoppage.this, "This is Shop page", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void openHomepage(){
        Intent intent = new Intent(this, homepage.class);
        startActivity(intent);
    }
    public void openGympage(){
        Intent intent = new Intent(this, gympage.class);
        startActivity(intent);
    }
    public void openPlanpage(){
        Intent intent = new Intent(this, planpage.class);
        startActivity(intent);
    }
    public void openshoppage(){
        Intent intent = new Intent(this, shoppage.class);
        startActivity(intent);
    }
}
