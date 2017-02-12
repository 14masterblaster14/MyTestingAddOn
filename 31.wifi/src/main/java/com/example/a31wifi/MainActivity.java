package com.example.a31wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WifiManager wifiManager;
    private BroadcastReceiver wifiBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            List<ScanResult> scanResults = wifiManager.getScanResults();
            for (ScanResult scanResult : scanResults) {

                Log.i("@MasterBlaster", "Scanned SSID - " + scanResult.SSID);
                Log.i("@MasterBlaster", "Scanned BSSID - " + scanResult.BSSID);
            }
        }
    };

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

        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

        if (!wifiManager.isWifiEnabled()) wifiManager.setWifiEnabled(true);

        findViewById(R.id.BtnConnected).setOnClickListener(this::connectedWifi);
        findViewById(R.id.BtnScan).setOnClickListener(this::scanForWifi);
        findViewById(R.id.BtnConnect).setOnClickListener(this::connectWifi);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(wifiBroadcastReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    @Override
    protected void onStop() {
        unregisterReceiver(wifiBroadcastReceiver);
        super.onStop();
    }


    private void connectedWifi(View view) {

        List<WifiConfiguration> wifiConfigurations = wifiManager.getConfiguredNetworks();
        for (WifiConfiguration wifiConfiguration : wifiConfigurations) {

            Log.i("@MasterBlaster", "Connected BSSID - " + wifiConfiguration.BSSID);
            Log.i("@MasterBlaster", "Connected SSID - " + wifiConfiguration.SSID);
        }
    }

    private void scanForWifi(View view) {

        wifiManager.startScan();

    }

    private void connectWifi(View view) {

        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = String.format("\"%s\"", "YOUR CODEKUL");
        // wifiConfiguration.SSID = String.format("\"%s\"","iBall-Baton");
        wifiConfiguration.preSharedKey = String.format("\"%s\"", "code.com;");
        wifiConfiguration.preSharedKey = String.format("\"%s\"", "pswd@123");

        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        int netId = wifiManager.addNetwork(wifiConfiguration);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
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
}
