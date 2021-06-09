package com.azzarina.uniformshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View aView) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void sendMessage1(View aView) {
        Intent intent = new Intent(this, buy001.class);
        startActivity(intent);
    }
}