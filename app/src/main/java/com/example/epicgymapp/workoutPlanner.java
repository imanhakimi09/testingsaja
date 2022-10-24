package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class workoutPlanner extends AppCompatActivity {
private Button addWorkout, increment, decrement;
private EditText workout;
private TextView newPlan;
private TextView repsValue, setsValue, backTextview;
int count = 5;
private DatabaseReference reference;

TextInputLayout textInputLayout;
AutoCompleteTextView autoCompleteTextView;
TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_planner);
        reference = FirebaseDatabase.getInstance().getReference("Plan");
        addWorkout = (Button) findViewById(R.id.addWorkoutBtn);
        workout = (EditText) findViewById(R.id.workout);
        repsValue = (TextView) findViewById(R.id.repsValue);
        increment = (Button) findViewById(R.id.increment);
        decrement = (Button) findViewById(R.id.decrement);
        textInputLayout = findViewById(R.id.workoutMenu);
        autoCompleteTextView = findViewById(R.id.dropdownItems);
        textView = findViewById(R.id.newPlan);
        backTextview = (TextView) findViewById(R.id.backTextview);

        backTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(workoutPlanner.this, viewWorkoutPlanner.class);
                startActivity(intent);
            }
        });

        String [] items = {"Back Workout", "Chest Workout", "Bicep Workout", "Tricep Workout", "Leg Workout"};
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(workoutPlanner.this, R.layout.item_list, items);
        autoCompleteTextView.setAdapter(itemAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                textView.setText( parent.getItemAtPosition(position));
            }
        });

        //add plan to database button
        addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plan = workout.getText().toString().trim();
                String workoutType = autoCompleteTextView.getText().toString().trim();
                String reps = repsValue.getText().toString().trim();

                if(plan.isEmpty()){
                    workout.setError("Data cannot be empty");
                    workout.requestFocus();
                    return;
                }

                HashMap<String, String > dataMap = new HashMap<String, String>();
                dataMap.put("Workout Plan", plan);
                dataMap.put("Workout Type", workoutType);
                dataMap.put("Reps", reps);

                reference.push().setValue(dataMap);
                Toast.makeText(workoutPlanner.this, "New Workout plan added", Toast.LENGTH_SHORT).show();

                //go to view Workout Planner
                Intent intent = new Intent(workoutPlanner.this, viewWorkoutPlanner.class);
                intent.putExtra("wkType", plan);
                intent.putExtra("wkName", workoutType);
                intent.putExtra("wkReps", reps);
                startActivity(intent);

//                getPlan(); //function to retrieve from database
            }
        });
        //reps button
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                repsValue.setText("" + count);
                if(count == 30){
                    Toast.makeText(workoutPlanner.this, "The maximum reps is 30", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        //reps button
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count <= 5){
                    Toast.makeText(workoutPlanner.this, "Reps must be more than 5", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    count--;
                    repsValue.setText("" + count);
                }
            }
        });
    }

//    //retrieve from database
//    private void getPlan() {
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String plan = snapshot.getValue().toString();
//                String workout = snapshot.getValue().toString();
//                String reps = snapshot.getValue().toString();
//
//                newPlan.setText("Workout Plan " + plan);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(workoutPlanner.this, "Unable to retrieve data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}