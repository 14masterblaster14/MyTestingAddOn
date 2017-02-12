package com.example.a35sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            Log.i("@MasterBlaster", " Values - " + event.values[0]);
            allSensors();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

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

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        allSensors();
        accelerometer();
        proximity();
        light();
    }

    private void allSensors() {

        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : sensorList) {
            Log.i("@MAsterBlaster", "Name - " + sensor.getName());
            Log.i("@MasterBlaster", "Vendor - " + sensor.getVendor());
            Log.i("@MasterBlaster", " Type - " + sensor.getType());
        }
    }

    private void accelerometer() {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                String data = "X - " + x + "y - " + y + "z - " + z;
                ((TextView) findViewById(R.id.TxtView_Accelerometer)).setText(data);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void proximity() {

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                ((TextView) findViewById(R.id.TxtView_Proximity)).setText(String.valueOf(event.values[0]));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void light() {

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                ((TextView) findViewById(R.id.TxtView_Light)).setText(String.valueOf(event.values[0]));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, sensor, SensorManager.SENSOR_DELAY_NORMAL);
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
