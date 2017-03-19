package com.example.a311wifitest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

/**
 * Created by comp on 3/18/2017.
 */
public class WifiScaner extends BroadcastReceiver {

    //private static final
    MainActivity main;

    public WifiScaner(MainActivity mainActivity) {
        super();
        this.main = mainActivity;

    }


    @Override
    public void onReceive(Context context, Intent intent) {

        List<android.net.wifi.ScanResult> scanresults = main.wifimanager.getScanResults();


        for (android.net.wifi.ScanResult scanresult : scanresults) {
            Log.i("@MasterBlaster", "results " + scanresult);

        }

    }
}
