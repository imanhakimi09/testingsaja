package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class workoutPlanner extends AppCompatActivity {
private Button addWorkout;
private EditText workout;
private ListView listView;
private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_planner);
        mDatabase = FirebaseDatabase.getInstance().getReference("Plan");
        addWorkout = (Button) findViewById(R.id.addWorkoutBtn);
        workout = (EditText) findViewById(R.id.workoutPlan);
        listView = (ListView) findViewById(R.id.viewOutput);

//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String plan = snapshot.getValue().toString();
//                listView.setText("Workout Plan" + plan);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(workoutPlanner.this, "Fail to add data", Toast.LENGTH_SHORT).show();
//            }
//        });

        addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plan = workout.getText().toString().trim();

                HashMap<String, String > dataMap = new HashMap<String, String>();
                dataMap.put("Workout Plan", plan);

                mDatabase.push().setValue(dataMap);
            }
        });
    }
}