package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class paymentpage extends AppCompatActivity {

    private EditText cardNumber;
    private EditText expDate;
    private EditText cardCvv;
    private EditText postalCode;
    private EditText phoneNumber;
    private EditText amount;
    private Button payBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentpage);

        DatabaseReference reference;

        reference = FirebaseDatabase.getInstance().getReference("Payment");

        cardNumber = (EditText) findViewById(R.id.cardNumb);
        expDate = (EditText) findViewById(R.id.expDate);
        cardCvv = (EditText) findViewById(R.id.cardCvv);
        postalCode = (EditText) findViewById(R.id.postalCode);
        phoneNumber = (EditText) findViewById(R.id.phoneNum);
        amount = (EditText) findViewById(R.id.amount);
        payBtn = (Button)findViewById((R.id.payBtn));

        //connect to database
        mAuth = FirebaseAuth.getInstance();

        //pay now button and validations
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //alert user after clicking Pay button
                AlertDialog.Builder builder = new AlertDialog.Builder(paymentpage.this);
                builder.setTitle("Proceed payment");
                builder.setMessage("Are these details correct?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        reference.child("Paid").setValue("No");
                        Toast.makeText(paymentpage.this, "Your purchase was successful!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(paymentpage.this, paysuccessful.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cancel payment
                        Toast.makeText(paymentpage.this, "Cancel payment", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();




                String cardnum = cardNumber.getText().toString();
                String expiryDate = expDate.getText().toString();
                String cvv = cardCvv.getText().toString();
                String pCode = postalCode.getText().toString();
                String phone = phoneNumber.getText().toString();
                String amt = amount.getText().toString();

                if (cardnum.isEmpty()){
                    cardNumber.setError("This field should not be empty!");
                    cardNumber.requestFocus();
                    return;
                }
                else if (cardnum.length() > 16){
                    cardNumber.setError("Max length is 16 characters.");
                    cardNumber.requestFocus();
                    return;
                }
                else if (cardnum.length() < 16){
                    cardNumber.setError("Please enter the 16 digits of your card");
                    cardNumber.requestFocus();
                    return;
                }

                //card expiry date
                if (expiryDate.isEmpty()){
                    expDate.setError("This field should not be empty!");
                    expDate.requestFocus();
                    return;
                }

                //card CVV
                if (cvv.isEmpty()){
                    cardCvv.setError("Please enter the CVV detail");
                    cardCvv.requestFocus();
                    return;
                }
                if (cvv.length() > 4){
                    cardCvv.setError("Error");
                    cardCvv.requestFocus();
                    return;
                }

                //postal Code
                if (pCode.isEmpty()){
                    postalCode.setError("Please enter the postal code");
                    postalCode.requestFocus();
                    return;
                }
                if (pCode.length() > 5){
                    postalCode.setError("Error");
                    postalCode.requestFocus();
                    return;
                }

                //phone
                if (phone.isEmpty()){
                    postalCode.setError("Phone number is required");
                    postalCode.requestFocus();
                    return;
                }
                if (phone.length() > 7){
                    postalCode.setError("Phone number must be 7 digits");
                    postalCode.requestFocus();
                    return;
                }

                //amount to be paid
                if (amt.isEmpty()){
                    amount.setError("Please enter amount to be paid");
                    amount.requestFocus();
                    return;
                }
            }
        });


    }
}