package com.kevint.waterloohacks_android.Fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevint.waterloohacks_android.Objects.Offer;
import com.kevint.waterloohacks_android.R;

/**
 * Created by kevinT on 2016-01-23.
 */
public class OfferFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.offer_layout, container, false);

        Bundle args = getArguments();

        if (args != null) {
            TextView offerName = (TextView) view.findViewById(R.id.offer_name);
            TextView offerDescription = (TextView) view.findViewById(R.id.offer_description);
            TextView offerHours = (TextView) view.findViewById(R.id.valid_hours);
            ImageView offerImageView = (ImageView) view.findViewById(R.id.offer_image);
            final String name = args.getString("name");
            final String description = args.getString("description");
            final int hours = args.getInt("validHours");
            final int imageId = args.getInt("imageId");
            if (offerName != null) {
                offerName.setText(name);
            }

            if (offerDescription != null) {
                offerDescription.setText(description);
            }

            if (offerHours != null) {
                offerHours.setText(hours+" hours");
            }

            if (offerImageView != null) {
                Bitmap offerImage = BitmapFactory.decodeResource(getResources(), imageId);
                offerImageView.setImageBitmap(offerImage);
            }
        }

        return view;
    }

    public static OfferFragment newInstance(String name, String description, int validHours, int imageId) {
        OfferFragment f = new OfferFragment();
        Bundle b = new Bundle();
        b.putString("name", name);
        b.putString("description", description);
        b.putInt("validHours", validHours);
        b.putInt("ImageId", imageId);
        f.setArguments(b);
        return f;
    }

}
