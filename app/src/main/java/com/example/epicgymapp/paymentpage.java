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

import java.util.HashMap;

public class paymentpage extends AppCompatActivity {


    private EditText cardNumber;
    private EditText expDate;
    private EditText cardCvv;
    private EditText postalCode;
    private EditText phoneNumber;
    private EditText amount;
    private Button payBtn;
    private DatabaseReference reference;

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

        //pay now button and validations
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validation
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
                    phoneNumber.setError("Phone number is required");
                    phoneNumber.requestFocus();
                    return;
                }
                if (phone.length() > 7){
                    phoneNumber.setError("Phone number must be 7 digits");
                    phoneNumber.requestFocus();
                    return;
                }

                //amount to be paid
                if (amt.isEmpty()){
                    amount.setError("Please enter amount to be paid");
                    amount.requestFocus();
                    return;
                }

                //alert user after clicking Pay button
                AlertDialog.Builder builder = new AlertDialog.Builder(paymentpage.this);
                builder.setTitle("Proceed payment");
                builder.setMessage("Are these details correct?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

//                        reference.child("Paid").setValue("Yes");
                        HashMap<String, String > dataMap = new HashMap<String, String>();
                        dataMap.put("Card No", cardnum);
                        dataMap.put("Expiry Date", expiryDate);
                        dataMap.put("CVV", cvv);
                        dataMap.put("Postal Code", pCode);
                        dataMap.put("Phone No", phone);
                        dataMap.put("Amount", amt);

                        reference.push().setValue(dataMap);
                        Toast.makeText(paymentpage.this, "Your purchase was successful!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(paymentpage.this, paysuccessful.class);
                        intent.putExtra("amount", amt);
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

            }
        });


    }
}



//Paypal integration (not working)
//import androidx.activity.result.ActivityResult;
//        import androidx.activity.result.ActivityResultCallback;
//        import androidx.activity.result.ActivityResultLauncher;
//        import androidx.activity.result.contract.ActivityResultContract;
//        import androidx.activity.result.contract.ActivityResultContracts;
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.app.Activity;
//        import android.app.AlertDialog;
//        import android.content.Intent;
//        import android.content.IntentFilter;
//        import android.os.Bundle;
//        import android.text.Editable;
//        import android.text.InputType;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.Toast;
//
//        import com.braintreepayments.cardform.view.CardForm;
//        import com.paypal.android.sdk.payments.PayPalConfiguration;
//        import com.paypal.android.sdk.payments.PayPalPayment;
//        import com.paypal.android.sdk.payments.PayPalPaymentDetails;
//        import com.paypal.android.sdk.payments.PayPalService;
//        import com.paypal.android.sdk.payments.PaymentConfirmation;
//
//        import org.json.JSONException;
//
//        import java.math.BigDecimal;
//
//public class PaymentActivity extends AppCompatActivity {
//
//    public static final int PAYPAL_REQUEST_CODE = 123;
//
//    public static final String clientKey = "AT2EdKAsUnhKzLQIj-t0vpD_1Ou-C81pjFDltC7wdMEMe7ivEIamUxe-TRk92wz0PsxkHnGX29Hr3_R_";
//
//    //PayPal config object
//    private static PayPalConfiguration config = new PayPalConfiguration()
//            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX) //use Sandbox bc we on Test
//            .clientId(clientKey); //pass on the clientKey
//
//    Button btnPayNow;
//    EditText edtAmount;
//
//    String amount = "";
//
//    @Override
//    protected void onDestroy() {
//        stopService(new Intent(this, PayPalService.class));
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_payment);
//
//        //Start Poypal Service
//        Intent intent = new Intent(this, PayPalService.class);
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//        startService(intent);
//
//        //buttons
//        btnPayNow = (Button)findViewById(R.id.btnPayNow);
//        edtAmount = (EditText) findViewById(R.id.edtAmount);
//
//        //call the function
//        btnPayNow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                processPayment();
//            }
//        });
//    }
//
//    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result != null && result.getResultCode() == RESULT_OK){
//                if (result.getData() != null) {
//
//
//                }
//            }
//        }
//    });
//
//    private void processPayment(){
//
//        // Getting the amount from editText
//        String amount = edtAmount.getText().toString();
//
//        // Creating a paypal payment on below line.
//        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(amount)),"USD",
//                "Test Payment", PayPalPayment.PAYMENT_INTENT_SALE);
//
//        // Creating Paypal Payment activity intent
//        Intent intent = new Intent(this, PaymentActivity.class);
//
//        //putting the paypal configuration to the intent
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//
//        // Putting paypal payment to the intent
//        Intent Intent = new Intent(android.content.Intent.ACTION_CHOOSER);
//        Intent.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT, payment);
//        //startActivityForResult(Intent, PAYPAL_REQUEST_CODE);
//        startForResult.launch(intent); --> this is where it is deprecated
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PAYPAL_REQUEST_CODE){
//            if (resultCode == RESULT_OK){
//                PaymentConfirmation confirmation = data.getParcelableExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION);
//                if (confirmation != null){
//                    try {
//                        String paymentDetails = confirmation.toJSONObject().toString(4);
//
//                        startActivity(new Intent(this, PayPalPaymentDetails.class)
//                                .putExtra("PaymentDetails", paymentDetails)
//                                .putExtra("PaymentAmount", amount));
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            else if (resultCode == Activity.RESULT_CANCELED)
//                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
//        }
//        else if(resultCode == com.paypal.android.sdk.payments.PaymentActivity.RESULT_EXTRAS_INVALID)
//            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
//    }
//}
