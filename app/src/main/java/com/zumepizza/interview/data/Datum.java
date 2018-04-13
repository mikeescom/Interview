package com.zumepizza.interview.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("menu_asset_id")
    @Expose
    private Integer menuAssetId;
    @SerializedName("menu_description")
    @Expose
    private String menuDescription;
    @SerializedName("vegetarian")
    @Expose
    private Integer vegetarian;
    @SerializedName("spicy")
    @Expose
    private Integer spicy;
    @SerializedName("classifications")
    @Expose
    private Classifications classifications;
    @SerializedName("menu_badge_id")
    @Expose
    private Object menuBadgeId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("discountable")
    @Expose
    private Boolean discountable;
    @SerializedName("addon")
    @Expose
    private Boolean addon;
    @SerializedName("menuAsset")
    @Expose
    private MenuAsset menuAsset;
    @SerializedName("menuBadge")
    @Expose
    private MenuBadge menuBadge;
    @SerializedName("toppings")
    @Expose
    private List<Topping> toppings = null;
    @SerializedName("availability")
    @Expose
    private Availability availability;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getMenuAssetId() {
        return menuAssetId;
    }

    public void setMenuAssetId(Integer menuAssetId) {
        this.menuAssetId = menuAssetId;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public Integer getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Integer vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Integer getSpicy() {
        return spicy;
    }

    public void setSpicy(Integer spicy) {
        this.spicy = spicy;
    }

    public Classifications getClassifications() {
        return classifications;
    }

    public void setClassifications(Classifications classifications) {
        this.classifications = classifications;
    }

    public Object getMenuBadgeId() {
        return menuBadgeId;
    }

    public void setMenuBadgeId(Object menuBadgeId) {
        this.menuBadgeId = menuBadgeId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getDiscountable() {
        return discountable;
    }

    public void setDiscountable(Boolean discountable) {
        this.discountable = discountable;
    }

    public Boolean getAddon() {
        return addon;
    }

    public void setAddon(Boolean addon) {
        this.addon = addon;
    }

    public MenuAsset getMenuAsset() {
        return menuAsset;
    }

    public void setMenuAsset(MenuAsset menuAsset) {
        this.menuAsset = menuAsset;
    }

    public MenuBadge getMenuBadge() {
        return menuBadge;
    }

    public void setMenuBadge(MenuBadge menuBadge) {
        this.menuBadge = menuBadge;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

}