package com.comapany.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CartActivity extends AppCompatActivity {
    TextView tv,tvprice,tvdesc;


    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tv = (TextView)findViewById(R.id.textView);
        tvprice=(TextView)findViewById(R.id.cartcost);
        tvdesc=(TextView)findViewById(R.id.cartdesc);
        imageView = findViewById(R.id.cartimage);
        tv.setText("Name: "+getIntent().getStringExtra("name"));
        tvprice.setText("Price: "+getIntent().getStringExtra("price"));
        tvdesc.setText("Descrpition: "+getIntent().getStringExtra("desc"));

        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("image")).into(imageView);
        String imagePath = getIntent().getStringExtra("image");
        //imageView.setImageURI(Uri.parse(imagePath ));


    }
}