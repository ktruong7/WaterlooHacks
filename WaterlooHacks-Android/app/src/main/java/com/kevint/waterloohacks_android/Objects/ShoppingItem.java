package com.kevint.waterloohacks_android.Objects;

/**
 * Created by kevinT on 2016-01-23.
 */
public class ShoppingItem {

    private String name;
    private String aisle;
    private double price;

    public ShoppingItem(String name, String aisle, double price) {
        this.name = name;
        this.aisle = aisle;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getAisle() {
        return aisle;
    }

    public double getPrice() {
        return price;
    }

}
