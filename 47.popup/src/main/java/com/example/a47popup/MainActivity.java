package com.example.a47popup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.BtnPopUp1).setOnClickListener(new View.OnClickListener() {
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

        findViewById(R.id.BtnPopUp2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(MainActivity.this, v);
                popup.setOnMenuItemClickListener(MainActivity.this);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.my_popup, popup.getMenu());
                popup.show();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        String title = (String) item.getTitle();
        Toast.makeText(this, "Movie Selected " + title + " Movies", Toast.LENGTH_LONG).show();
        switch (item.getItemId()) {

            case R.id.action_id:
                break;
            case R.id.romantic_id:
                break;
            case R.id.Suspense_id:
                break;
            case R.id.thriller_id:
                break;
        }
        return false;
    }
}
