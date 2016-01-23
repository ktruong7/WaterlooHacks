package com.kevint.waterloohacks_android.Objects;

import android.graphics.Bitmap;

/**
 * Created by kevinT on 2016-01-23.
 */
public class Offer {
    private String offer_name;
    private String offer_description;
    private int valid_hours;
    private Bitmap offerImage;

    public Offer(String name, String description, int validHours, Bitmap offerImage) {
        offer_name = name;
        offer_description = description;
        valid_hours = validHours;
        this.offerImage = offerImage;
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

    public Bitmap getOfferImage() {
        return offerImage;
    }
}
