package com.example.a401test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by comp on 3/9/2017.
 */

public class ActionbarToolbar extends AppCompatActivity {

    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Action Bar Toolbar !");
        toolbar.setSubtitle("by MasterBlaster !");
        //toolbar.setNavigationIcon(R.drawable.navigation_back_icon);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String title = (String) item.getTitle();
        Toast.makeText(ActionbarToolbar.this, title + "selected !", Toast.LENGTH_LONG).show();

        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
            case R.id.action_login:
                break;
            case R.id.action_logout:
                break;
            case R.id.action_refresh:
                break;
            case R.id.action_mail:
                break;
            case R.id.action_camera:
                break;
            case R.id.action_alarm:
                break;
            case R.id.action_facebook:
                break;
            case R.id.action_delete:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
