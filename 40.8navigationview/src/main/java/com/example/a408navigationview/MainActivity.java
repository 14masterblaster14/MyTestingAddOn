package com.example.a408navigationview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbarMenu();
        setupNavigationDrawerMenu();
    }


    private void setupToolbarMenu() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Navigation Menu");
    }

    private void setupNavigationDrawerMenu() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationview);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // item.setCheckable(true);
        // item.setChecked(true);  //It will help to know which Item is clicked

        String itemName = (String) item.getTitle();

        Toast.makeText(MainActivity.this, itemName + "Clicked", Toast.LENGTH_SHORT).show();

        closeDrawer();

        switch (item.getItemId()) {
            case R.id.facebook:
                break;
            case R.id.googlePlus:
                break;
            case R.id.instagram:
                break;
            case R.id.linkedin:
                break;
            case R.id.pinterest:
                break;
            case R.id.twitter:
                break;
        }
        return false;

    }

    private void closeDrawer() {

        drawerLayout.closeDrawer(GravityCompat.START);
    }


    private void openDrawer() {

        drawerLayout.openDrawer(GravityCompat.START);
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            closeDrawer();
        else
            super.onBackPressed();
    }
}
