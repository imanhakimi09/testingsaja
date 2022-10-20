package com.example.epicgymapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class membershippage extends AppCompatActivity {
    private TextView membershipText;
    private Button oneMonthMembership, threeMonthMembership, oneYearMembership;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membershippage);

        membershipText = (TextView) findViewById(R.id.membershipText);
        oneMonthMembership = (Button) findViewById(R.id.oneMonthMembership);
        threeMonthMembership = (Button) findViewById(R.id.threeMonthMembership);
        oneYearMembership = (Button) findViewById(R.id.oneYearMembership);
        reference = FirebaseDatabase.getInstance().getReference("Membership");

        membershipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(membershippage.this);
                builder.setTitle("Cancel Membership");
                builder.setMessage("Your membership is still valid. If you want continue deregister click below.");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        membershipText.setText("You are not a member!");
                        Toast.makeText(membershippage.this, "Membership cancelled", Toast.LENGTH_LONG).show();
                        reference.child("Membership Status").setValue("Non-member");
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(membershippage.this, "Cancel process", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        oneMonthMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(membershippage.this);
                builder.setTitle("Apply Membership");
                builder.setMessage("Register as member for 1 Month?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        membershipText.setText("Your membership is valid for 1 Month");
                        Toast.makeText(membershippage.this, "Registered successful", Toast.LENGTH_LONG).show();
                        reference.child("Membership Status").setValue("One Month");

                        //timer
                        long duration = TimeUnit.MINUTES.toMillis(1);
                        new CountDownTimer(duration, 1000) {
                            @Override
                            public void onTick(long l) {
                                String timerCountdown = String.format(Locale.ENGLISH, "%02d : %02d",
                                        TimeUnit.MILLISECONDS.toMinutes(l),
                                        TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                            }

                            @Override
                            public void onFinish() {
                                membershipText.setText("You are not a member!");
                                reference.child("Membership Status").setValue("Non-member");
                                Toast.makeText(membershippage.this, "Your membership plan has expired!", Toast.LENGTH_LONG).show();
                            }
                        }.start();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(membershippage.this, "Cancel process", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        threeMonthMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(membershippage.this);
                builder.setTitle("Apply Membership");
                builder.setMessage("Register as member for 3 Month?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        membershipText.setText("Your membership is valid for 3 Months");
                        Toast.makeText(membershippage.this, "Registered successful", Toast.LENGTH_LONG).show();
                        reference.child("Membership Status").setValue("Three Month");

                        //timer
                        long duration = TimeUnit.MINUTES.toMillis(1);
                        new CountDownTimer(duration, 1000) {
                            @Override
                            public void onTick(long l) {
                                String timerCountdown = String.format(Locale.ENGLISH, "%02d : %02d",
                                        TimeUnit.MILLISECONDS.toMinutes(l),
                                        TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                            }

                            @Override
                            public void onFinish() {
                                membershipText.setText("You are not a member!");
                                reference.child("Membership Status").setValue("Non-member");
                                Toast.makeText(membershippage.this, "Your membership plan has expired!", Toast.LENGTH_LONG).show();
                            }
                        }.start();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(membershippage.this, "Cancel process", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        oneYearMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(membershippage.this);
                builder.setTitle("Apply Membership");
                builder.setMessage("Register as member for 1 Year?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        membershipText.setText("Your membership is valid for 1 Year");
                        Toast.makeText(membershippage.this, "Registered successful", Toast.LENGTH_LONG).show();
                        reference.child("Membership Status").setValue("One Year");

                        //timer
                        long duration = TimeUnit.MINUTES.toMillis(1);
                        new CountDownTimer(duration, 1000) {
                            @Override
                            public void onTick(long l) {
                                String timerCountdown = String.format(Locale.ENGLISH, "%02d : %02d",
                                        TimeUnit.MILLISECONDS.toMinutes(l),
                                        TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                            }

                            @Override
                            public void onFinish() {
                                membershipText.setText("You are not a member!");
                                reference.child("Membership Status").setValue("Non-member");
                                Toast.makeText(membershippage.this, "Your membership plan has expired!", Toast.LENGTH_LONG).show();
                            }
                        }.start();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(membershippage.this, "Cancel process", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}