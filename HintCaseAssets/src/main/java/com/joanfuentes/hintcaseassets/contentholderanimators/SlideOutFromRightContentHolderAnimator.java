package com.joanfuentes.hintcaseassets.contentholderanimators;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import com.joanfuentes.hintcase.ContentHolderAnimator;

public class SlideOutFromRightContentHolderAnimator extends ContentHolderAnimator {

    public SlideOutFromRightContentHolderAnimator() {
        super();
    }

    public SlideOutFromRightContentHolderAnimator(int durationInMilliseconds) {
        super(durationInMilliseconds);
    }

    @Override
    public ValueAnimator getAnimator(final View view, final OnFinishListener onFinishListener) {
        float spaceUntilRightSide = view.getRootView().getWidth() - view.getLeft();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 0,
                spaceUntilRightSide);
        animator.setDuration(durationInMilliseconds);
        animator.setStartDelay(startDelayInMilliseconds);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setAlpha(1);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (onFinishListener != NO_CALLBACK) {
                    onFinishListener.onFinish();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) { }

            @Override
            public void onAnimationRepeat(Animator animation) { }
        });
        return animator;
    }
}
