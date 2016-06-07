package com.joanfuentes.hintcaseassets.contentholderanimators;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import com.joanfuentes.hintcase.ContentHolderAnimator;

public class FadeInContentHolderAnimator extends ContentHolderAnimator {

    public FadeInContentHolderAnimator() {
        super();
    }

    public FadeInContentHolderAnimator(int durationInMilliseconds) {
        super(durationInMilliseconds);
    }

    @Override
    public ValueAnimator getAnimator(View view, final OnFinishListener onFinishListener) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1);
        animator.setDuration(durationInMilliseconds);
        animator.setStartDelay(startDelayInMilliseconds);
        if (onFinishListener != NO_CALLBACK) {
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) { }

                @Override
                public void onAnimationEnd(Animator animation) {
                    onFinishListener.onFinish();
                }

                @Override
                public void onAnimationCancel(Animator animation) { }

                @Override
                public void onAnimationRepeat(Animator animation) { }
            });
        }
        return animator;
    }
}
