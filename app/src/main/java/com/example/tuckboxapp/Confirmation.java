package com.example.tuckboxapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuckboxapp.DataModelPackage.Cards;
import com.example.tuckboxapp.DataModelPackage.OrderedLuch;
import com.example.tuckboxapp.DataModelPackage.User;

import static com.example.tuckboxapp.PlaceOrder.EXTRA_ORDERED;

public class Confirmation extends Menu {

    TextView tvRgion,tvNote,tvOrdered,tvTime,tvAddress,tvPayment,tvTitle,tvFname,tvLname,tvMobile;
    long insertionResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        user =(User) getIntent().getSerializableExtra(MainActivity.USER_OBJECT);
        Intent i = getIntent();
        String region =i.getStringExtra(RegionAndDeliveryTime.EXTRA_REGION);
        String time = i.getStringExtra(RegionAndDeliveryTime.EXTRA_TIME);
        String note = i.getStringExtra(PlaceOrder.EXTRA_NOTE);
        String order = i.getStringExtra(EXTRA_ORDERED);
        String address = i.getStringExtra(OrderAddress.EXTRA_ADDRESS);
        String payment = i.getStringExtra(OrderPayment.EXTRA_CARD);


        tvRgion =findViewById(R.id.con_region);
        tvNote =findViewById(R.id.con_note);
        tvTime =findViewById(R.id.con_time);
        tvAddress =findViewById(R.id.con_address);
        tvPayment = findViewById(R.id.con_payment);
        tvOrdered =findViewById(R.id.con_ordered);
        tvTitle=findViewById(R.id.title_con);
        tvFname=findViewById(R.id.fname_con);
        tvLname=findViewById(R.id.lname_con);
        tvMobile=findViewById(R.id.con_mobile);


        tvTitle.setText(user.getTitle());
        tvFname.setText(user.getFirstName());
        tvLname.setText(user.getLastName());
        tvMobile.setText(user.getMobileNumber());
        tvOrdered.setText(order);
        tvRgion.setText(region);
        tvNote.setText(note);
        tvTime.setText(time);
        tvAddress.setText(address);
        tvPayment.setText(payment);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Order");
        builder.setMessage("Are you confirm this order and make a payment now ?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OrderedLuch orderedLuch = new OrderedLuch();
                orderedLuch.setRegion(tvRgion.getText().toString());
                orderedLuch.setMeal(tvOrdered.getText().toString());
                orderedLuch.setNote(tvNote.getText().toString());
                orderedLuch.setPayment(tvPayment.getText().toString());
                orderedLuch.setDeliveryTime(tvTime.getText().toString());
                orderedLuch.setAddress(tvAddress.getText().toString());
                orderedLuch.setID(user.getID());

                InsertOrdered insertAddress = new InsertOrdered();
                insertAddress.execute(orderedLuch);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),
                        "Not confirm yet! Please confirm it ASAP then we will deliver to you soon!", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    public class InsertOrdered extends AsyncTask<OrderedLuch,Void,Void> {

        @Override
        protected Void doInBackground(OrderedLuch... orderedLuches) {
            insertionResult = MainActivity.userDao.insertOrderedLunch(orderedLuches[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(insertionResult == -1){
                Toast.makeText(getApplicationContext(),
                        "Error, please confirm it again.", Toast.LENGTH_LONG).show();
            } else {
                confirmOrder();
            }
        }
    }

    private void cancelOrder(){
        finish();
        Intent i = new Intent(this,AppServices.class);
        i.putExtra(MainActivity.USER_OBJECT,user);
        startActivity(i);
        Toast.makeText(getApplicationContext(),
                "Order Cancellation Successfully!",
                Toast.LENGTH_LONG).show();
    }

    private void confirmOrder(){
        Intent i = new Intent(this,SplashPaymentScreen.class);
        i.putExtra(MainActivity.USER_OBJECT,user);
        startActivity(i);
    }
}
