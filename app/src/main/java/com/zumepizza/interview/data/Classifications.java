package com.zumepizza.interview.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Classifications {

    @SerializedName("vegetarian")
    @Expose
    private Boolean vegetarian;

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

}