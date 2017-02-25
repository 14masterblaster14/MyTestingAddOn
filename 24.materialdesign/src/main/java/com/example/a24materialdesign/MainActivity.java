package com.example.a24materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        //Enabling Context Menu
        registerForContextMenu(findViewById(R.id.UserName));
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
            Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show();
        } else if (id == R.id.action_profile) {
            Toast.makeText(this, "Profile", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, ProfileActivity.class));
        } else if (id == R.id.action_help) {
            Toast.makeText(this, "Help", Toast.LENGTH_LONG).show();
        }

        // return super.onOptionsItemSelected(item);

        return true;
    }

    // This method will be used if you want to change the options dynamically
    // i.e. options will be changed runtime if you select any options on the screen
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.UserName) {

        /*    MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.floating_context_menu,menu);  */
            menu.add(100, 1, 0, "Cut");
            menu.add(100, 2, 1, "Copy");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            Toast.makeText(this, "Cut", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Copy", Toast.LENGTH_LONG).show();
        }


        // return super.onContextItemSelected(item);
        return true;
    }
}
