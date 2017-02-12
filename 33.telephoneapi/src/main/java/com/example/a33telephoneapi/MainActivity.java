package com.example.a33telephoneapi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_SENT = 1234;
    private static final int REQ_DELIVERED = 4561;
    private TelephonyManager telephonyManager;

    private BroadcastReceiver broadcastReceiverSms = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i("@MasterBlaster", "Action - " + intent.getAction());

            if (intent.getAction().equals("com.codekul.SENT")) {
                Log.i("@MasterBlaster", " Messgae Sent");
            } else {
                Log.i("@MasterBlaster", " Messgae Delivered");
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

        telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        Log.i("@MasterBlaster", "Imei - " + telephonyManager.getDeviceId());
        Log.i("@MasterBlaster", "Mobile No - " + telephonyManager.getLine1Number());
        Log.i("@MasterBlaster", "Iso - " + telephonyManager.getSimCountryIso());
        Log.i("@MasterBlaster", "Nw Operator - " + telephonyManager.getNetworkOperator());
        Log.i("@MasterBlaster", "Sim Operator - " + telephonyManager.getSimOperator());

        findViewById(R.id.BtnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

    }

    private void sendMessage() {

        SmsManager smsManager = SmsManager.getDefault();

        Intent intentSent = new Intent("com.codekul.SENT");
        PendingIntent pendingIntentSent = PendingIntent.getBroadcast
                (this, REQ_SENT, intentSent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentDelivered = new Intent("com.codekul.DELIVERED");
        PendingIntent pendingTntentDelivered = PendingIntent.getBroadcast
                (this, REQ_DELIVERED, intentDelivered, PendingIntent.FLAG_UPDATE_CURRENT);

        smsManager.sendTextMessage("+919820857225", null, "Hi From Android..1", pendingIntentSent, pendingTntentDelivered);

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
