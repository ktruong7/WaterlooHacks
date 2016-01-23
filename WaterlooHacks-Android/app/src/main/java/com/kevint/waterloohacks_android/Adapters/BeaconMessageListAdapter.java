package com.kevint.waterloohacks_android.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kevint.waterloohacks_android.R;

import java.util.ArrayList;

/**
 * Created by kevinT on 2016-01-23.
 */
public class BeaconMessageListAdapter extends ArrayAdapter<String> {

    private final Context context;
    private ArrayList<String> messages;

    public BeaconMessageListAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        messages = new ArrayList<String>();
        messages.add("test message 1");
        fillMessageList();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.beacon_message_layout, null);
        }

        String currentMessage = getItem(position);

        if (currentMessage != null) {
            TextView beaconMessageTextView= (TextView) v.findViewById(R.id.beacon_message);
            beaconMessageTextView.setText(currentMessage);
        }

        return v;
    }

    private void fillMessageList() {
        if(messages != null && messages.size() > 0) {
            for (int i = 0; i < messages.size(); i++) {
                insert(messages.get(i), 0);
            }
        }
    }

    public void addMessage(String message) {
        messages.add(message);
        insert(message, 0);
    }
}
