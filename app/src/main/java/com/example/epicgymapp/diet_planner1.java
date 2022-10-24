package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class diet_planner1 extends AppCompatActivity {
    static ArrayList<String> notes = new ArrayList<String>();
    static ArrayAdapter<String> arrayAdapter;
    private TextView backTextview;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.add_note)
        {
            Intent intent = new Intent(getApplicationContext(), diet_planner.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_planner1);
        Button addPlan = (Button) findViewById(R.id.addPlan);
        ListView listView = (ListView)findViewById(R.id.listView);
        backTextview = (TextView) findViewById(R.id.backTextview);

        backTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(diet_planner1.this, planpage.class);
                startActivity(intent);
            }
        });

        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {  openDietPlan(); }
            }
        });

        //edit and delete plan
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(arrayAdapter);

        //double click to edit data
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), diet_planner.class);
                intent.putExtra("noteID", position);
                startActivity(intent);
            }
        });

        //long click to alert user to delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                //alert user before delete
                new AlertDialog.Builder(diet_planner1.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete?")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                notes.remove(position);
                                arrayAdapter.notifyDataSetChanged();

                            }
                        })

                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
    }

    public void openDietPlan(){
        Intent intent = new Intent(this, diet_planner.class);
        startActivity(intent);
    }
}