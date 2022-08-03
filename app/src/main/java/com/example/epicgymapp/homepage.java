package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //direct to homepage function
        ImageButton directHome = (ImageButton)findViewById(R.id.directHome);
        directHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText( homepage.this, "Go to Homepage", Toast.LENGTH_SHORT).show();
            }
        });
        //direct to gympage function
        ImageButton directGym = (ImageButton)findViewById(R.id.directGym);
        directGym.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText( homepage.this, "Go to Gympage", Toast.LENGTH_SHORT).show();
            }
        });
        //direct to QR page function
        ImageButton directQR = (ImageButton)findViewById(R.id.directQR);
        directQR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText( homepage.this, "Go to QRpage", Toast.LENGTH_SHORT).show();
            }
        });
        //direct to plan page function
        ImageButton directPlan = (ImageButton)findViewById(R.id.directPlan);
        directPlan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText( homepage.this, "Go to Plan page", Toast.LENGTH_SHORT).show();
            }
        });
        //direct to shop page function
        ImageButton directShop = (ImageButton)findViewById(R.id.directShop);
        directShop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText( homepage.this, "Go to Shop page", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
