package com.zumepizza.interview;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zumepizza.interview.data.Topping;

import java.util.List;

/**
 * Created by miguelsaavedralozano on 4/13/18.
 */

public class Utils {
    public static <T> T decodeResp(Class<T> classOfT, String encoded) {
        T retVal = null;
        try {
            Gson gson = new Gson();
            retVal = gson.fromJson(encoded, classOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return retVal;
    }

    public static String getToppings (List<Topping> toppings) {
        String toppingString = "";

        for (Topping topping: toppings) {
            toppingString = toppingString.concat(topping.getName()).concat(", ");
        }

        return  toppingString.substring(0, toppingString.length()-2);
    }

    public static String getFormsttedPrice(String price) {
        return  "$ ".concat(price);
    }
}
