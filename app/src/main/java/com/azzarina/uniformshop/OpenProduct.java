package com.azzarina.uniformshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.azzarina.uniformshop.ProductsList.EXTRA_DESC;
import static com.azzarina.uniformshop.ProductsList.EXTRA_EMAIL;
import static com.azzarina.uniformshop.ProductsList.EXTRA_IMAGE;
import static com.azzarina.uniformshop.ProductsList.EXTRA_PHONE;
import static com.azzarina.uniformshop.ProductsList.EXTRA_PRICE;
import static com.azzarina.uniformshop.ProductsList.EXTRA_SIZE;
import static com.azzarina.uniformshop.ProductsList.EXTRA_TITLE;

public class OpenProduct extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_product);

        Intent intent = getIntent();
        String coverImage = intent.getStringExtra(EXTRA_IMAGE);
        String productTitle = intent.getStringExtra(EXTRA_TITLE);
        String productPrice = intent.getStringExtra(EXTRA_PRICE);
        String productSize = intent.getStringExtra(EXTRA_SIZE);
        String productDesc = intent.getStringExtra(EXTRA_DESC);
        String email = intent.getStringExtra(EXTRA_EMAIL);
        String phone = intent.getStringExtra(EXTRA_PHONE);

        ImageView imageView = findViewById(R.id.coverImage_detail);
        TextView tv_title = findViewById(R.id.productTitle_detail);
        TextView tv_price = findViewById(R.id.productPrice_detail);
        TextView tv_size = findViewById(R.id.productSize_detail);
        TextView tv_desc = findViewById(R.id.productDesc_detail);
        TextView tv_email = findViewById(R.id.email_detail);
        TextView tv_phone = findViewById(R.id.phone_detail);

        Picasso.get().load(coverImage).fit().centerInside().into(imageView);
        tv_title.setText(productTitle);
        tv_price.setText(productPrice);
        tv_size.setText("Size " + productSize);
        tv_desc.setText(productDesc);

        String email_source = "<b>  Sellers email:  </b>" + email;
        tv_email.setText(Html.fromHtml(email_source));

        String phone_source = "<b>  Sellers phone:  </b>" + phone;
        tv_phone.setText(Html.fromHtml(phone_source));
    }
}