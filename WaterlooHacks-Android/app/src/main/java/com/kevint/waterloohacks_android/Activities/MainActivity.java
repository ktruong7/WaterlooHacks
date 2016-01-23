package com.kevint.waterloohacks_android.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kevint.waterloohacks_android.Adapters.OffersListAdapter;
import com.kevint.waterloohacks_android.Objects.Offer;
import com.kevint.waterloohacks_android.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView offersListView;
    private OffersListAdapter offersListAdapter;
    private ArrayList<Offer> offers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        offersListView = (ListView) findViewById(R.id.offers_list);
        populateOfferList();
        offersListAdapter = new OffersListAdapter(this, android.R.layout.simple_list_item_1, offers);
        offersListView.setAdapter(offersListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void populateOfferList() {
        Offer offer1 = new Offer("Apple sale", "Pick up apples for $1 each.", 1);
        Offer offer2 = new Offer("Cereal sale", "Grab two 900g cereal boxes for $5", 2);
        Offer offer3 = new Offer("Fish sale", "Buy 2 Tilapia fillets for $4", 2);
        offers = new ArrayList<>();
        offers.add(offer1);
        offers.add(offer2);
        offers.add(offer3);
    }
}
