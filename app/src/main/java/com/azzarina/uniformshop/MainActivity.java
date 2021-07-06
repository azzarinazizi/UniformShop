package com.azzarina.uniformshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bg_slideshow = findViewById(R.id.bg_slideshow);
        AnimationDrawable animationDrawable = (AnimationDrawable) bg_slideshow.getDrawable();
        animationDrawable.start();

        // To change colour of search bar on home page
        SearchView searchView= (SearchView) findViewById(R.id.searchbar);
        int id = searchView.getContext()
                .getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setHintTextColor(Color.WHITE);
        textView.setTextColor(Color.WHITE);


    }


    public void sendMessage(View aView) {
        Intent intent = new Intent(this, SellingPage.class);
        startActivity(intent);
    }

    public void product_list(View aView) {
        Intent intent = new Intent(this, ProductsList.class);
        startActivity(intent);
    }

    public void sendMessage2(View aView) {
        Intent intent = new Intent(this, uniformGuide.class);
        startActivity(intent);
    }

}


