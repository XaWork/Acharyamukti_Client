package com.acharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.acharyamukti.R;
import com.razorpay.Checkout;

import org.json.JSONObject;

import java.util.Objects;


public class PaymentInformation extends AppCompatActivity implements View.OnClickListener {
    private Button paymentsProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        paymentsProcess = findViewById(R.id.paymentsProcess);
        paymentsProcess.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    public void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_live_hoOYE8lF0W3nZo");
        String intent_total_amt = null;
        double amount = Double.parseDouble(intent_total_amt) * 100.00;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Parchun");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //  options.put("order_id", "000010"); //from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amount);//pass amount in currency subunits

            JSONObject prefill = new JSONObject();
            prefill.put("email", emailid);
            prefill.put("contact", mEdt_mobielno.getText().toString());
            options.put("prefill", prefill);

            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(PaymentMode.this, options);

        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }
}