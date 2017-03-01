package com.example.a403touchfeedbackanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by comp on 2/28/2017.
 */

public class RippleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);

        getSupportActionBar().setTitle("Ripple Animations !");
    }

    //This dummyClick class needed to enable Ripple effect on Views.
    //Without click event, the Ripple Effect is not visible on views.

    public void dummyClick(View view) {

    }
}
