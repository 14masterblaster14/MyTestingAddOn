package com.example.a29webservices;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.a29webservices.dto.Joke;
import com.example.a29webservices.dto.JokeInfo;
import com.example.a29webservices.web.SingletonRequestQueue;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

        findViewById(R.id.ButtonGet).setOnClickListener(this::click);
        findViewById(R.id.ButtonPost).setOnClickListener(this::click);
    }

    private void click(View view) {
        if (view.getId() == R.id.ButtonGet) get();
        else post();
    }

    private void get() {
        //You can show Progress Dialog here..
        SingletonRequestQueue.myQueue(this)
                .add(new StringRequest("http://api.icndb.com/jokes/random/3",
                        this::onJoke,
                        this::onJokeError));
    }

    private void onJokeError(VolleyError volleyError) {
        // You should dismiss Progress Dialog here..
        Log.i("@MasterBlaster", "Volley Error - " + volleyError.toString());
    }

    private void onJoke(String s) {
        // You should dismiss Progress Dialog here..
        Log.i("@MasterBlaster", "Json - " + s);

        Gson gson = new Gson();
        Joke joke = gson.fromJson(s, Joke.class);
        updateJokeList(joke);
    }

    private void updateJokeList(Joke joke) {

        ArrayList<String> myJokes = new ArrayList<>();
        for (JokeInfo jokeInfo : joke.getValue()) {
            myJokes.add(jokeInfo.getJoke());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myJokes);
        ((ListView) findViewById(R.id.ListViewJokes)).setAdapter(adapter);
    }

    private void post() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "MasterBlaster");
            jsonObject.put("job", "Project Manager");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SingletonRequestQueue.myQueue(this)
                .add(new JsonObjectRequest("https://reqres.in/api/users", jsonObject,
                        this::onPostSuccess,
                        this::onPostError));
    }

    private void onPostSuccess(JSONObject jsonObject) {
        Log.i("@MasterBlaster", "Response - " + jsonObject.toString());
    }

    private void onPostError(VolleyError error) {
        Log.i("@MasterBlaster", "Error - " + error.toString());
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
