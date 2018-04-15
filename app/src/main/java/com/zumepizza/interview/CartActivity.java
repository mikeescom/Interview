package com.zumepizza.interview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zumepizza.interview.db.DatabaseHandler;
import com.zumepizza.interview.db.Item;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getData();
    }

    private void getData() {
        final DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Item> cartList = db.getAllItems();
        populateList(cartList);
    }

    private void populateList(ArrayList<Item> cartList) {
        final ListAdapter adapter = new ListAdapter(this, cartList);
        final DatabaseHandler db = new DatabaseHandler(this);
        cartListView = (ListView) findViewById(R.id.cart_list);
        cartListView.setAdapter(adapter);
        cartListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int arg2, long arg3) {
                db.deleteItem(adapter.getItem(arg2));
                adapter.remove(adapter.getItem(arg2));
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public class ListAdapter extends ArrayAdapter<Item> {
        public ListAdapter(Context context, ArrayList<Item> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Item item = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_list_item_layout, parent, false);
            }

            ImageView mainImage = (ImageView) convertView.findViewById(R.id.main_image);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView toppings = (TextView) convertView.findViewById(R.id.toppings);
            TextView price = (TextView) convertView.findViewById(R.id.price);

            Picasso.get().load(item.get_url()).into(mainImage);
            title.setText(item.get_name());
            toppings.setText(item.get_toppings());
            price.setText(item.get_price());

            return convertView;
        }
    }
}
