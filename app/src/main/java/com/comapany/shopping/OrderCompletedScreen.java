package com.comapany.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.comapany.shopping.Models.Orders;
import com.comapany.shopping.Models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderCompletedScreen extends AppCompatActivity {
    String name,number,address,city,state,pin;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_completed_screen);
        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("number");
        address = getIntent().getStringExtra("address");
        city = getIntent().getStringExtra("city");
        state = getIntent().getStringExtra("state");
        pin = getIntent().getStringExtra("pin");
        textView = findViewById(R.id.testext);
        textView.setText(name);
        storeDataInDB();
    }

    private void storeDataInDB() {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Orders");


        Orders addnewUser = new Orders(name,number,address,city,state,pin);
        reference.child(number).setValue(addnewUser);
    }
}