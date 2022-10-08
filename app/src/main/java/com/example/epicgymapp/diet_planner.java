package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class diet_planner extends AppCompatActivity{
    private Spinner spinner;
    private EditText dietFood;
    TextInputLayout textInputLayout1, textInputLayout2;
    AutoCompleteTextView autoCompleteTextView1, autoCompleteTextView2;
    private Button addDietBtn;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_planner);
        reference = FirebaseDatabase.getInstance().getReference("DietPlan");
        dietFood = (EditText) findViewById(R.id.dietFood);
        textInputLayout1 = (TextInputLayout) findViewById(R.id.dietType);
        textInputLayout2 = (TextInputLayout) findViewById(R.id.dietMeal);
        autoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.dropdownDiet);
        autoCompleteTextView2 = (AutoCompleteTextView) findViewById(R.id.dropdownMeal);
        addDietBtn = (Button) findViewById(R.id.addDietBtn);

        String [] diet = {"Carbs", "Protein", "Fat", "Sugar"};
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(diet_planner.this, R.layout.item_list, diet);
        autoCompleteTextView1.setAdapter(itemAdapter);
        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                textView.setText( parent.getItemAtPosition(position));
            }
        });

        String [] meal = {"Breakfast", "Lunch", "Dinner", "Supper"};
        ArrayAdapter<String> itemAdapter2 = new ArrayAdapter<>(diet_planner.this, R.layout.item_list, meal);
        autoCompleteTextView2.setAdapter(itemAdapter2);
        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                textView.setText( parent.getItemAtPosition(position));
            }
        });

        //add plan to database button
        addDietBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String food = dietFood.getText().toString().trim();
                String diet = autoCompleteTextView1.getText().toString().trim();
                String meal = autoCompleteTextView2.getText().toString().trim();

                if(food.isEmpty()){
                    dietFood.setError("Data cannot be empty");
                    dietFood.requestFocus();
                    return;
                }

                HashMap<String, String > dataMap = new HashMap<String, String>();
                dataMap.put("Food Name", food);
                dataMap.put("Diet Plan", diet);
                dataMap.put("Meal Plan", meal);

                reference.push().setValue(dataMap);
                Toast.makeText(diet_planner.this, "New Diet plan added", Toast.LENGTH_SHORT).show();

//                getPlan(); //function to retrieve from database
            }
        });

    }

}