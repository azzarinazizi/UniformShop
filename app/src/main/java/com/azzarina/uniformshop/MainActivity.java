package com.azzarina.uniformshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // background image slideshow
        ImageView bg_slideshow = findViewById(R.id.bg_slideshow);
        AnimationDrawable animationDrawable = (AnimationDrawable) bg_slideshow.getDrawable();
        animationDrawable.start();

        // To change colour of search bar on home page
        SearchView searchView= (SearchView) findViewById(R.id.searchbar);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setHintTextColor(Color.WHITE);
        textView.setTextColor(Color.WHITE);
    }

    // 'SELL' button onclick
    public void sellingPage (View aView) {
        Intent intent = new Intent(this, SellingPage.class);
        startActivity(intent);
    }

    // 'BUY' button onclick
    public void productList(View aView) {
        Intent intent = new Intent(this, ProductsList.class);
        startActivity(intent);
    }

    // 'Uniform Guide' button onclick
    public void uniformGuide(View aView) {
        Intent intent = new Intent(this, UniformGuide.class);
        startActivity(intent);
    }
}


