package com.kevint.waterloohacks_android.Objects;

/**
 * Created by kevinT on 2016-01-23.
 */
public class Offer {
    private String offer_name;
    private String offer_description;
    private int valid_hours;

    public Offer(String name, String description, int validHours) {
        offer_name = name;
        offer_description = description;
        valid_hours = validHours;
    }

    public String getOfferName() {
        return offer_name;
    }

    public String getOfferDescription() {
        return offer_description;
    }

    public int getValidHours() {
        return valid_hours;
    }
}
