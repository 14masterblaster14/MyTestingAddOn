package com.example.a33telephoneapi;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_SENT = 1234;
    private static final int REQ_DELIVERED = 4561;
    private static final int CALL_PHONE_PERMISSION_CONSTANT = 5678;
    private static final int SEND_SMS_PERMISSION_CONSTANT = 6789;
    private static final int READ_PHONE_STATE_PERMISSION_CONSTANT = 7890;
    StringBuilder stringBuilder;
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Need READ_PHONE_STATE permission")
                    .setMessage("This app need READ_PHONE_STATE permission")
                    .setPositiveButton("Grant", (dialog, which) -> {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_PHONE_STATE},
                                READ_PHONE_STATE_PERMISSION_CONSTANT);
                    }).setNegativeButton("Deny", (((dialog, which) -> {
                        dialog.dismiss();
                    })));

        } else {

            telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        }
        Log.i("@MasterBlaster", "Imei - " + telephonyManager.getDeviceId());
        Log.i("@MasterBlaster", "Mobile No - " + telephonyManager.getLine1Number());
        Log.i("@MasterBlaster", "Iso - " + telephonyManager.getSimCountryIso());
        Log.i("@MasterBlaster", "Ntwk Operator - " + telephonyManager.getNetworkOperator());
        Log.i("@MasterBlaster", "Sim Operator - " + telephonyManager.getSimOperator());

        stringBuilder = new StringBuilder();
        stringBuilder.append("IMEI -> ").append(telephonyManager.getDeviceId()).append("\n").
                append("Mobile No. --> ").append(telephonyManager.getLine1Number()).append("\n").
                append("ISO -> ").append(telephonyManager.getSimCountryIso()).append("\n").
                append("Ntwk Operator ->").append(telephonyManager.getNetworkOperator()).append("\n").
                append("Sim Operator -> ").append(telephonyManager.getSimOperator()).append("\n");

        ((TextView) findViewById(R.id.Txtview)).setText(stringBuilder.toString());

        findViewById(R.id.BtnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        findViewById(R.id.BtnCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveCall();
            }
        });

    }


    private void sendMessage() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Need SEND_SMS permission")
                    .setMessage("This app need SEND_SMS permission")
                    .setPositiveButton("Grant", (dialog, which) -> {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.SEND_SMS},
                                SEND_SMS_PERMISSION_CONSTANT);
                    }).setNegativeButton("Deny", (((dialog, which) -> {
                        dialog.dismiss();
                    })));

        } else {

        SmsManager smsManager = SmsManager.getDefault();

        Intent intentSent = new Intent("com.codekul.SENT");
        PendingIntent pendingIntentSent = PendingIntent.getBroadcast
                (this, REQ_SENT, intentSent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentDelivered = new Intent("com.codekul.DELIVERED");
        PendingIntent pendingTntentDelivered = PendingIntent.getBroadcast
                (this, REQ_DELIVERED, intentDelivered, PendingIntent.FLAG_UPDATE_CURRENT);

            smsManager.sendTextMessage("+919820857225", null, "Hi From Android..1", pendingIntentSent, pendingTntentDelivered);
        }

    }

    private void giveCall() {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:+919820857225"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;

            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Need CALL_PHONE permission")
                    .setMessage("This app need CALL_PHONE permission")
                    .setPositiveButton("Grant", (dialog, which) -> {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                CALL_PHONE_PERMISSION_CONSTANT);
                    }).setNegativeButton("Deny", (((dialog, which) -> {
                        dialog.dismiss();
                    })));
        } else {
            startActivity(intent);
        }
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
