package com.comapany.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

public class register2 extends AppCompatActivity {

    EditText phonenumber;
    TextView tv;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        countryCodePicker = findViewById(R.id.countrycode);
        phonenumber = findViewById(R.id.phonenumber_getotp);
        String _name = getIntent().getStringExtra("name");
        tv = findViewById(R.id.textviewdisplay);
        tv.setText(_name);
        countryCodePicker.registerCarrierNumberEditText(phonenumber);
    }

    public void onclickgetotpbutton(View view){

        String _name = getIntent().getStringExtra("name");
        String _email = getIntent().getStringExtra("email");
        String _password = getIntent().getStringExtra("password");

        //String _getUserEnteredNumber = phonenumber.getText().toString().trim();
        /*String _phoneNo = "+"+countryCodePicker.getFullNumber()+_getUserEnteredNumber;*/


        Intent intent = new Intent(getApplicationContext(),otpscreen.class);
        intent.putExtra("phoneNo",countryCodePicker.getFullNumberWithPlus().replace(" ",""));
        intent.putExtra("name",_name);
        intent.putExtra("email",_email);
        intent.putExtra("password",_password);
        //intent.putExtra("phoneNo",_phoneNo);
        startActivity(intent);
    }


}