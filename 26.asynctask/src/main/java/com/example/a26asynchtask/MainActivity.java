package com.example.a26asynchtask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

        findViewById(R.id.BtnSubmit).setOnClickListener(this::click);
    }

    private void click(View view) {

        //threadRun();
        new MyTask().execute(0, 100 /* Params*/);
    }

    private void threadRun() {
        new Thread(() -> {

            TextView textview = (TextView) findViewById(R.id.TxtView);
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                textview.setText(String.valueOf(i));
            }
        }).start();
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

    // private class MyTask extends AsyncTask<Params, Progress, Result>
    // AsyncTask is a generics i.e.AsyncTask<Void/Integer, Integer/float, Boolean/Integer>
    private class MyTask extends AsyncTask<Integer, Integer, Boolean> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //This is Main/UI Thread
            ProgressDialog.show(MainActivity.this, "Counter", "I am counting...");
        }

        @Override
        protected Boolean/*Result*/ doInBackground(Integer... params /*Params from exeute method*/) {
            //This is Worker Thread
            for (int i = params[0]; i < params[1]; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i /*Progress*/);
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean /*Result*/) {
            super.onPostExecute(aBoolean);
            //This is Main/UI Thread
            progressDialog.dismiss();
            if (aBoolean) {

            }
        }

        @Override
        protected void onProgressUpdate(Integer... values /*Progress*/) {
            super.onProgressUpdate(values);
            //This is Main/UI Thread

            ((TextView) findViewById(R.id.TxtView)).setText(String.valueOf(values[0]));
        }
    }
}
