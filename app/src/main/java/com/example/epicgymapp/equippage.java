package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class equippage extends AppCompatActivity {
    private ImageButton CardioEqBtn;
    private ImageButton ChestEqBtn;
    private ImageButton BackEqBtn;
    private ImageButton LegsEqBtn;
    private ImageButton ArmsEqBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);
        CardioEqBtn = (ImageButton) findViewById(R.id.cardio);
        ChestEqBtn = (ImageButton) findViewById(R.id.chest);
        BackEqBtn = (ImageButton) findViewById(R.id.back);
        LegsEqBtn = (ImageButton) findViewById(R.id.legs);
        ArmsEqBtn = (ImageButton) findViewById(R.id.arms);


        CardioEqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {openCardioEq();}
            }
        });

        BackEqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {openBackEq();}
            }
        });

        ChestEqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {openChestEq();}
            }
        });

        ArmsEqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {openArmsEq();}
            }
        });

        LegsEqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {openLegsEq();}
            }
        });

        //direct to homepage function
        ImageButton directHome = (ImageButton)findViewById(R.id.directHome);
        directHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openHomepage(); }
            }
        });
        //direct to userprofile button function
        ImageButton directGym = (ImageButton)findViewById(R.id.directGym);
        directGym.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openUserProfile(); }
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
    //direct to workout planner
    public void openWorkoutPlanner(){
        Intent intent = new Intent(this, workoutPlanner.class);
        startActivity(intent);
    }
    //direct to meal planner
    public void openMealPlanner(){
        Intent intent = new Intent(this, diet_planner.class);
        startActivity(intent);
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
    public void openPlanpage(){
        Intent intent = new Intent(this, planpage.class);
        startActivity(intent);
    }
    public void openshoppage(){
        Intent intent = new Intent(this, shoppage.class);
        startActivity(intent);
    }

    public void openCardioEq(){
        Intent intent = new Intent(this, shoppage.class);
        startActivity(intent);
    }

    public void openChestEq(){
        Intent intent = new Intent(this, shoppage.class);
        startActivity(intent);
    }

    public void openBackEq(){
        Intent intent = new Intent(this, shoppage.class);
        startActivity(intent);
    }

    public void openLegsEq(){
        Intent intent = new Intent(this, shoppage.class);
        startActivity(intent);
    }

    public void openArmsEq(){
        Intent intent = new Intent(this, shoppage.class);
        startActivity(intent);
    }
}