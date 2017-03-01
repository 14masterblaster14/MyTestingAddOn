package com.example.a402materialanddesignsnackbarfab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // FrameLayout rootLayout;
    CoordinatorLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(rootLayout, "FAB clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

        // rootLayout = (FrameLayout) findViewById(R.id.activity_main);
        rootLayout = (CoordinatorLayout) findViewById(R.id.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MySnackbar");
        toolbar.inflateMenu(R.menu.menu_main);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.simple_snackbar:
                        showSimpleSnackbar();
                        break;
                    case R.id.snackbar_action_callback:
                        showSnackbarWithActionCallback();
                        break;
                    case R.id.custom_snackbar:
                        showCustomSnackbar();
                        break;
                }

                return false;
            }
        });
    }

    private void showSimpleSnackbar() {

        Snackbar.make(rootLayout, "Simple Snack Bar Example", Snackbar.LENGTH_LONG).show();
    }

    private void showSnackbarWithActionCallback() {

        Snackbar snackbar = Snackbar.make(rootLayout, "File Deleted Successfully", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, "File Recovered Successfully", Snackbar.LENGTH_SHORT).show();
            }
        });
        snackbar.show();

    }

    private void showCustomSnackbar() {

        Snackbar snackbar = Snackbar.make(rootLayout, "Custom Stylish Snack Bar Example", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        snackbar.setActionTextColor(Color.BLUE);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.GREEN);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();

    }
}
