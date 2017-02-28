package com.example.a404activitytransition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by comp on 2/28/2017.
 */

public class SharedElementActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //  Inside your activity (if you did not enable transition in your theme)
        //  For AppCompat getWindow must be called before calling super.OnCreate().
        //  Must be called before SetContentView
        // Enable Window Content Transitions - Either specify here or specify in the Activity

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);

        initPage();
    }

    @Override
    public boolean onSupportNavigateUp() {
        // return super.onSupportNavigateUp();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        return true;
    }

    private void initPage() {


        // Shared element transaction

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shared Element Transition");

        //Button buttonExit = (Button)

        findViewById(R.id.Exit).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });

        // Circular revelation

        relativeLayout = (RelativeLayout) findViewById(R.id.shared_elements_expand);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeCircularRevealAnimation(relativeLayout);
            }
        });
    }

    private void makeCircularRevealAnimation(RelativeLayout relativeLayout) {

        final TextView textDesc = (TextView) findViewById(R.id.textCircularRevelation);

        int centerX = (relativeLayout.getLeft() + relativeLayout.getRight()) / 2;
        int centerY = (relativeLayout.getTop() + relativeLayout.getBottom()) / 2;
        float radius = Math.max(textDesc.getWidth(), textDesc.getHeight()) * 2.0f;

        if (textDesc.getVisibility() == View.INVISIBLE) {
            textDesc.setVisibility(View.VISIBLE);
            textDesc.setText(R.string.description);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ViewAnimationUtils.createCircularReveal(textDesc, centerX, centerY, 0, radius).start();
            }
        } else {

            Animator reverseCircularAnimation = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                reverseCircularAnimation = ViewAnimationUtils.createCircularReveal(textDesc, centerX, centerY, radius, 0);
            }

            reverseCircularAnimation.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    //super.onAnimationEnd(animation);
                    textDesc.setVisibility(View.INVISIBLE);
                }
            });
            reverseCircularAnimation.start();

        }
    }

}
