package com.example.a401materialanddesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by comp on 2/26/2017.
 */

public class StandaloneToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Standalone Toolbar !");
        toolbar.setSubtitle("by MasterBlaster");
        toolbar.setNavigationIcon(R.drawable.navigation_back_icon);


        //Compatibility by JAVA
    /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(10f);
        } */

        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                String title = (String) item.getTitle();
                Toast.makeText(StandaloneToolbar.this, title + "selected !", Toast.LENGTH_LONG).show();

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
                return true;
            }
        });
    }
}
