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

public class shirt1 extends AppCompatActivity {

    int quantity = 0;
    private Button submitorder1, ConfirmBtn;
    private DatabaseReference reference;
    private CheckBox small_checkbox, medium_checkbox, large_checkbox;
    private TextView shirtTextview, quantity_text_view, Order_summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shirt1);

        submitorder1 = (Button) findViewById(R.id.orderBtn);
        ConfirmBtn = (Button) findViewById(R.id.ConfirmBtn);
        quantity_text_view = (TextView) findViewById(R.id.quantity_text_view);
        Order_summary = (TextView) findViewById(R.id.Order_summary);
        shirtTextview = (TextView) findViewById(R.id.shirtTextview);
        small_checkbox = (CheckBox) findViewById(R.id.small_checkbox);
        medium_checkbox = (CheckBox) findViewById(R.id.medium_checkbox);
        large_checkbox = (CheckBox) findViewById(R.id.large_checkbox);
        reference = FirebaseDatabase.getInstance().getReference("Shopping");

        ConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shirt3 = shirtTextview.getText().toString();
                String quantity = quantity_text_view.getText().toString();
                String small = small_checkbox.getText().toString();
                String medium = medium_checkbox.getText().toString();
                String large = large_checkbox.getText().toString();
                String order = Order_summary.getText().toString();

                HashMap<String, String > dataMap = new HashMap<String, String>();
                dataMap.put("Item", shirt3);
                dataMap.put("Item details", order);

                reference.push().setValue(dataMap);
                //reference.child("Apparels").setValue("shirt3");
                Intent intent = new Intent(shirt1.this, paymentpage.class);
                startActivity(intent);
            }
        });

        submitorder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox smallCheckBox = (CheckBox) findViewById(R.id.small_checkbox);
                boolean hasSmall = smallCheckBox.isChecked();

                CheckBox regularCheckBox = (CheckBox) findViewById(R.id.medium_checkbox);
                boolean hasMedium = regularCheckBox.isChecked();

                CheckBox largeCheckBox = (CheckBox) findViewById(R.id.large_checkbox);
                boolean hasLarge = largeCheckBox.isChecked();

                int price = calculatePrice(hasSmall, hasMedium, hasLarge);

                String priceMessage = createOrderSummary(price, hasSmall, hasMedium, hasLarge);
//                priceMessage = priceMessage + ". \nAdd To Cart?";
                displayMessage(priceMessage);
            }
        });
    }

    private String createOrderSummary(int price, boolean addSmall, boolean addMedium, boolean addLarge) {
        String message = "\nAdd Ons: (Should show true or false)";
        message += "\nSmall? " + addSmall;
        message += "\nMedium? " + addMedium;
        message += "\nLarge? " + addLarge;
        message += "\nQuantity: " + quantity;
        message += "\nTotal: $" + price;
        return message;

    }

    private int calculatePrice(boolean addSmall1, boolean addMedium1, boolean addLarge1) {
        int basePrice = 9;

        if (addSmall1) {
            basePrice = basePrice + 0;
        }

        if (addMedium1) {
            basePrice = basePrice + 0;
        }

        if (addLarge1)
            basePrice = basePrice + 0;


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



