package com.comapany.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class BookingPage extends AppCompatActivity {

    public static final String PAYTM_PACKAGE_NAME = "net.one97.paytm";

    TextView items,itemname, itemdesc, price;
    ImageView imageView;
    EditText nameed,numberedit,addressed,cityed,pined,stateed;
    String name,phone,address,city,pin,state;
    Button minus, plus, pay;
    int counter = 1, amount;
    String recivername,reciverupi,note,ammountpaid,status;
    TextView paymentmsg;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);
        items = (TextView)findViewById(R.id.txt_counter);
        itemname =(TextView)findViewById(R.id.txt_header);
        price = (TextView)findViewById(R.id.txt_amount);
        pay = findViewById(R.id.btn_pay);
        itemdesc= findViewById(R.id.itemdecription);
        imageView = findViewById(R.id.txt_image_view);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        nameed = findViewById(R.id.edt_name);
        numberedit = findViewById(R.id.edt_phone_number);
        addressed = findViewById(R.id.edt_address1);
        cityed = findViewById(R.id.edt_address2);
        stateed = findViewById(R.id.edt_address3);
        pined = findViewById(R.id.edt_pin);

       /* name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("number");
        address = getIntent().getStringExtra("address");
        city = getIntent().getStringExtra("city");
        state = getIntent().getStringExtra("state");
        pin = getIntent().getStringExtra("pincode");*/
        recivername="sandeep";
        reciverupi = "sandeep@mail.com";
        note="hello";
        ammountpaid = "1";



        name = nameed.getText().toString();
        phone = numberedit.getText().toString();
        address = addressed.getText().toString();
        city = cityed.getText().toString();
        state = stateed.getText().toString();
        pin = pined.getText().toString();





        itemname.setText(getIntent().getStringExtra("name"));
        itemdesc.setText(getIntent().getStringExtra("desc"));
        price.setText(getIntent().getStringExtra("price"));
        pay.setText("PAY: "+getIntent().getStringExtra("price")+"" );
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("image")).into(imageView);
        amount = Integer.parseInt(getIntent().getStringExtra("price"));
        nameed.setText(name);
        numberedit.setText(phone);
        addressed.setText(address);
        cityed.setText(city);
        stateed.setText(state);
        pined.setText(pin);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                amount = amount/counter;
                items.setText(counter + "");
                price.setText(amount + "");
                pay.setText("PAY: "+ amount+"");
                amount = Integer.parseInt(getIntent().getStringExtra("price"));
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                amount = amount*counter;
                items.setText(counter + "");
                price.setText(amount + "");
                pay.setText("PAY: "+ amount+"");
                amount = Integer.parseInt(getIntent().getStringExtra("price"));
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uri = getPayTmUri(recivername, reciverupi, note, ammountpaid);
                payWithPayTm(PAYTM_PACKAGE_NAME);



            }

            
        });
       

        



    }

    private Uri getPayTmUri(String recivername, String reciverupi, String note, String ammountpaid) {
        return new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", reciverupi)
                .appendQueryParameter("pn", recivername)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", ammountpaid)
                .appendQueryParameter("cu", "INR")
                .build();
    }
    private void payWithPayTm(String packageName) {

        if (isAppInstalled(this, packageName)) {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uri);
            i.setPackage(packageName);
            startActivityForResult(i, 0);

        } else {
            Toast.makeText(this, "Paytm is not installed Please install and try again.", Toast.LENGTH_SHORT).show();
        }


    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            status = data.getStringExtra("Status").toLowerCase();
        }
        if ((RESULT_OK == resultCode) && status.equals("success")) {
            Toast.makeText(BookingPage.this, "Transaction successful." + data.getStringExtra("ApprovalRefNo"), Toast.LENGTH_SHORT).show();
            paymentmsg.setText("Transaction successful of ₹" + ammountpaid);
            paymentmsg.setTextColor(Color.GREEN);

            Intent intent = new Intent(BookingPage.this,OrderCompletedScreen.class);
            name = nameed.getText().toString();
            phone = numberedit.getText().toString();
            address = addressed.getText().toString();
            city = cityed.getText().toString();
            state = stateed.getText().toString();
            pin = pined.getText().toString();
            intent.putExtra("name", name);
            intent.putExtra("number", phone);
            intent.putExtra("address", address);
            intent.putExtra("city", city);
            intent.putExtra("state", state);
            intent.putExtra("pin", String.valueOf(pin));

            startActivity(intent);

        } else {
            Toast.makeText(BookingPage.this, "Transaction cancelled or failed please try again.", Toast.LENGTH_SHORT).show();
            paymentmsg.setText("Transaction Failed of ₹" + ammountpaid);
            paymentmsg.setTextColor(Color.RED);
        }

    }
}