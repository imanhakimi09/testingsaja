package com.example.epicgymapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class bookingPage2 extends AppCompatActivity {
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8;

    private TextView tvSelectDate;
    private EditText etSelectDate;
    private DatabaseReference reference;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page2);
        tvSelectDate = findViewById(R.id.tvSelectDate);
        etSelectDate = findViewById(R.id.etSelectDate);

        btnConfirm = findViewById(R.id.alertbtn);
        AlertDialog.Builder builder = new AlertDialog.Builder(bookingPage2.this);

        reference = FirebaseDatabase.getInstance().getReference("Booking");

        c1 = findViewById(R.id.time1);
        c2 = findViewById(R.id.time2);
        c3 = findViewById(R.id.time3);
        c4 = findViewById(R.id.time4);
        c5 = findViewById(R.id.time5);
        c6 = findViewById(R.id.time6);
        c7 = findViewById(R.id.time7);
        c8 = findViewById(R.id.time8);




        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c1.isChecked()) {
                    reference.child("time").setValue("10-11am");
                }else {
                }
                if (c2.isChecked()) {
                    reference.child("time").setValue("11-12am");
                }else {
                }
                if (c3.isChecked()) {
                    reference.child("time").setValue("1-2pm");
                }else {
                }
                if (c4.isChecked()) {
                    reference.child("time").setValue("2-3pm");
                }else {
                }
                if (c5.isChecked()) {
                    reference.child("time").setValue("3-4pm");
                }else {
                }
                if (c6.isChecked()) {
                    reference.child("time").setValue("4-5pm");
                }else {
                }
                if (c7.isChecked()) {
                    reference.child("time").setValue("5-6pm");
                }else {
                }
                if (c8.isChecked()) {
                    reference.child("time").setValue("6-7pm");
                }else {
                }

                builder.setTitle("Confirmation!")
                        .setMessage("Confirm your booking with this trainer?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        });


        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(bookingPage2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        etSelectDate.setText(date);

                    }
                }, year, month, day);
                dialog.show();
                reference.child("day").setValue(day );
                reference.child("month").setValue(month );
                reference.child("year").setValue(year );


            }
        });
    }
}
