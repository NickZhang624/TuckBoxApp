package com.example.tuckboxapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tuckboxapp.DataModelPackage.User;

public class UserUpdateInfo extends AppCompatActivity {
    User user;
    EditText etUPFname,etUPLname,etUPUname,etUPPassword,etUPMobile,etUPEmail,
            etUPDeliveryAddress,etUPCard,etUPExpiredDate,etID;
    Spinner etUPTitle;

    int updateResult,deleteResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_info);

        user =(User) getIntent().getSerializableExtra(MainActivity.USER_OBJECT);
        updateResult = -1;
        deleteResult = -1;

        //etUPTitle=findViewById(R.id.spinner_up_title);
        etID= findViewById(R.id.et_id);
        etUPFname=findViewById(R.id.edit_up_first_name);
        etUPLname=findViewById(R.id.edit_up_last_name);
        etUPUname=findViewById(R.id.edit_up_user_name);
        etUPPassword=findViewById(R.id.edit_up_password);
        etUPMobile=findViewById(R.id.edit_up_mob_num);
        etUPEmail=findViewById(R.id.edit_up_email);
        etUPDeliveryAddress=findViewById(R.id.edit_up_adress);
        etUPCard=findViewById(R.id.edit_up_credit_card);
        etUPExpiredDate=findViewById(R.id.edit_up_expired_date);

        etID.setText(String.valueOf(user.getID()));
        etID.setEnabled(false);

        etUPFname.setText(user.getFirstName());
        etUPLname.setText(user.getLastName());
        etUPUname.setText(user.getUserName());
        etUPPassword.setText(user.getPassword());
        etUPMobile.setText(user.getMobileNumber());
        etUPEmail.setText(user.getEmail());
        etUPDeliveryAddress.setText(user.getDeliveryAddress());
        etUPCard.setText(user.getCreditNumber());
        etUPExpiredDate.setText(user.getExpiredDate());

        setUIBehaviors();
    }

    private void setUIBehaviors() {
//        etUPTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(!etUPTitle.getSelectedItem().toString().equals(user.getTitle())){
//                    user.setTitle(etUPTitle.getSelectedItem().toString());
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        etUPFname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    // edit text is leaving the focus of the user
                    if(!etUPFname.getText().toString().equals(user.getFirstName())){
                        // user has changed the info in the input field
                        user.setFirstName(etUPFname.getText().toString());
                    }
                } else
                    etUPFname.selectAll();
            }
        });
        etUPLname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    // edit text is leaving the focus of the user
                    if(!etUPLname.getText().toString().equals(user.getLastName())){
                        // user has changed the info in the input field
                        user.setLastName(etUPLname.getText().toString());
                    }
                } else
                    etUPLname.selectAll();
            }
        });
        etUPUname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    // edit text is leaving the focus of the user
                    if(!etUPUname.getText().toString().equals(user.getUserName())){
                        // user has changed the info in the input field
                        user.setUserName(etUPUname.getText().toString());
                    }
                } else
                    etUPUname.selectAll();
            }
        });
        etUPPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    // edit text is leaving the focus of the user
                    if(!etUPPassword.getText().toString().equals(user.getPassword())){
                        // user has changed the info in the input field
                        user.setPassword(etUPPassword.getText().toString());
                    }
                } else
                    etUPPassword.selectAll();
            }
        });
        etUPMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    // edit text is leaving the focus of the user
                    if(!etUPMobile.getText().toString().equals(user.getMobileNumber())){
                        // user has changed the info in the input field
                        user.setMobileNumber(etUPMobile.getText().toString());
                    }
                } else
                    etUPMobile.selectAll();
            }
        });
        etUPEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    // edit text is leaving the focus of the user
                    if(!etUPEmail.getText().toString().equals(user.getEmail())){
                        // user has changed the info in the input field
                        user.setEmail(etUPEmail.getText().toString());
                    }
                } else
                    etUPEmail.selectAll();
            }
        });
        etUPDeliveryAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    // edit text is leaving the focus of the user
                    if(!etUPDeliveryAddress.getText().toString().equals(user.getDeliveryAddress())){
                        // user has changed the info in the input field
                        user.setDeliveryAddress(etUPDeliveryAddress.getText().toString());
                    }
                } else
                    etUPDeliveryAddress.selectAll();
            }
        });
        etUPCard.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    // edit text is leaving the focus of the user
                    if(!etUPCard.getText().toString().equals(user.getCreditNumber())){
                        // user has changed the info in the input field
                        user.setCreditNumber(etUPCard.getText().toString());
                    }
                } else
                    etUPCard.selectAll();
            }
        });
        etUPExpiredDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    // edit text is leaving the focus of the user
                    if(!etUPExpiredDate.getText().toString().equals(user.getExpiredDate())){
                        // user has changed the info in the input field
                        user.setExpiredDate(etUPExpiredDate.getText().toString());
                    }
                } else
                    etUPExpiredDate.selectAll();
            }
        });

    }


    public void updateCancelButtonClicked(View view) {
        finishActivity();
    }


    public void updateButtonClicked(View view) {
        new UpdateUser().execute(user);
    }

    private class UpdateUser extends AsyncTask<User,Void,Void> {

        @Override
        protected Void doInBackground(User... users) {
            updateResult = MainActivity.userDao.updateUser(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(updateResult != -1){
                Toast.makeText(getApplicationContext(),
                        "Your information has been updated",
                        Toast.LENGTH_LONG).show();
                finishActivity();
            } else{
                Toast.makeText(getApplicationContext(),
                        "Please try again or contact us for further requirement",
                        Toast.LENGTH_LONG).show();}
        }
    }


    public void deleteButtonClicked(View view) {
        new DeleteUser().execute(user);
    }

    private class DeleteUser extends AsyncTask<User,Void,Void> {
        @Override
        protected Void doInBackground(User... users) {
            deleteResult = MainActivity.userDao.deleteUser(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(deleteResult == 1){
                Toast.makeText(getApplicationContext(),
                        "Student Record has been deleted",
                        Toast.LENGTH_LONG).show();
                backToMain();
            } else{
                Toast.makeText(getApplicationContext(),
                        "Student Record has not been deleted",
                        Toast.LENGTH_LONG).show();}
        }
    }


    private void finishActivity() {
        Intent i = new Intent(this,AppServices.class);
        startActivity(i);
    }
    private void backToMain(){
        finish();
//        Intent i = new Intent(this,MainActivity.class);
//        startActivity(i);
    }
}
