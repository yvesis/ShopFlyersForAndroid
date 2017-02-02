package com.yvesis.flyers.core.models;

/**
 * Created by louly on 2017-01-30.
 */
@ItemCategory
public class FlyerItem {

    private String name;
    private String price;
    private String imageUrl;

    public FlyerItem(String name, String price) {
        this.name = name;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;// Double.parseDouble(price);
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public String toString() {
        return name + " @" + price;
    }
}
