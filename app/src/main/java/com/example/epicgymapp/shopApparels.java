package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class shopApparels extends AppCompatActivity {
    private TextView backTextview;

    private Button  shirt1Button, shirt2Button, shirt3Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_apparels);
        backTextview = (TextView) findViewById(R.id.backTextview);

        shirt1Button = (Button) findViewById(R.id.shirt1Button);
        shirt2Button = (Button) findViewById(R.id.shirt2Button);
        shirt3Button = (Button) findViewById(R.id.shirt3Button);


        backTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopApparels.this, shoppage.class);
                startActivity(intent);
            }
        });

        shirt1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopApparels.this, shirt1.class);
                startActivity(intent);
            }
        });

        shirt2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopApparels.this, shirt2.class);
                startActivity(intent);
            }
        });

        shirt3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopApparels.this, shirt3.class);
                startActivity(intent);
            }
        });
    }
}