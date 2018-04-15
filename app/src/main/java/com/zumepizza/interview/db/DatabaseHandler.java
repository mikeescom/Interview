package com.zumepizza.interview.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cartManager";
    private static final String TABLE_CART = "cart";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_URL = "url";
    private static final String KEY_TOPPINGS = "toppings";
    private static final String KEY_PRICE = "price";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_URL + " TEXT,"
                + KEY_TOPPINGS + " TEXT,"
                + KEY_PRICE + " TEXT" + ")";
        db.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.get_name());
        values.put(KEY_URL, item.get_url());
        values.put(KEY_TOPPINGS, item.get_toppings());
        values.put(KEY_PRICE, item.get_price());
        db.insert(TABLE_CART, null, values);
        db.close();
    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> itemList = new ArrayList<Item>();
        String selectQuery = "SELECT * FROM " + TABLE_CART;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.set_id(Integer.parseInt(cursor.getString(0)));
                item.set_name(cursor.getString(1));
                item.set_url(cursor.getString(2));
                item.set_toppings(cursor.getString(3));
                item.set_price(cursor.getString(4));
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        return itemList;
    }

    public int getItemsCount() {
        ArrayList<Item> itemsList = getAllItems();
        return itemsList.size();
    }

    public void deleteItem(Item contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
        db.close();
    }
}