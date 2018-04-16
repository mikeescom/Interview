package com.zumepizza.interview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zumepizza.interview.db.DatabaseHandler;
import com.zumepizza.interview.db.Item;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ListAdapter adapter = null;
    private DatabaseHandler db = null;
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
        adapter = new ListAdapter(this, cartList);
        db = new DatabaseHandler(this);
        cartListView = (ListView) findViewById(R.id.cart_list);
        cartListView.setAdapter(adapter);
    }

    public class ListAdapter extends ArrayAdapter<Item> {
        public ListAdapter(Context context, ArrayList<Item> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Item item = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_list_item_layout, parent, false);
            }

            ImageView mainImage = (ImageView) convertView.findViewById(R.id.main_image);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView price = (TextView) convertView.findViewById(R.id.price);

            ImageButton plus = (ImageButton) convertView.findViewById(R.id.plus);
            final TextView counter = (TextView) convertView.findViewById(R.id.counter);
            counter.setText(item.get_counter());
            ImageButton minus = (ImageButton) convertView.findViewById(R.id.minus);
            ImageButton delete = (ImageButton) convertView.findViewById(R.id.delete);
            plus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int addOne = Integer.valueOf(counter.getText().toString()) + 1;
                    db.updateItemCounter(item, addOne);
                    counter.setText(String.valueOf(addOne));
                    getData();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "One more added!", Toast.LENGTH_SHORT).show();
                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int removeOne = Integer.valueOf(counter.getText().toString()) - 1;
                    if (removeOne <= 0) {
                        db.deleteItem(adapter.getItem(position));
                        adapter.remove(adapter.getItem(position));
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Item deleted!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    db.updateItemCounter(item, removeOne);
                    counter.setText(String.valueOf(removeOne));
                    getData();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "One less!", Toast.LENGTH_SHORT).show();
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    db.deleteItem(adapter.getItem(position));
                    adapter.remove(adapter.getItem(position));
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Item deleted!", Toast.LENGTH_SHORT).show();
                }
            });

            Picasso.get().load(item.get_url()).into(mainImage);
            title.setText(item.get_name());
            price.setText(item.get_price());

            return convertView;
        }
    }
}
