package com.kevint.waterloohacks_android.Objects;

/**
 * Created by kevinT on 2016-01-23.
 */
public class Offer {
    private String offer_name;
    private String offer_description;
    private String offer_discount;

    public Offer(String name, String description, String discount) {
        offer_name = name;
        offer_description = description;
        offer_discount = discount;
    }

    public String getOfferName() {
        return offer_name;
    }

    public String getOfferDescription() {
        return offer_description;
    }
}
