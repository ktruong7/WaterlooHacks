package com.kevint.waterloohacks_android.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;
import info.androidhive.slidingmenu.model.NavDrawerItem;
import info.androidhive.slidingmenu.HomeFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.RemoteException;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.res.TypedArray;
import android.content.res.Configuration;

import com.kevint.waterloohacks_android.Adapters.OffersListAdapter;
import com.kevint.waterloohacks_android.Objects.Offer;
import com.kevint.waterloohacks_android.Objects.OfferMapper;
import com.kevint.waterloohacks_android.R;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity implements BeaconConsumer {
    protected static final String TAG = "MainActivity";
    private Context context;

    private ListView offersListView;
    public static OffersListAdapter offersListAdapter;
    private ArrayList<Offer> offers;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    // This broadcast receiver is used to update the activity ui when the phone receives data
    // from a bluetooth beacon.
    BroadcastReceiver navDrawerCloserReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        }
    };

    private BeaconManager beaconManager;

    // This broadcast receiver is used to update the activity ui when the phone receives data
    // from a bluetooth beacon.
    BroadcastReceiver beaconReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Offer offer = OfferMapper.getInstance().getOffer(intent.getIntExtra("offerId", 1000));
            Bitmap offerImage = BitmapFactory.decodeResource(getResources(), offer.getImageByID());
            offer.setImageBitmap(offerImage);
            offersListAdapter.add(offer);
            offersListAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        offersListView = (ListView) findViewById(R.id.offers_list);
        populateOfferList();
        offersListAdapter = new OffersListAdapter(this, android.R.layout.simple_list_item_1, offers);
        offersListView.setAdapter(offersListAdapter);
        setUpNavMenu(savedInstanceState);
        setUpBluetoothBeacon();
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

        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(navDrawerCloserReceiver);
    }

    private void setUpNavMenu(Bundle savedInstanceState) {
        mTitle = mDrawerTitle = "Menu";

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1), new Intent(context, ShoppingListActivity.class)));
        // Profile
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1), new Intent(context, ShoppingListActivity.class)));
        // Shopping List
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1), new Intent(context, ShoppingListActivity.class)));
        // Settings
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), new Intent(context, BluetoothBeaconActivity.class)));

        // Can add a counter by adding "true" and an int to the constructor
        //navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"))

        // Recycle the typed array
        navMenuIcons.recycle();

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.intent.CLOSE_DRAWER");
        registerReceiver(navDrawerCloserReceiver, filter);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
//                    Log.i(TAG, "The first beacon I see is about "+beacons.iterator().next().getDistance()+" meters away.");
                    ArrayList<Beacon> beaconsList = (ArrayList<Beacon>) beacons;
                    int index = 0;
                    double shortestDistance = 1000;
                    Beacon nearestBeacon = beaconsList.get(0);
                    while(index < beacons.size()) {
                        Beacon beacon = beaconsList.get(index);
                        Identifier id1 = beacon.getId1();
                        String hexId = id1.toHexString();
                        if (hexId.equals("0xa77a1b6849a74dbf914c760d07fbb8aa")) {
                            double distance = beacon.getDistance();
                            if (distance < shortestDistance) {
                                nearestBeacon = beacon;
                                shortestDistance = distance;
                            }
                        };
                        index++;
                    }
                    if(nearestBeacon != null && nearestBeacon.getId1().toHexString().equals("0xa77a1b6849a74dbf914c760d07fbb8aa")) {
                        String id3 = nearestBeacon.getId3().toHexString();
                    }
                    // TODO: Extract id3 from the nearest beacon and perform a look up from the offerMapper
                    // to add the new offer to the listview.
//                    broadcastIntent("The first beacon I see is about " + beacons.iterator().next().getDistance()+" meters away.");
                }
            }
        });

        beaconManager.setMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.i(TAG, "I just saw an beacon for the first time!");
//                broadcastIntent("Detected a beacon with id: " + region.getUniqueId());
            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "I no longer see an beacon");
//                broadcastIntent("Lost signal to beacon with id: " + region.getUniqueId());
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.i(TAG, "I have just switched from seeing/not seeing beacons: " + state);
//                broadcastIntent("I have just switched from seeing/not seeing beacons: " + state);
            }
        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {    }
    }

    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }
    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new HomeFragment();
                break;
            case 2:
                fragment = new HomeFragment();
                break;
            case 3:
                fragment = new HomeFragment();
                break;
            case 4:
                fragment = new HomeFragment();
                break;
            case 5:
                fragment = new HomeFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    private void populateOfferList() {
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

    private void setUpBluetoothBeacon() {
        // bluetooth beacon setup
        beaconManager = BeaconManager.getInstanceForApplication(this);
        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
        // beaconManager.getBeaconParsers().add(new BeaconParser().
        // setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);

        // register broadcast receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.intent.BEACON_INTENT");
        registerReceiver(beaconReceiver, filter);
    }

    public void broadcastIntent(int offerId)
    {
        Intent intent = new Intent();
        intent.setAction("com.intent.BEACON_INTENT");
        intent.putExtra("offerId", offerId);
        sendBroadcast(intent);
    }

    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
