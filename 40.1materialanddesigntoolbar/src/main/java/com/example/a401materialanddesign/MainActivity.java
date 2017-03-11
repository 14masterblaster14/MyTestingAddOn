package com.example.a401materialanddesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*
        //Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //call some material design APIs here
        } else {  // For Below API 21
            // Implement this feature without material design
        } */

        findViewById(R.id.standaloneToolbar).setOnClickListener(this::click);
        findViewById(R.id.actionBarToolbar).setOnClickListener(this::click);
        findViewById(R.id.contextualMenu).setOnClickListener(this::click);
    }

    private void click(View view) {

        switch (view.getId()) {

            case R.id.standaloneToolbar:
                showStandaloneToolbar();
                break;
            case R.id.actionBarToolbar:
                showToolbarAsActionbar();
                break;
            case R.id.contextualMenu:
                showContextualMenu();
                break;
        }
    }

    private void showStandaloneToolbar() {
        Intent intent = new Intent(this, StandaloneToolbar.class);
        startActivity(intent);
    }

    private void showToolbarAsActionbar() {
        Intent intent = new Intent(this, ActionbarToolbar.class);
        startActivity(intent);
    }

    private void showContextualMenu() {
        Intent intent = new Intent(this, ContextualMenu.class);
        startActivity(intent);
    }
}
