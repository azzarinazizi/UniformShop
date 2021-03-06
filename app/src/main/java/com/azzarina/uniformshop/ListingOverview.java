package com.azzarina.uniformshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static java.lang.String.format;

public class  ListingOverview extends AppCompatActivity {

    // Set attributes as strings before fetching data from database

    String price;
    String description;
    String checked;
    String category;
    String size;
    String condition;
    String email;
    String phone;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_overview);

        // Get data of each specification from database using the object name in the database

        Intent i = getIntent();

        price = i.getStringExtra("price");
        description = i.getStringExtra("description");
        checked = i.getStringExtra("checked");
        category = i.getStringExtra("category");
        size = i.getStringExtra("size");
        condition = i.getStringExtra("condition");
        email = i.getStringExtra("email");
        phone = i.getStringExtra("phone");

        // Match data to its placement in the xml layout

        TextView tv_price = (TextView) findViewById(R.id.price);
        TextView tv_description = (TextView) findViewById(R.id.extradesc);
        TextView tv_listing_hdg = (TextView) findViewById(R.id.listing_hdg);
        TextView tv_size = (TextView) findViewById(R.id.size);
        TextView tv_condition = (TextView) findViewById(R.id.condition);
        TextView tv_email = (TextView) findViewById(R.id.email);
        TextView tv_phone = (TextView) findViewById(R.id.phone);

        // Set text of the data in xml layout

        tv_price.setText(format("$%s", price));
        tv_description.setText(description);
        tv_listing_hdg.setText(String.format("%s %s", checked, category));
        tv_size.setText(size);
        tv_condition.setText(condition);
        tv_email.setText(email);
        tv_phone.setText(phone);
    }

    // 'Return to home' button on click
    public void home (View aView) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}