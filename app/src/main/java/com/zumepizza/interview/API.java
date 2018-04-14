package com.zumepizza.interview;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zumepizza.interview.data.Datum;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by jimmy on 1/31/18.
 */

public class API {
    private Context context;

    public API(Context context) {
        this.context = context;
    }

    interface ResponseHandler {
        public void completion(JSONArray response);
    }

    public void fetchMenu(final ResponseHandler responseHandler) {
        RequestQueue queue = Volley.newRequestQueue(this.context);

        String url = "https://api.myjson.com/bins/d7wgd";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray json = null;
                        try {
                            json = new JSONArray(response);
                            responseHandler.completion(json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("API", "Menu fetch error: " + error.getLocalizedMessage());
            }
        });
        queue.add(request);
    }
}
