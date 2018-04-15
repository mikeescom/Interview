package com.zumepizza.interview;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zumepizza.interview.data.Datum;
import com.zumepizza.interview.db.DatabaseHandler;
import com.zumepizza.interview.db.Item;

import org.json.JSONArray;

/*
 Exercise instructions

 1) Fetch menu data using the URL given in API.java.
 DONE
 2) Use fetched data to instantiate product model objects.
 DONE
 2) Using product models, populate a list view using designs found in mocks/menu-mock-up.png
 DONE but no folder was found
 3) Implement cart functionality, to allow users to add/remove items to their cart.
 DONE
 4) Add cart activity to display items added to cart. Use designs found in mocks/cart-mock-up.png
 DONE but no folder was found

 */


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    final DatabaseHandler db = new DatabaseHandler(this);
    private Context context;
    final int ANIMATION_DURATION = 650;
    private TableLayout tableLayout;

    Datum[] datumArray = null;
    private API api;
    private AlphaAnimation buttonClick;
    private AnimationSet animationSet;
    private Button checkoutButton;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        api = new API(this);
        initViews();
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final TextView itemsCounter = (TextView) findViewById(R.id.items_counter);
        itemsCounter.setText(db.getItemsCount() + " items added to cart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private void initViews() {
        buttonClick = new AlphaAnimation(1F, 0.5F);
        animationSet = new AnimationSet(true);
        animationSet.addAnimation(buttonClick);
        animationSet.setDuration(ANIMATION_DURATION);
        tableLayout = (TableLayout) findViewById(R.id.main_table);
        checkoutButton = (Button) findViewById(R.id.checkout_button);
    }
    
    private void getData (){
        API.ResponseHandler responseHandler = new API.ResponseHandler() {
            @Override
            public void completion(JSONArray response) {
                Log.d(TAG, response.toString());
                try {
                    datumArray = Utils.decodeResp(Datum[].class, response.toString());
                    populateTable();
                }
                catch (Exception e) {
                    e.getStackTrace();
                }
            }
        };
        api.fetchMenu(responseHandler);
    }

    private void populateTable() {
        TableRow row = null;
        for(final Datum datum: datumArray) {
            row = new TableRow(this);
            row.setGravity(Gravity.CENTER);
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View layoutView = inflater.inflate(R.layout.item_layout, null);

            final TextView itemsCounter = (TextView) findViewById(R.id.items_counter);
            itemsCounter.setText(db.getItemsCount() + " items added to cart");

            ImageView mainImage = layoutView.findViewById(R.id.main_image);
            ImageButton addButton = layoutView.findViewById(R.id.add_button);
            final TextView title = layoutView.findViewById(R.id.title);
            TextView toppings = layoutView.findViewById(R.id.toppings);
            final TextView price = layoutView.findViewById(R.id.price);

            Picasso.get().load(datum.getMenuAsset().getUrl()).into(mainImage);
            title.setText(datum.getName());
            toppings.setText(Utils.getToppings(datum.getToppings()));
            price.setText(Utils.getFormsttedPrice(datum.getPrice()));

            addButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    counter++;
                    v.startAnimation(animationSet);
                    checkoutButton.setText("Go to cart");
                    Item item = new Item();
                    item.set_name(title.getText().toString());
                    item.set_url(datum.getMenuAsset().getUrl().toString());
                    item.set_toppings(Utils.getToppings(datum.getToppings()));
                    item.set_price(price.getText().toString());
                    db.addItem(item);
                    itemsCounter.setText(counter + " items added to cart");
                    Toast.makeText(getApplicationContext(), "1 item added! ", Toast.LENGTH_SHORT).show();
                }
            });

            checkoutButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(context, CartActivity.class);
                    startActivity(intent);
                }
            });

            row.addView(layoutView);
            tableLayout.addView(row);
        }
    }
}
