package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bookingpage extends AppCompatActivity {
    private TextView backTextview;
    private Button trainer1, trainer2, trainer3, trainer4;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingpage);
        backTextview = (TextView) findViewById(R.id.backTextview);
        trainer1 = (Button) findViewById(R.id.trainer1);
        trainer2 = (Button) findViewById(R.id.trainer2);
        trainer3 = (Button) findViewById(R.id.trainer3);
        trainer4 = (Button) findViewById(R.id.trainer4);
        reference = FirebaseDatabase.getInstance().getReference("Booking");

        backTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookingpage.this, qrpage.class);
                startActivity(intent);
            }
        });

        trainer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("BookedTrainer").setValue("Booked with trainer1");
                Intent intent = new Intent(bookingpage.this, bookingPage2.class);
                startActivity(intent);
            }
        });

        trainer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("BookedTrainer").setValue("Booked with trainer2");
                Intent intent = new Intent(bookingpage.this, bookingPage2.class);
                startActivity(intent);
            }
        });

        trainer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("BookedTrainer").setValue("Booked with trainer3");
                Intent intent = new Intent(bookingpage.this, bookingPage2.class);
                startActivity(intent);
            }
        });

        trainer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("BookedTrainer").setValue("Booked with trainer4");
                Intent intent = new Intent(bookingpage.this, bookingPage2.class);
                startActivity(intent);
            }
        });
    }
}