package com.example.a404activitytransition;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

/**
 * Created by comp on 3/1/2017.
 */

public class TransitionActivity extends AppCompatActivity {

    Constants.TransitionType transitionType;
    String toolbarTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //  Inside your activity (if you did not enable transition in your theme)
        //  For AppCompat getWindow must be called before calling super.OnCreate().
        //  Must be called before SetContentView
        // Enable Window Content Transitions - Either specify here or specify in the Activity

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trasition);

        initPage();
        initAnimation();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setAllowEnterTransitionOverlap(false);
        }
    }

    private void initPage() {

        transitionType = (Constants.TransitionType) getIntent().getSerializableExtra(Constants.KEY_ANIM_TYPE);
        toolbarTitle = getIntent().getExtras().getString(Constants.KEY_TITLE);

        findViewById(R.id.Btn_Exit_transition).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(toolbarTitle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // return super.onSupportNavigateUp();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // finish();    //Without Reverse Transition on back button
            // but it will be there for Navigation back button

            finishAfterTransition();  //With Reverse Transition
        }
        return true;
    }

    private void initAnimation() {

        switch (transitionType) {

            case ExplodeJava:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Explode enterTransition = new Explode();
                    enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
                    getWindow().setEnterTransition(enterTransition);
                }
                break;

            case ExplodeXML:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setEnterTransition(enterTransition);
                    }
                }
                break;

            case SlideJava:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Slide enterTransition = new Slide();
                    enterTransition.setSlideEdge(Gravity.BOTTOM);  //Comes from bottom
                    enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_long));
                    enterTransition.setInterpolator(new FastOutLinearInInterpolator());
                    getWindow().setEnterTransition(enterTransition);
                }
                break;

            case SlideXML:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.slide);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setEnterTransition(enterTransition);
                    }
                }

                break;

            case FadeJava:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Fade enterTransition = new Fade();
                    enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setEnterTransition(enterTransition);
                    }
                }
                break;

            case FadeXML:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.fade);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setEnterTransition(enterTransition);
                    }
                }

                break;
        }
    }
}



