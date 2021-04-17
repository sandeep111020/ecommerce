package com.comapany.shopping;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.comapany.shopping.Models.ProductDisplay;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.io.File;

import javax.xml.namespace.QName;

public class MyAdpter extends FirebaseRecyclerAdapter<ProductDisplay,MyAdpter.myviewholder> {

    Context context;
    public MyAdpter(@NonNull FirebaseRecyclerOptions<ProductDisplay> options, Context context) {
        super(options);
        this.context = context;

    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull ProductDisplay model) {
        holder.name.setText(model.getImageName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.price.setText(model.getImagePrice());
        holder.description.setText(model.getImageDesc());

        Glide.with(context).load(model.getImageURL()).into(holder.img);

        holder.AddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,CartActivity.class);
                intent.putExtra("name",model.getImageName());
                intent.putExtra("desc", model.getImageDesc());
                intent.putExtra("price",model.getImagePrice());
                intent.putExtra("image",model.getImageURL());
                context.startActivity(intent);
            }
        });
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,BookingPage.class);
                i.putExtra("name",model.getImageName());
                i.putExtra("desc",model.getImageDesc());
                i.putExtra("price", model.getImagePrice());
                i.putExtra("image",model.getImageURL());
                context.startActivity(i);
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout,parent,false);

        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name, price, description;
        Button buy, AddtoCart;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.user_product_image);
            name = (TextView)itemView.findViewById(R.id.user_product_name);
            price = (TextView)itemView.findViewById(R.id.user_product_price);
            description = (TextView)itemView.findViewById(R.id.user_product_description);
            buy = (Button)itemView.findViewById(R.id.buy);
            AddtoCart = (Button)itemView.findViewById(R.id.addcart);



        }
    }
}
