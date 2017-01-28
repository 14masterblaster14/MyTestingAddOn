package com.example.a28jsonparsingusinggson;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.a28jsonparsingusinggson.dto.Devices;
import com.example.a28jsonparsingusinggson.dto.My;
import com.example.a28jsonparsingusinggson.dto.Versions;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

        findViewById(R.id.Button).setOnClickListener(this::click);
    }

    private void click(View view) {
        parseUsingGson(readJsonFromAssets());
        convertObjectToJson();
    }


    private String readJsonFromAssets() {

        StringBuilder builder = new StringBuilder();

        try {
            InputStream inputStream = getAssets().open("my.json");
            while (true) {
                int ch = inputStream.read();
                if (ch == -1) break;
                else builder.append((char) ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    private void parseUsingGson(String json) {

        Gson gson = new Gson();
        My my = gson.fromJson(json, My.class);
        Log.i("@MasterBlaster", "Gson - " + my);
    }

    private void convertObjectToJson() {
        Gson gson = new Gson();

        My myConvert = new My();
        myConvert.setName("MasterBlaster");
        myConvert.setOS("Android");
        myConvert.setVer(7.0);
        myConvert.setUpdateAva(true);

        Versions versonsConvert = new Versions();
        versonsConvert.setBase("Master");
        versonsConvert.setCupCake("Blaster");
        myConvert.setAllVersions(versonsConvert);

        ArrayList<Devices> devicesConvert = new ArrayList<>();
        Devices d1 = new Devices();
        d1.setMobile("Motorola");
        d1.setCost(7000);
        myConvert.setDevices(devicesConvert);

        String newJson = gson.toJson(myConvert);
        Log.i("@MasterBlaster", "Converted Json - " + newJson);
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
