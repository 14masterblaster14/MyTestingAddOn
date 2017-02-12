package com.example.a30locationservices;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.location.LocationManager.PASSIVE_PROVIDER;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_LOCATION = 1234;
    private LocationManager locationManager;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Boolean isEntering = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false);

            if (isEntering) {


            } else {

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

        /*<---------- My code starts here... ---------------->*/
        initLocationManager();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.codekul.pune");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void initLocationManager() {

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setMessage("We need access to your location")
                    .setPositiveButton("Okey", (dialog, which) -> {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                PERMISSION_LOCATION);
                    })
                    .setNegativeButton("No", ((dialog, which) -> {
                        dialog.dismiss();
                    }));

            builder.show();
        } else {

            listenForLocations();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_LOCATION) {
            if (permissions.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    listenForLocations();
                }
            }
        }
    }

    private void listenForLocations() {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "Plz provide Access FINE + COARSE Location", Toast.LENGTH_LONG).show();

            return;
        }

        locationManager.requestLocationUpdates(PASSIVE_PROVIDER, 100, 0.5f, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                setInfo("Lat - " + location.getLatitude() + "Lang - " + location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }

    private void setInfo(String info) {

        ((TextView) findViewById(R.id.ShowText)).setText(info);
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
