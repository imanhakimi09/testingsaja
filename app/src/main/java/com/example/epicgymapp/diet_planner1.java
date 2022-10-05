package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class diet_planner1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_planner1);
        Button addPlan = (Button) findViewById(R.id.addPlan);

        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {  openDietPlan(); }
            }
        });
    }
    public void openDietPlan(){
        Intent intent = new Intent(this, diet_planner.class);
        startActivity(intent);
    }
}