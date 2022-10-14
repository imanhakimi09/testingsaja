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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class shoppage extends AppCompatActivity {

    private Button paymentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppage);

        Button paymentBtn = (Button) findViewById(R.id.paymentBtn);
        paymentBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openpaymentpage();
            }
        });
    }

    public void openpaymentpage(){
        Intent intent = new Intent(this, paymentpage.class);
        startActivity(intent);
    }
}
