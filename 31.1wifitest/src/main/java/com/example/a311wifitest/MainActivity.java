package com.example.a311wifitest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WifiManager wifimanager;
    BroadcastReceiver broadcastReceiver;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        textView = (TextView) findViewById(R.id.TxtView);
        button = (Button) findViewById(R.id.BtnScan);
        button.setOnClickListener(this::scan);

        //get wifi status
        wifimanager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiinfo = wifimanager.getConnectionInfo();
        textView.append("\n\n Wifi status :" + wifiinfo.toString());

        if (wifimanager.isWifiEnabled() == false) {

            Toast.makeText(this, "Wifi is disabled, enable it", Toast.LENGTH_LONG).show();
            wifimanager.setWifiEnabled(true);
        }

        //list available networks

        List<WifiConfiguration> wificonfigurations = wifimanager.getConfiguredNetworks();
        for (WifiConfiguration wificonfiguration : wificonfigurations) {

            textView.append("\n\n " + wificonfiguration.toString());
        }

        //register broadcast receiver
        if (broadcastReceiver == null)
            broadcastReceiver = new WifiScaner(this);
        registerReceiver(broadcastReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Log.d("@MasterBlaster", "onCreate()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    private void scan(View view) {

        Toast.makeText(getApplicationContext(), "All networks searched!!", Toast.LENGTH_LONG).show();
        Log.d("@MasterBlaster", "Before start scan");
        wifimanager.startScan();
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

    public class WifiReceiver {

    }
}
