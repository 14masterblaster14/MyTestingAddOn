package com.example.a32bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_ENABLE_BT = 4689;
    private BluetoothAdapter bluetoothAdapter;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        StringBuilder stringBuilder = new StringBuilder();
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i("@MasterBlaster", " Bluetooth Divices Found :: ");
            BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            Log.i("@MasterBlaster", " Bluetooth Divices Name : " + bluetoothDevice.getName());
            Log.i("@MasterBlaster", " Bluetooth Divices Address : " + bluetoothDevice.getAddress());
            stringBuilder.append("Bluetooth Divices Name --> ").append(bluetoothDevice.getName()).append("\n").
                    append("Bluetooth Divices Address --> ").append(bluetoothDevice.getAddress());
            ((TextView) findViewById(R.id.TxtView)).setText(stringBuilder.toString());
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

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Log.i("@MasterBlaster", " UUID - " + UUID.randomUUID().toString());
        //a427aa15-740e-4319-b4b4-8aa8f580d16e
        Log.i("@MasterBlaster", "Name - " + bluetoothAdapter.getName());
        //YOGA Tablet 2
        Log.i("@MasterBlaster", "Address - " + bluetoothAdapter.getAddress());
        //88:70:8C:41:20:2D

        if (bluetoothAdapter == null) {
            Log.i("@MasterBlaster", "Bluetooth is not available");
            return;
        }

        registerReceiver(broadcastReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));


        findViewById(R.id.BtnBluetoothEnable).setOnClickListener(this::enableBluetooth);
        findViewById(R.id.BtnConnectedDevices).setOnClickListener(this::connectedDevices);
        findViewById(R.id.BtnStartDiscovery).setOnClickListener(this::discoverDevices);
        findViewById(R.id.BtnMakeDiscoverable).setOnClickListener(this::makeDiscoverable);
        findViewById(R.id.BtnBluetoothServer).setOnClickListener(this::server);
        findViewById(R.id.BtnBluetoothClient).setOnClickListener(this::client);
    }

    private void enableBluetooth(View view) {
        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQ_ENABLE_BT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_ENABLE_BT) {

            if (resultCode == RESULT_OK) showToast("Bluetooth Enabled");
            else showToast("Bluetooth Not Enabled");

        }
    }

    private void connectedDevices(View view) {

        StringBuilder stringBuilder = new StringBuilder();

        Set<BluetoothDevice> connectedDevices = bluetoothAdapter.getBondedDevices();

        for (BluetoothDevice connectedDevice : connectedDevices) {
            Log.i("@MasterBlaster", "Bluetooth Connected Devices :: ");
            Log.i("@MasterBlaster", "Name - " + connectedDevice.getName());
            Log.i("@MasterBlaster", "Address - " + connectedDevice.getAddress());

            stringBuilder.append("Name -> ").append(connectedDevice.getName()).append("\n")
                    .append("Address -> ").append(connectedDevice.getAddress()).append("\n");
        }

        ((TextView) findViewById(R.id.TxtView)).setText(stringBuilder.toString());
    }

    private void discoverDevices(View view) {

        bluetoothAdapter.startDiscovery();
    }

    private void makeDiscoverable(View view) {

        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 400);
        startActivity(intent);
    }

    private void server(View view) {
        new Thread(this::serverRunnable).start();
    }

    private void serverRunnable() {

        try {
            BluetoothServerSocket bluetoothServerSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord("myService", UUID.fromString("b59c64b7-33ea-4895-a025-71fc85d44f2b"));
            //bluetoothServerSocket.connect();  //Ask for Pairing
            BluetoothSocket bluetoothSocket = bluetoothServerSocket.accept(); //Accept connection from client
            DataOutputStream dos = new DataOutputStream(bluetoothSocket.getOutputStream());
            dos.writeUTF("This is HELLO from Server side / Connected to Bluetooth Server ");
            //dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void client(View view) {
        new Thread(this::clientRunnable).start();
    }

    private void clientRunnable() {
        BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice("F4:F5:A5:E4:98:C0");
        try {
            BluetoothSocket bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString("b59c64b7-33ea-4895-a025-71fc85d44f2b"));
            bluetoothSocket.connect(); // ask for pairing
            DataInputStream dis = new DataInputStream(bluetoothSocket.getInputStream());
            String dataFromServer = dis.readUTF();
            Log.i("@MasterBlaster", "Server Said : " + dataFromServer);
            //dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showToast(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
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
