package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class shopSupplements extends AppCompatActivity {
    private TextView backTextview;
    private ImageButton medicine1, medicine2, medicine3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_supplements);

        backTextview = (TextView) findViewById(R.id.backTextview);
        medicine1 = (ImageButton) findViewById(R.id.medicine1);
        medicine2 = (ImageButton) findViewById(R.id.medicine2);
        medicine3 = (ImageButton) findViewById(R.id.medicine3);

        backTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopSupplements.this, shoppage.class);
                startActivity(intent);
            }
        });

        medicine1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopSupplements.this, supplements1.class);
                startActivity(intent);
            }
        });

        medicine2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopSupplements.this, supplements2.class);
                startActivity(intent);
            }
        });

        medicine3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopSupplements.this, supplements3.class);
                startActivity(intent);
            }
        });
    }
}