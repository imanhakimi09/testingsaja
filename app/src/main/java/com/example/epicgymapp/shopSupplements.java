package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class shopSupplements extends AppCompatActivity {
    private TextView backTextview;
    private Button medicine1Btn, medicine2Btn, medicine3Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_supplements);

        backTextview = (TextView) findViewById(R.id.backTextview);
        medicine1Btn= (Button) findViewById(R.id.medicine1Btn);
        medicine2Btn= (Button) findViewById(R.id.medicine2Btn);
        medicine3Btn= (Button) findViewById(R.id.medicine3Btn);

        backTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopSupplements.this, shoppage.class);
                startActivity(intent);
            }
        });

        medicine1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopSupplements.this, supplements1.class);
                startActivity(intent);
            }
        });

        medicine2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopSupplements.this, supplements2.class);
                startActivity(intent);
            }
        });

        medicine3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopSupplements.this, supplements3.class);
                startActivity(intent);
            }
        });
    }
}