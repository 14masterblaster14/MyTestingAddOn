package com.example.a461searchrecyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // String[] s_names = getResources().getStringArray(R.array.Actors);
    String[] s_names = {"Shahrukh1", "Ajay2", "Ranvir1", "Irfan", "Shahrukh2", "Amir1", "Ranvir2",
            "Nawazuddin", "Shahid", "All", "Ajay2", "Ranvir3", "Shahrukh3", "Raj", "Amitabh",
            "Amir2", "Priyanka", "Abhishek", "Imran", "Akshay", "Amir3"};

    int[] s_actors = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
            R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
            R.drawable.s11, R.drawable.s12, R.drawable.s13, R.drawable.s14, R.drawable.s15,
            R.drawable.s16, R.drawable.s17, R.drawable.s18, R.drawable.s19, R.drawable.s20, R.drawable.s21};

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Actors> dataset = new ArrayList<>();

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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        int count = 0;

        for (String Name : s_names) {

            dataset.add(new Actors(Name, s_actors[count]));
            count++;
        }
        recyclerAdapter = new RecyclerAdapter(dataset);
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();
        ArrayList<Actors> newList = new ArrayList<>();
        for (Actors actors : dataset) {
            String name = actors.getNames().toLowerCase();
            if (name.contains(newText))
                newList.add(actors);
        }

        recyclerAdapter.setFilter(newList);
        return true;
    }
}
