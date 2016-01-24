package com.kevint.waterloohacks_android.DialogFragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevint.waterloohacks_android.R;

/**
 * Created by kevinT on 2016-01-23.
 */
public class OfferDialog extends DialogFragment{

    private String name;
    private String description;
    private int validHours;
    private int imageId;

    private TextView offerNameTextView;
    private TextView offerOldPriceTextView;
    private TextView offerNewPriceTextView;
    private TextView offerValidHoursTextView;
    private TextView offerLongDescriptionTextView;

    public OfferDialog() {}

    public static OfferDialog newInstance(Bundle bundle) {
        OfferDialog offerDialog = new OfferDialog();
        offerDialog.setOfferInfo(bundle);
        return offerDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer_dialog, container);
        offerNameTextView = (TextView) view.findViewById(R.id.offer_name);
        offerOldPriceTextView = (TextView) view.findViewById(R.id.old_price_value);
        offerNewPriceTextView = (TextView) view.findViewById(R.id.new_price_value);
        offerValidHoursTextView = (TextView) view.findViewById(R.id.valid_hours);
        offerLongDescriptionTextView = (TextView) view.findViewById(R.id.offer_description);

        return view;
    }

    public void setOfferInfo(Bundle bundle) {
        name = bundle.getString("name");
        description = bundle.getString("description");
        validHours = bundle.getInt("hours");
//        imageId = bundle.getInt("imageId");
    }
}
