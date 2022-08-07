package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class planpage extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private Button addPlan;
    private EditText typePlanner;
    private EditText typePlannerB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planpage);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        addPlan = (Button) findViewById(R.id.addNewPlan);
        typePlanner = (EditText) findViewById(R.id.typePlanHere);
        typePlannerB = (EditText) findViewById(R.id.typePlanHereB);

        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String planner = typePlanner.getText().toString().trim();
                String plannerB = typePlannerB.getText().toString().trim();

                HashMap<String, String > dataMap = new HashMap<String, String>();
                dataMap.put("Plan A", planner);
                dataMap.put("Plan B", plannerB);

                mDatabase.push().setValue(dataMap);
            }
        });

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
                Toast.makeText( planpage.this, "This is Plan page", Toast.LENGTH_SHORT).show();
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
    public void openPlanpage(){
        Intent intent = new Intent(this, planpage.class);
        startActivity(intent);
    }
    public void openshoppage(){
        Intent intent = new Intent(this, shoppage.class);
        startActivity(intent);
    }
}