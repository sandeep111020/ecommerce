package com.comapany.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.comapany.shopping.Models.User;
import com.comapany.shopping.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity {
    private CircleImageView profileImageView;
    private EditText fullnameEditText,userPhoneEditText,addressEditText,pinedittext,cityedittext,stateedittext;
    private TextView profileChangeTextbtn,closeTextbtn,saveTextbtn;
    String user_name,number,address,pincode,city,state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        profileImageView=findViewById(R.id.settings_profile_image);
        fullnameEditText=findViewById(R.id.setting_full_name);
        userPhoneEditText=findViewById(R.id.setting_phone_number);
        addressEditText=findViewById(R.id.setting_address);
        pinedittext= findViewById(R.id.setting_edtpin);
        cityedittext = findViewById(R.id.setting_edtcity);
        stateedittext = findViewById(R.id.setting_edtstate);
        profileChangeTextbtn=findViewById(R.id.profile_image_change_btn);
        closeTextbtn=findViewById(R.id.close_setting_btn);
        saveTextbtn=findViewById(R.id.update_account_setting_btn);
        user_name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("number");

        fullnameEditText.setText(user_name);
        userPhoneEditText.setText(number);


        Query checkuser = FirebaseDatabase.getInstance().getReference("UserDetails").child(number);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String address1 = snapshot.child("addressU").getValue(String.class);
                String pincode1 = snapshot.child("pincodeU").getValue(String.class);
                String city1 = snapshot.child("city").getValue(String.class);
                String state1 = snapshot.child("state").getValue(String.class);
                cityedittext.setText(city1);
                stateedittext.setText(state1);
                addressEditText.setText(address1);
                pinedittext.setText(pincode1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        saveTextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address = addressEditText.getText().toString();
                pincode=pinedittext.getText().toString();
                city = cityedittext.getText().toString();
                state = stateedittext.getText().toString();
                storeNewUserData();
            }
        });

        Intent intent = new Intent(SettingActivity.this,BookingPage.class);
        intent.putExtra("name",user_name);
        intent.putExtra("number",number);
        intent.putExtra("address",address);
        intent.putExtra("city",city);
        intent.putExtra("state",state);
        intent.putExtra("pincode",pincode);




       /* UserInfoDisplay(profileImageView,userPhoneEditText,fullnameEditText,addressEditText);

        saveTextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });
        */

    }

    private void storeNewUserData() {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("UserDetails");


        UserDetails addnewUser = new UserDetails(user_name,number,address,pincode,city,state);
        reference.child(number).setValue(addnewUser);
        fullnameEditText.setText(user_name);
        userPhoneEditText.setText(number);
        addressEditText.setText(address);
        pinedittext.setText(pincode);
        cityedittext.setText(city);
        stateedittext.setText(state);
    }

   /* private void UserInfoDisplay(CircleImageView profileImageView, EditText userPhoneEditText, EditText fullnameEditText, EditText addressEditText)
    {
        //Display The User Information

        DatabaseReference userRef= FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.CurrntOnlineuser.getEmail());
    }*/
}