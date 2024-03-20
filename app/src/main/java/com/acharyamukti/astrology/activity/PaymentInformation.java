package com.acharyamukti.astrology.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.acharyamukti.R;
import com.acharyamukti.astrology.api.RetrofitClient;
import com.acharyamukti.astrology.helper.Backend;
import com.acharyamukti.astrology.model.DataModel;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentInformation extends AppCompatActivity implements PaymentResultListener, View.OnClickListener {
    String mobile, email_id;
    String orderId;
    String paymentId, userid;
    Button processToPay;
    String userAmount, totalGstAmount, profile_name;
    int num3;
    TextView amount, gst, totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        processToPay = findViewById(R.id.processToPay);
        processToPay.setOnClickListener(this);
        email_id = Backend.getInstance(this).getEmail();
        mobile = Backend.getInstance(this).getMobile();
        profile_name = Backend.getInstance(this).getName();
        userid = Backend.getInstance(this).getUserId();
        Intent intent = getIntent();
        userAmount = intent.getStringExtra("balance");
        amount = findViewById(R.id.amount);
        gst = findViewById(R.id.gst);
        totalAmount = findViewById(R.id.totalAmount);
        num3 = Integer.parseInt(userAmount);
        amount.setText(userAmount);
        num3 = Integer.parseInt(userAmount);
        double sum = (18 * num3) / 100;
        String totalGST = Double.toString(sum);
        double totalSum = sum + num3;
        totalGstAmount = Double.toString(totalSum);
        totalAmount.setText(totalGstAmount);
        gst.setText(totalGST);
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
        double amount = Double.parseDouble(totalGstAmount) * 100.00;
        try {
            JSONObject options = new JSONObject();
            options.put("name", profile_name);
            options.put("description", userid);
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //  options.put("order_id", "000010"); //from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amount);//pass amount in currency subunits

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
        postPaymentDetails(s);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(PaymentInformation.this, "something went wrong", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        startPayment();
    }

    private void postPaymentDetails(String transactionId) {
        String userId = Backend.getInstance(this).getUserId();
        Call<DataModel> call = RetrofitClient.getInstance().getApi().postPaymentDetails(userId, userAmount, transactionId);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NonNull Call<DataModel> call, @NonNull Response<DataModel> response) {
                DataModel dataModel = response.body();
                if (response.isSuccessful()) {
                    assert dataModel != null;

                    Log.e("payment", dataModel.toString());

                    if (dataModel.getError().equals("false")) {
                        //Toast.makeText(PaymentInformation.this, dataModel.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(PaymentInformation.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataModel> call, @NonNull Throwable t) {
                Toast.makeText(PaymentInformation.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}