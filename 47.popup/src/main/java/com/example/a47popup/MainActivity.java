package com.example.a47popup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.BtnPopUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = getLayoutInflater();

                View popUpView = layoutInflater.inflate(R.layout.popup_layout, null);
                LinearLayout popUpLayout = (LinearLayout) popUpView.findViewById(R.id.popUp_Layout);

                final PopupWindow popupWindow = new PopupWindow(popUpView,
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setContentView(popUpView);
                popupWindow.showAsDropDown(popUpLayout, 0, 0);
            }
        });
    }
}
