package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class supplements1 extends AppCompatActivity {
    int quantity = 0;
    private Button submitorder1, ConfirmBtn;
    private DatabaseReference reference;
    private CheckBox small_checkbox, large_checkbox;
    private TextView medicineTextview, quantity_text_view, Order_summary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplements1);
        submitorder1 = (Button) findViewById(R.id.orderBtn);
        ConfirmBtn = (Button) findViewById(R.id.ConfirmBtn);
        quantity_text_view = (TextView) findViewById(R.id.quantity_text_view);
        Order_summary = (TextView) findViewById(R.id.Order_summary);
        medicineTextview = (TextView) findViewById(R.id.medicineTextview);
        small_checkbox = (CheckBox) findViewById(R.id.small_checkbox);
        large_checkbox = (CheckBox) findViewById(R.id.large_checkbox);
        reference = FirebaseDatabase.getInstance().getReference("Shopping");

        ConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String meds1 = medicineTextview.getText().toString();
                String quantity = quantity_text_view.getText().toString();
                String small = small_checkbox.getText().toString();
                String large = large_checkbox.getText().toString();
                String order = Order_summary.getText().toString();

                HashMap<String, String > dataMap = new HashMap<String, String>();
                dataMap.put("Item", meds1);
                dataMap.put("Item details", order);

                reference.push().setValue(dataMap);
                //reference.child("Apparels").setValue("shirt3");
                Intent intent = new Intent(supplements1.this, paymentpage.class);
                startActivity(intent);
            }
        });

        submitorder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox smallCheckBox = (CheckBox) findViewById(R.id.small_checkbox);
                boolean hasSmall = smallCheckBox.isChecked();

                CheckBox largeCheckBox = (CheckBox) findViewById(R.id.large_checkbox);
                boolean hasLarge = largeCheckBox.isChecked();

                int price = calculatePrice(hasSmall, hasLarge);

                String priceMessage = createOrderSummary(price, hasSmall, hasLarge);
//                priceMessage = priceMessage + ". \nAdd To Cart?";
                displayMessage(priceMessage);
            }
        });
    }

    private String createOrderSummary(int price, boolean addSmall, boolean addLarge) {
        String message = "\nAdd Ons: (Should show true or false)";
        message += "\nSmall? " + addSmall;
        message += "\nLarge? " + addLarge;
        message += "\nQuantity: " + quantity;
        message += "\nTotal: $" + price;
        return message;

    }

    private int calculatePrice(boolean addSmall1, boolean addLarge1) {
        int basePrice = 20;

        if (addSmall1) {
            basePrice = basePrice + 0;
        }

        if (addLarge1)
            basePrice = basePrice + 15;


        return quantity * basePrice;
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.Order_summary);
        priceTextView.setText(message);
    }

    public void increment(View view) {
        if (quantity == 30) {
            Toast.makeText(this,"Maximum quantity is 10", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view){
        if (quantity == 1) {
            Toast.makeText(this,"Minimum quantity is 1", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);

    }
}