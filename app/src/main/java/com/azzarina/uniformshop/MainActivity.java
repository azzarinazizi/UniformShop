package com.azzarina.uniformshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bg_slidehshow = findViewById(R.id.bg_slideshow);
        AnimationDrawable animationDrawable = (AnimationDrawable) bg_slidehshow.getDrawable();
        animationDrawable.start();


        SearchView searchView= (SearchView) findViewById(R.id.searchbar);
        int id = searchView.getContext()
                .getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setHintTextColor(Color.WHITE);
        textView.setTextColor(Color.WHITE);
    }

    public void sendMessage(View aView) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void sendMessage1(View aView) {
        Intent intent = new Intent(this, buy001.class);
        startActivity(intent);
    }

    public void sendMessage2(View aView) {
        Intent intent = new Intent(this, uniformGuide.class);
        startActivity(intent);
    }
}


