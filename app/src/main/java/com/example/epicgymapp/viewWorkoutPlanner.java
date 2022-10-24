package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class viewWorkoutPlanner extends AppCompatActivity {
    private Button newWKBtn;
    private TextView wkTypeTextview, wkNameTextview, RepsTextview, backTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout_planner);
        TextView wkTypeTextview = (TextView)findViewById(R.id.wkTypeTextview);
        TextView wkNameTextview = (TextView)findViewById(R.id.wkNameTextview);
        TextView RepsTextview = (TextView)findViewById(R.id.RepsTextview);
        Button newWkBtn = (Button) findViewById(R.id.newWkBtn);
        backTextview = (TextView) findViewById(R.id.backTextview);

        backTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewWorkoutPlanner.this, planpage.class);
                startActivity(intent);
            }
        });
        //retrieve from workout planner
        String plan = getIntent().getStringExtra("wkType");
        String workoutType = getIntent().getStringExtra("wkName");
        String reps = getIntent().getStringExtra("wkReps");

        //display from workout planner
        wkTypeTextview.setText("Workout Type: " + plan);
        wkNameTextview.setText("Workout Name: " + workoutType);
        RepsTextview.setText("Reps: " + reps);

        newWkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWorkoutPlanner();
            }
        });
    }

    private void openWorkoutPlanner() {
        Intent intent = new Intent(this, workoutPlanner.class);
        startActivity(intent);
    }
}