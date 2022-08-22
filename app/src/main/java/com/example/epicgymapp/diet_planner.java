package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class diet_planner extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_planner);
        spinner = (Spinner) findViewById(R.id.spinner1);
        String[] dietPlan = getResources().getStringArray(R.array.dietType);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dietPlan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
            if(parent.getId() == R.id.spinner1){
                String valueFromSpinner = parent.getItemAtPosition(position).toString();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent){

        }
}