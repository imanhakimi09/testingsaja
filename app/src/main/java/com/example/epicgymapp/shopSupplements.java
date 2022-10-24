package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class shopSupplements extends AppCompatActivity {
    private TextView backTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_supplements);

        backTextview = (TextView) findViewById(R.id.backTextview);

        backTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopSupplements.this, shoppage.class);
                startActivity(intent);
            }
        });
    }
}