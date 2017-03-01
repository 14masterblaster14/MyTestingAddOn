package com.example.a404activitytransition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by comp on 3/1/2017.
 */

//  This activity is for the API below 21
public class Transition1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trasition);

        getSupportActionBar().setTitle(" Activity1 Transition !");

        findViewById(R.id.Btn_Exit_transition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
