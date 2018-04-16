package com.zumepizza.interview.db;

public class Item {
    private int _id;
    private String _name;
    private String _url;
    private String _toppings;
    private String _price;
    private String _counter;

    public Item(){   }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_url() {
        return _url;
    }

    public void set_url(String _url) {
        this._url = _url;
    }

    public String get_toppings() {
        return _toppings;
    }

    public void set_toppings(String _toppings) {
        this._toppings = _toppings;
    }

    public String get_price() {
        return _price;
    }

    public void set_price(String _price) {
        this._price = _price;
    }

    public String get_counter() {
        return _counter;
    }

    public void set_counter(String _counter) {
        this._counter = _counter;
    }
}
