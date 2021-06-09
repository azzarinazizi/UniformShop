package com.azzarina.uniformshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity2 extends AppCompatActivity {
 Spinner spinner;
 public static final String EXTRA_MESSAGE = "Description Entry";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        spinner = (Spinner) findViewById(R.id.category_spinner);
        Button enterButton = (Button) findViewById(R.id.enterButton);
        enterButton.setTransformationMethod(null);

    }

    public void sendMessage (View aView) {
        Intent intent = new Intent(this,MainActivity3.class);
        EditText editText = (EditText) findViewById(R.id.extraDescription);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
}}