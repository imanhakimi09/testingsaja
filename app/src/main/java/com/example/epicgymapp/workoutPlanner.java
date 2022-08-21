package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
private Button addWorkout;
private EditText workout;
private TextView listView;
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
//        listView = (TextView) findViewById(R.id.viewOutput);

        textInputLayout = findViewById(R.id.workoutMenu);
        autoCompleteTextView = findViewById(R.id.dropdownItems);
        textView = findViewById(R.id.selectedItem);

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

                HashMap<String, String > dataMap = new HashMap<String, String>();
                dataMap.put("Workout Plan", plan);

                reference.push().setValue(dataMap);
            }
        });
    }
}