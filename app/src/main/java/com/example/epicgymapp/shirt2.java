package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class shirt2 extends AppCompatActivity {
    int quantity = 0;
    private Button submitorder2, ConfirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shirt2);

        submitorder2 = (Button) findViewById(R.id.orderBtn);
        ConfirmBtn = (Button) findViewById(R.id.ConfirmBtn);

        ConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shirt2.this, paymentpage.class);
                startActivity(intent);
            }
        });
        submitorder2.setOnClickListener(new View.OnClickListener() {
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
