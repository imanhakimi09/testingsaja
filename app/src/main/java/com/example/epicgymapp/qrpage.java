package com.example.epicgymapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class qrpage extends AppCompatActivity {
private Button scanQR, scanOut;
private TextView attendance;
Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpage);

        scanQR = (Button) findViewById(R.id.scanQR);
        scanOut = (Button) findViewById(R.id.scanOut);
        attendance = (TextView) findViewById(R.id.attendance);

        //scan qr code function
        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //alert user before scan
                AlertDialog.Builder builder = new AlertDialog.Builder(qrpage.this);
                builder.setTitle("Scan QR code");
                builder.setMessage("Confirm?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        attendance.setText("Status: Present");
                        Toast.makeText(qrpage.this, "Welcome to EpicGym", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //cancel scan
                        Toast.makeText(qrpage.this, "Cancel Scan QR code", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

//                //timer function
//                timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(qrpage.this, "Times Up!", Toast.LENGTH_LONG).show();
//                        attendance.setText("Attendance status");
//                        finish();
//                    }
//                },5000);
            }
        });

        //scan out function
        scanOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //alert user before scan out
                AlertDialog.Builder builder = new AlertDialog.Builder(qrpage.this);
                builder.setTitle("Scan Out");
                builder.setMessage("Confirm?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        attendance.setText("Attendance status");
                        Toast.makeText(qrpage.this, "Thank You for coming!", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //cancel scan out
                        Toast.makeText(qrpage.this, "Cancel Scan Out", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}