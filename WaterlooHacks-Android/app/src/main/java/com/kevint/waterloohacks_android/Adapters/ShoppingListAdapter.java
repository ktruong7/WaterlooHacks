package com.kevint.waterloohacks_android.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevint.waterloohacks_android.Objects.Offer;
import com.kevint.waterloohacks_android.Objects.ShoppingItem;
import com.kevint.waterloohacks_android.R;

import java.util.ArrayList;

/**
 * Created by kevinT on 2016-01-23.
 */
public class ShoppingListAdapter extends ArrayAdapter<ShoppingItem> {

    private final Context context;
    private ArrayList<ShoppingItem> items;

    public ShoppingListAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        items = new ArrayList<ShoppingItem>();
    }

    public ShoppingListAdapter(Context context, int resource, ArrayList<ShoppingItem> items) {
        super(context, resource);
        this.context = context;
        this.items = items;
        fillShoppingList();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.offer_layout, null);
        }

        ShoppingItem item = getItem(position);

        if (item != null) {
            TextView offerName = (TextView) v.findViewById(R.id.offer_name);
            TextView offerDescription = (TextView) v.findViewById(R.id.offer_description);
            TextView offerHours = (TextView) v.findViewById(R.id.valid_hours);

            if (offerName != null) {
                offerName.setText(item.getName());
            }

            if (offerDescription != null) {
                offerDescription.setText(item.getAisle());
            }

            if (offerHours != null) {
                offerHours.setText(item.getPrice()+" hours");
            }
        }

        return v;
    }

    private void fillShoppingList() {
        for(int i = 0; i < items.size(); i++) {
            add(items.get(i));
        }
    }

    public void addShoppingItem(ShoppingItem item) {
        add(item);
    }

}
