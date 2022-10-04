package com.example.epicgymapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class qrpage extends AppCompatActivity {
    private Button scanQR, scanOut;
    private TextView attendance, timerTextview;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpage);
        reference = FirebaseDatabase.getInstance().getReference("AttendGym");
        scanQR = (Button) findViewById(R.id.scanQR);
        scanOut = (Button) findViewById(R.id.scanOut);
        attendance = (TextView) findViewById(R.id.attendance);
        timerTextview = (TextView) findViewById(R.id.timerTextview);

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
//                        {
//                            scanqrcode();
//                        }

//                        ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
//                            if(result.getContents() != null)
//                            {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(qrpage.this);
//                                builder.setTitle("Welcome");
//                                builder.setMessage(result.getContents());
//                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        dialogInterface.dismiss();
//                                    }
//                                }).show();
//                            }
//                        });
                        //set attendance status
                        String attend = attendance.getText().toString().trim();
                        reference.child("Present").setValue("Yes");
                        attendance.setText("Status: Present");
                        Toast.makeText(qrpage.this, "Welcome to EpicGym", Toast.LENGTH_LONG).show();

                        //timer
                        long duration = TimeUnit.MINUTES.toMillis(1);
                        new CountDownTimer(duration, 1000) {
                            @Override
                            public void onTick(long l) {
                                String timerCountdown = String.format(Locale.ENGLISH, "%02d : %02d",
                                        TimeUnit.MILLISECONDS.toMinutes(l),
                                        TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                                timerTextview.setText(timerCountdown);
                            }

                            @Override
                            public void onFinish() {
                                reference.child("Present").setValue("No");
                                attendance.setText("Attendance status");
                                Toast.makeText(qrpage.this, "Time's Up! Your session is over.", Toast.LENGTH_LONG).show();
                            }
                        }.start();
                    }
//                    //scan function
//                    public void scanqrcode(){
//                        ScanOptions options = new ScanOptions();
//                        options.setPrompt("Volume up to use flash");
//                        options.setBeepEnabled(true);
//                        options.setOrientationLocked(true);
//                        options.setCaptureActivity(CaptureAct.class);
//                        barLauncher.launch(options);
//                    }
//                    //scan function
//                    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
//                            if(result.getContents() != null)
//                            {
//                                AlertDialog.Builder builder3 = new AlertDialog.Builder(qrpage.this);
//                                builder3.setTitle("Welcome");
//                                builder3.setMessage(result.getContents());
//                                builder3.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        dialogInterface.dismiss();
//                                    }
//                                }).show();
//                            }
//                        });
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
                        reference.child("Present").setValue("No");
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