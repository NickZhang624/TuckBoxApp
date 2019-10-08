package com.example.tuckboxapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Confirmation extends Menu {

    TextView tvRgion,tvNote,tvTime,tvAddress,tvPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Intent i = getIntent();
        String region =i.getStringExtra(RegionAndDeliveryTime.EXTRA_REGION);
        String note = i.getStringExtra(PlaceOrder.EXTRA_NOTE);
        String time = i.getStringExtra(RegionAndDeliveryTime.EXTRA_TIME);
        String address = i.getStringExtra(OrderAddress.EXTRA_ADDRESS);
        String payment = i.getStringExtra(OrderPayment.EXTRA_CARD);


        tvRgion =findViewById(R.id.con_region);
        tvNote =findViewById(R.id.con_note);
        tvTime =findViewById(R.id.con_time);
        tvAddress =findViewById(R.id.con_address);
        tvPayment = findViewById(R.id.con_payment);


        tvRgion.setText("Delivery Region: " + region);
        tvNote.setText("Customer Notes: " + note);
        tvTime.setText("Delivery Time: " + time);
        tvAddress.setText("Deliver Address: " + address);
        tvPayment.setText("Payment Way: " + payment);
    }

    public void confirmCancelButtonClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order Cancellation");
        builder.setMessage("Are you want to cancel this order  ?");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelOrder();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void finalConfirmButtonClicked(View view) {
        Intent i = new Intent(this,AppServices.class);
        startActivity(i);
    }

    private void cancelOrder(){
        finish();
        Intent i = new Intent(this,AppServices.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(),
                "Order Cancellation Successfully!",
                Toast.LENGTH_LONG).show();
    }
}
