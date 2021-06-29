package com.azzarina.uniformshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        String message = intent.getStringExtra(SellingPage.EXTRA_MESSAGE);
        TextView textMsg = (TextView)findViewById(R.id.message);
        textMsg.setText(message);
    }
}