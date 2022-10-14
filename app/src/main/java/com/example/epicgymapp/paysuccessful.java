package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class paysuccessful extends AppCompatActivity {

    private Button paymentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paysuccessful);

        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openshoppage();
            }
        });
    }

    public void openshoppage(){
        Intent intent = new Intent(this, shoppage.class);
        startActivity(intent);
    }

}