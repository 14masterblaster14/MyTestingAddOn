package com.example.a37recyclerview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by comp on 2/23/2017.
 */

public class MyAnimation {

    public static void animate(RecyclerView.ViewHolder viewHolder, boolean goesDown) {

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator objectAnimatorTranslateY = ObjectAnimator.ofFloat(viewHolder.itemView, "translationY",
                goesDown == true ? 200 : -200, 0);
        objectAnimatorTranslateY.setDuration(1000);

        ObjectAnimator objectAnimatorTranslateX = ObjectAnimator.ofFloat(viewHolder.itemView, "translationX",
                -50, 50, -30, 30, -20, 20, -5, 5, 0);
        objectAnimatorTranslateX.setDuration(1000);

        //animatorSet.playTogether(objectAnimatorTranslateY);
        animatorSet.playTogether(objectAnimatorTranslateX, objectAnimatorTranslateY);
        animatorSet.start();


    }
}
