package com.azzarina.uniformshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class listing_overview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_overview);



        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity2.EXTRA_MESSAGE);
        TextView textMsg = (TextView) findViewById(R.id.price);
        textMsg.setText(message);


    }
}