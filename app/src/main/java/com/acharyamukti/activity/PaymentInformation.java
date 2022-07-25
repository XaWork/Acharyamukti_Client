package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.helper.Backend;
import com.google.gson.JsonObject;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


public class PaymentInformation extends AppCompatActivity implements PaymentResultListener, View.OnClickListener {
    String mobile, email_id;
    String orderId;
    String paymentId;
    Button processToPay;
    String userAmount;
    int num3;
    float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        processToPay = findViewById(R.id.processToPay);
        processToPay.setOnClickListener(this);
        email_id = Backend.getInstance(this).getEmail();
        mobile = Backend.getInstance(this).getMobile();
        Intent intent=getIntent();
        userAmount=intent.getStringExtra("balance");
        num3 = Integer.parseInt (userAmount);
        result = Math.round ((num3 / 18.0) * 100);

//        int gst=18;
//        float amount=(gst*(userAmount))/100;
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("order_id", orderId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonParams.put("razorpay_payment_id", paymentId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonParams.put("razorpay_order_id", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonParams.put("razorpay_signature", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_live_I78MvAvaK8xapT");
        double amount = Double.parseDouble(userAmount) * 100.00;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Amrita");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //  options.put("order_id", "000010"); //from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", result);//pass amount in currency subunits

            JSONObject prefill = new JSONObject();
            prefill.put("email", email_id);
            prefill.put("contact", mobile);
            options.put("prefill", prefill);

            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(PaymentInformation.this, options);

        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(PaymentInformation.this, "Payment Success", Toast.LENGTH_LONG).show();
        Log.d("paymentId", "razorpayPaymentID");
        // paymentUpdateStatus(orderId, "razorpayPaymentID");

    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(PaymentInformation.this, "somthing went wrong", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        startPayment();
    }
}