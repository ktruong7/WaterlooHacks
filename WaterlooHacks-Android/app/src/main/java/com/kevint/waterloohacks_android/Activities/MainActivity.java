package com.kevint.waterloohacks_android.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.kevint.waterloohacks_android.Adapters.OffersListAdapter;
import com.kevint.waterloohacks_android.Objects.Offer;
import com.kevint.waterloohacks_android.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Context context;

    private ListView offersListView;
    private OffersListAdapter offersListAdapter;
    private ArrayList<Offer> offers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        offersListView = (ListView) findViewById(R.id.offers_list);
        populateOfferList();
        offersListAdapter = new OffersListAdapter(this, android.R.layout.simple_list_item_1, offers);
        offersListView.setAdapter(offersListAdapter);
        Button shoppingListButton = (Button) findViewById(R.id.shopping_list_button);
        shoppingListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShoppingListActivity.class);
                startActivity(intent);
            }
        });
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

    public void openProfile(){

    }

    public void openSettings(){

    }

    public void populateOfferList() {
        Bitmap offerImage1 = BitmapFactory.decodeResource(getResources(), R.drawable.apples);
        Bitmap offerImage2 = BitmapFactory.decodeResource(getResources(), R.drawable.cereal);
        Bitmap offerImage3 = BitmapFactory.decodeResource(getResources(), R.drawable.tilapia);
        Offer offer1 = new Offer("Apple sale", "Pick up apples for $1 each.", 1, offerImage1);
        Offer offer2 = new Offer("Cereal sale", "Grab two 900g cereal boxes for $5", 2, offerImage2);
        Offer offer3 = new Offer("Fish sale", "Buy 2 Tilapia fillets for $4", 2, offerImage3);
        offers = new ArrayList<>();
        offers.add(offer1);
        offers.add(offer2);
        offers.add(offer3);
    }
}
