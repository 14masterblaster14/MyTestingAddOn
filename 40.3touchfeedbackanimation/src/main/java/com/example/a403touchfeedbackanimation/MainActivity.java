package com.example.a403touchfeedbackanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.BtnRippleEffect).setOnClickListener(this::showRippleAnimation);
    }

    private void showRippleAnimation(View view) {

        Intent intent = new Intent(this, RippleActivity.class);
        startActivity(intent);
    }

}
