package com.example.a404activitytransition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageLogo, imagePic;
    private TextView textView;
    private RelativeLayout revealDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageLogo = (ImageView) findViewById(R.id.Img_Logo);
        imagePic = (ImageView) findViewById(R.id.Img_IronMan);
        textView = (TextView) findViewById(R.id.TxtView_MasterBlaster);

        //Setting up animations for Main Activity
        setupWindowAnimations();

        //findViewById(R.id.shared_elements).setOnClickListener(this :: sharedElementTransition);
        findViewById(R.id.Btn_Explode_ByJava).setOnClickListener(this::explodeTransitionJava);
        findViewById(R.id.Btn_Explode_ByXML).setOnClickListener(this::explodeTransitionByXML);
        findViewById(R.id.Btn_Slide_ByJava).setOnClickListener(this::slideTransitionByJava);
        findViewById(R.id.Btn_Slide_ByXML).setOnClickListener(this::slideTransitionByXML);
        findViewById(R.id.Btn_Fade_ByJava).setOnClickListener(this::fadeTransitionByJava);
        findViewById(R.id.Btn_Fade_ByXML).setOnClickListener(this::fadeTransitionByXML);

    }

    private void setupWindowAnimations() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Slide slideTransition = new Slide();
            //Starting from Right to Left
            slideTransition.setSlideEdge(Gravity.LEFT);
            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
            //Reenter transition is executed when returning back to this activity
            getWindow().setExitTransition(slideTransition); //When Main Activity Exits the screen
            getWindow().setReenterTransition(slideTransition); //When Main Activity Re-enter the screen

            //For overlap of Reentering Activity to Main Activity.java and
            //Exiting the TransitionActivity.java
            getWindow().setAllowReturnTransitionOverlap(false);

        }
    }

    private void explodeTransitionJava(View view) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
            intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.ExplodeJava);
            intent.putExtra(Constants.KEY_TITLE, "Explode By Java");
            startActivity(intent, activityOptions.toBundle());
        } else {
            Intent intent = new Intent(MainActivity.this, Transition1Activity.class);
            startActivity(intent);
        }

    }

    private void explodeTransitionByXML(View view) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
            intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.ExplodeXML);
            intent.putExtra(Constants.KEY_TITLE, "Explode By XML");
            startActivity(intent, activityOptions.toBundle());
        } else {
            Intent intent = new Intent(MainActivity.this, Transition1Activity.class);
            startActivity(intent);
        }
    }

    private void slideTransitionByJava(View view) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
            intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.SlideJava);
            intent.putExtra(Constants.KEY_TITLE, "Slide By Java");
            startActivity(intent, activityOptions.toBundle());
        } else {
            Intent intent = new Intent(MainActivity.this, Transition1Activity.class);
            startActivity(intent);
        }
    }

    private void slideTransitionByXML(View view) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
            intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.SlideXML);
            intent.putExtra(Constants.KEY_TITLE, "Slide By XML");
            startActivity(intent, activityOptions.toBundle());
        } else {
            Intent intent = new Intent(MainActivity.this, Transition1Activity.class);
            startActivity(intent);
        }
    }

    private void fadeTransitionByJava(View view) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
            intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.FadeJava);
            intent.putExtra(Constants.KEY_TITLE, "Fade By Java");
            startActivity(intent, activityOptions.toBundle());
        } else {
            Intent intent = new Intent(MainActivity.this, Transition1Activity.class);
            startActivity(intent);
        }
    }

    private void fadeTransitionByXML(View view) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
            intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.FadeXML);
            intent.putExtra(Constants.KEY_TITLE, "Fade By XML");
            startActivity(intent, activityOptions.toBundle());
        } else {
            Intent intent = new Intent(MainActivity.this, Transition1Activity.class);
            startActivity(intent);
        }
    }


    public void sharedElementTransition(View view) {

        Pair[] pair = new Pair[3];
        pair[0] = new Pair<View, String>(imageLogo, "logo_shared");
        pair[1] = new Pair<View, String>(textView, "text_shared");
        pair[2] = new Pair<View, String>(imagePic, "img_shared");

        ActivityOptions activityOptions = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, pair);
        }
        Intent intent = new Intent(MainActivity.this, SharedElementActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, activityOptions.toBundle());
        }
    }
}
