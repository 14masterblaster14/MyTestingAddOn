package com.example.a401materialanddesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by comp on 2/26/2017.
 */


public class ContextualMenu extends AppCompatActivity {

    Toolbar toolbar;
    Button button;
    android.view.ActionMode actionMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contextual_menu);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Contextual Toolbar !");
        toolbar.setSubtitle("by MasterBlaster !");

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                actionMode = ContextualMenu.this.startActionMode(new ContextualCallback());
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String title = (String) item.getTitle();
        Toast.makeText(ContextualMenu.this, title + "selected !", Toast.LENGTH_LONG).show();

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


    private class ContextualCallback implements android.view.ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(android.view.ActionMode actionMode, Menu menu) {

            actionMode.getMenuInflater().inflate(R.menu.contextual_menu, menu);
            return true;
            //return false;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode actionMode, Menu menu) {

            actionMode.setTitle("My Action Mode");
            actionMode.setSubtitle("By MasterBlaster");
            return true;
            //return false;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode actionMode, MenuItem item) {
            // Add functionality to menu items
            switch (item.getItemId()) {
                case R.id.download:
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode actionMode) {
            //
        }
    }
}

/*
public class ContextualMenu extends AppCompatActivity {

   android.view.ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contextual_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Standalone Toolbar !");
        toolbar.setSubtitle("by MasterBlaster !");

        Button button = (Button) findViewById(R.id.button);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                String title = (String) item.getTitle();
                Toast.makeText(ContextualMenu.this, title + "selected !", Toast.LENGTH_LONG).show();

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionMode = ContextualMenu.this.startActionMode(new ContextualCallback());
            }
        });
    }

    private class ContextualCallback implements android.view.ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(android.view.ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.contextual_menu, menu);
            return true;
            //return false;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode actionMode, Menu menu) {
            actionMode.setTitle("My Action Mode");
            actionMode.setSubtitle("By Little MasterBlaster");
            return false;

        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode actionMode, MenuItem item) {
            // Add functionality to menu items
            switch (item.getItemId()) {
                case R.id.download:
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode actionMode) {
            //Action mode is completed
        }
    }
} */
