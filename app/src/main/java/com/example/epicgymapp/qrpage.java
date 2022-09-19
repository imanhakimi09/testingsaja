package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class qrpage extends AppCompatActivity {
private Button scanQR;
Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpage);

        scanQR = (Button) findViewById(R.id.scanQR);

        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //timer function
//                timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(qrpage.this, "Welcome to EpicGym!", Toast.LENGTH_LONG).show();
//                        finish();
//                    }
//                },5000);
            }
        });
    }
}