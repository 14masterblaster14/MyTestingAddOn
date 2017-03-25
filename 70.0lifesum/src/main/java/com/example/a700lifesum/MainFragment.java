package com.example.a700lifesum;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by comp on 3/22/2017.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.main_fragment, container, false);

         /* Insert the font for our title */
        final TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "CalligraphyFLF.ttf");
        tvTitle.setTypeface(typeface);

        /* Do something when the user clicks the button */
        final LinearLayout beHealtheir = (LinearLayout) view.findViewById(R.id.be_healthier);
        beHealtheir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Be Healthier", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}


