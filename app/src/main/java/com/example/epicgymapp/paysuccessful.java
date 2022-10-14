package com.example.epicgymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class paysuccessful extends AppCompatActivity {

    private Button paymentBtn;
    private DatabaseReference reference;
    private String userId;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paysuccessful);
        TextView cardName1 = (TextView)findViewById(R.id.cardName1);
        TextView amountPaid1 = (TextView)findViewById(R.id.amountPaid1);
        TextView phonenumb1 = (TextView)findViewById(R.id.phonenumb1);
        Button done = (Button) findViewById(R.id.done);
        reference = FirebaseDatabase.getInstance().getReference("Payment");

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

                    cardName1.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(paysuccessful.this, "Failed to retrieve data!", Toast.LENGTH_LONG).show();
            }
        });


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