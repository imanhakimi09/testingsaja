package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class diet_planner extends AppCompatActivity{
    private DatabaseReference reference;
    private Button textview;
    private EditText editText;
    int noteID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_planner);
        reference = FirebaseDatabase.getInstance().getReference("DietPlan");
        textview = (Button) findViewById(R.id.textView);
//        editText = (EditText) findViewById(R.id.editText);

        //add plan to database button
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //push data to database
                String newPlan = editText.getText().toString().trim();

                if(newPlan.isEmpty()){
                    editText.setError("Data cannot be empty");
                    editText.requestFocus();
                    return;
                }

                HashMap<String, String > dataMap = new HashMap<String, String>();
                dataMap.put("Diet Plan", newPlan);

                reference.push().setValue(dataMap);
                Toast.makeText(diet_planner.this, "New Diet plan added", Toast.LENGTH_SHORT).show();

                openDietPlan1();
            }
        });
        EditText editText = (EditText)findViewById(R.id.editText);
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);

        if(noteID != -1)
        {
            editText.setText(diet_planner1.notes.get(noteID));
        }

        else
        {
            diet_planner1.notes.add("");
            noteID = diet_planner1.notes.size() - 1;
            diet_planner1.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                diet_planner1.notes.set(noteID, String.valueOf(s));
                diet_planner1.arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }

    private void openDietPlan1() {
        Intent intent = new Intent(this, diet_planner1.class);
        startActivity(intent);
    }

}