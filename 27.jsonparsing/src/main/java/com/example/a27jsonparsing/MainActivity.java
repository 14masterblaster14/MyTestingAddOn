package com.example.a27jsonparsing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

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

        parseUsingJsonObjectApi(readJsonFromAssets());
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

    private void parseUsingJsonObjectApi(String json) {
        StringBuilder builder = new StringBuilder();

        try {
            JSONObject rootObject = new JSONObject(json);
            String name = rootObject.getString("Name");
            String os = rootObject.getString("OS");
            double version = rootObject.getDouble("Ver");
            boolean isUpdateAva = rootObject.getBoolean("IsUpdateAva");
            builder.append("\n Name ").append(name);
            builder.append("\n OS ").append(os);
            builder.append("\n Version ").append(version);
            builder.append("\n IsUpdateAva ").append(isUpdateAva);

            JSONObject innerObject = rootObject.getJSONObject("AllVersions");
            double base = innerObject.getDouble("Base");
            double cupCake = innerObject.getDouble("cupCake");
            builder.append("\n Base ").append(base);
            builder.append("\n CupCake ").append(cupCake);

            JSONArray devices = rootObject.getJSONArray("devices");

            for (int i = 0; i < devices.length(); i++) {

                JSONObject arrayObject = devices.getJSONObject(i);
                String mobile = arrayObject.getString("mobile");
                int cost = arrayObject.getInt("cost");
                builder.append("\n Mobile ").append(mobile);
                builder.append("\n Cost ").append(cost);
            }

            Log.i("@JSONPARSING", builder.toString());

        } catch (JSONException e) {
            e.printStackTrace();
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
