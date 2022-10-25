package com.example.epicgymapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class qrpage extends AppCompatActivity {
    private Button scanQR, scanOut;
    private TextView attendance, timerTextview;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String userId;
    private TextView textView2;
    private ImageButton bookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpage);
        reference = FirebaseDatabase.getInstance().getReference("AttendGym");
        scanQR = (Button) findViewById(R.id.scanQR);
        scanOut = (Button) findViewById(R.id.scanOut);
        attendance = (TextView) findViewById(R.id.attendance);
        timerTextview = (TextView) findViewById(R.id.timerTextview);
        textView2 = (TextView) findViewById(R.id.textView2);
        bookBtn = (ImageButton) findViewById(R.id.bookBtn);

        //retrieve data
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("New Users");
        userId = user.getUid();

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                //retrieve and display user profile
                if(userProfile != null){
                    String name = userProfile.name;

                    textView2.setText("Hello " + name + ",");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(qrpage.this, "Failed to retrieve data!", Toast.LENGTH_LONG).show();
            }
        });

        //navigate to book trainer
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(qrpage.this, bookingpage.class);
                startActivity(intent);
            }
        });

        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanning();
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

        //direct to homepage button function
        ImageButton directHome = (ImageButton)findViewById(R.id.directHome);
        directHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openHomepage(); }
            }
        });
        //direct to userprofile button function
        ImageButton directGym = (ImageButton)findViewById(R.id.directGym);
        directGym.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openUserProfile(); }
            }
        });
        //direct to QR page button function
        ImageButton directQR = (ImageButton)findViewById(R.id.directQR);
        directQR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText( qrpage.this, "This is QR page", Toast.LENGTH_SHORT).show();
            }
        });
        //direct to plan page button function
        ImageButton directPlan = (ImageButton)findViewById(R.id.directPlan);
        directPlan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openPlanpage(); }
            }
        });
        //direct to shop page button function
        ImageButton directShop = (ImageButton)findViewById(R.id.directShop);
        directShop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                {  openshoppage(); }
            }
        });
    }

    //direct to homepage
    public void openHomepage(){
        Intent intent = new Intent(this, homepage.class);
        startActivity(intent);
    }
    //direct to userprofile
    public void openUserProfile(){
        Intent intent = new Intent(this, userprofile.class);
        startActivity(intent);
    }
    //direct to planner
    public void openPlanpage(){
        Intent intent = new Intent(this, planpage.class);
        startActivity(intent);
    }
    //direct to shopping
    public void openshoppage(){
        Intent intent = new Intent(this, shoppage.class);
        startActivity(intent);
    }

    //scan function
    private void scanning() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to use flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);

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
    //scan function
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null)
        {
            AlertDialog.Builder builder3 = new AlertDialog.Builder(qrpage.this);
            builder3.setTitle("Welcome");
            builder3.setMessage(result.getContents());
            builder3.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });

}