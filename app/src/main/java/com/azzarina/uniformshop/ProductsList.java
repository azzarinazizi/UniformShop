package com.azzarina.uniformshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

public class ProductsList extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Product> products;

    private static String JSON_URL = "https://gist.githubusercontent.com/azzarinazizi/de58252ac41bf1521ace5095730941f2/raw/7b62c6acf40a1038975ed140cd9c0f566ac7400f/gistfile1.txt";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        recyclerView = findViewById(R.id.productsList);
        products = new ArrayList<>();

        extractProducts();
    }

    private void extractProducts() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject productObject = response.getJSONObject(i);

                        Product product = new Product();
                        product.setTitle(productObject.getString("name").toString());
                        product.setPrice(productObject.getString("price").toString());
                        product.setSize(productObject.getString("size").toString());
                        product.setCoverImage(productObject.getString("imageURL").toString());

                        products.add(product);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), products);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);

    }
}