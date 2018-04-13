package com.zumepizza.interview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 Exercise instructions

 1) Fetch menu data using the URL given in API.java.
 2) Use fetched data to instantiate product model objects.
 2) Using product models, populate a list view using designs found in mocks/menu-mock-up.png
 3) Implement cart functionality, to allow users to add/remove items to their cart.
 4) Add cart activity to display items added to cart. Use designs found in mocks/cart-mock-up.png

 */


public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        tableLayout = (TableLayout) findViewById(R.id.main_table);
        View view;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_layout, null);
        ImageView mainImage = view.findViewById(R.id.main_image);
        tableLayout.addView(view);
    }
}
