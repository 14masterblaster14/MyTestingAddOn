package com.example.a406appbarandcollapsingtoolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("AppBarLayout");
    }
}


/* Scroll : This flag should be set for all views that want to scroll off the screen
   - for views thet do not use this flag , they'll remain pinned to the top of the screen

   enterAlways = toolbar appears back ASA we move down
   enterAlwaysCollapsed = toolbar appears after reaching its top
   exitUntilCollapsed = */