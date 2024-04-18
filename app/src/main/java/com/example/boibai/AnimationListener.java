package com.example.boibai;

import android.view.animation.Animation;

public interface AnimationListener extends Animation.AnimationListener {
    @Override
    default void onAnimationEnd(Animation animation) {

    }

    @Override
    default void onAnimationStart(Animation animation) {

    }

    @Override
    default void onAnimationRepeat(Animation animation) {

    }
}
