package com.kevint.waterloohacks_android.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevint.waterloohacks_android.Objects.Offer;
import com.kevint.waterloohacks_android.R;

import java.util.ArrayList;

/**
 * Created by kevinT on 2016-01-23.
 */
public class OffersListAdapter extends ArrayAdapter<Offer> {

    private final Context context;
    private ArrayList<Offer> offers;

    public OffersListAdapter(Context context, int resource, ArrayList<Offer> offers) {
        super(context, resource);
        this.context = context;
        this.offers = offers;
        fillOfferList();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.offer_layout, null);
        }

        Offer offer = getItem(position);

        if (offer != null) {
            TextView offerName = (TextView) v.findViewById(R.id.offer_name);
            TextView offerDescription = (TextView) v.findViewById(R.id.offer_description);
            TextView offerHours = (TextView) v.findViewById(R.id.valid_hours);
            ImageView offerImageView = (ImageView) v.findViewById(R.id.offer_image);
            final String name = offer.getOfferName();
            final String description = offer.getOfferDescription();
            final int hours = offer.getValidHours();
//            final Bitmap image = offer.getOfferImage();
            if (offerName != null) {
                offerName.setText(offer.getOfferName());
            }

            if (offerDescription != null) {
                offerDescription.setText(offer.getOfferDescription());
            }

            if (offerHours != null) {
                offerHours.setText(offer.getValidHours()+" hours");
            }

            if (offerImageView != null) {
                Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), offer.getOfferImageID());
                offerImageView.setImageBitmap(bmp);
            }
        }

        return v;
    }

    private void fillOfferList() {
        for(int i = 0; i < offers.size(); i++) {
            insert(offers.get(i), 0);
        }
    }
}
