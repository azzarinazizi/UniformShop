package com.azzarina.uniformshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

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

public class ProductsList extends AppCompatActivity implements Adapter.OnItemClickListener {

    public static final String EXTRA_IMAGE = "imageURL";
    public static final String EXTRA_TITLE = "name";
    public static final String EXTRA_PRICE = "price";
    public static final String EXTRA_SIZE = "size";
    public static final String EXTRA_DESC = "description";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_PHONE = "phone";

    RecyclerView recyclerView;
    List<Product> products;

    private static String JSON_URL = "https://gist.githubusercontent.com/azzarinazizi/3fff562d066a5936853334a401d0c437/raw/7e8eae5a7a4dedd3f18c53e45246558e7b3e0bbf/products";
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
                        product.setDescription(productObject.getString("description").toString());
                        product.setEmail(productObject.getString("email").toString());
                        product.setPhone(productObject.getString("phone").toString());
                        product.setCoverImage(productObject.getString("imageURL").toString());

                        products.add(product);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), products);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(ProductsList.this);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, OpenProduct.class);
        Product clickedItem = products.get(position);

        detailIntent.putExtra(EXTRA_IMAGE,clickedItem.getCoverImage());
        detailIntent.putExtra(EXTRA_TITLE,clickedItem.getTitle());
        detailIntent.putExtra(EXTRA_PRICE,clickedItem.getPrice());
        detailIntent.putExtra(EXTRA_SIZE,clickedItem.getSize());
        detailIntent.putExtra(EXTRA_DESC,clickedItem.getDescription());
        detailIntent.putExtra(EXTRA_EMAIL,clickedItem.getEmail());
        detailIntent.putExtra(EXTRA_PHONE,clickedItem.getPhone());

        startActivity(detailIntent);
    }
}