package com.kevint.waterloohacks_android.Activities;

import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.kevint.waterloohacks_android.Adapters.BeaconMessageListAdapter;
import com.kevint.waterloohacks_android.R;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

public class BluetoothBeaconActivity extends AppCompatActivity implements BeaconConsumer{
    protected static final String TAG = "BluetoothBeaconActivity";

    private int index = 1;

    private ListView messageList;
    private BeaconMessageListAdapter messageListAdapter;
    private Button testMessageButton;

    private BeaconManager beaconManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_beacon);
        messageList = (ListView) findViewById(R.id.beacon_message_list);
        messageListAdapter = new BeaconMessageListAdapter(this, android.R.layout.simple_list_item_1);
        testMessageButton = (Button) findViewById(R.id.test_message_button);
        testMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index += 1;
                messageListAdapter.addMessage("test message "+index);
            }
        });
        messageList.setAdapter(messageListAdapter);
        // bluetooth beacon setup
        beaconManager = BeaconManager.getInstanceForApplication(this);
        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
        // beaconManager.getBeaconParsers().add(new BeaconParser().
        //        setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bluetooth_beacon, menu);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.i(TAG, "I just saw an beacon for the first time!");
                messageListAdapter.addMessage("Detected a beacon: " + region.getUniqueId());
            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "I no longer see an beacon");
                messageListAdapter.addMessage("Detected a beacon: "+region.getUniqueId());
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.i(TAG, "I have just switched from seeing/not seeing beacons: "+state);
            }
        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
        } catch (RemoteException e) {    }
    }
}
