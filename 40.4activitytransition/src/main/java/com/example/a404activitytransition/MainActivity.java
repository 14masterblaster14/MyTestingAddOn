package com.example.a404activitytransition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
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

        //findViewById(R.id.shared_elements).setOnClickListener(this :: sharedElementTransition);

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
