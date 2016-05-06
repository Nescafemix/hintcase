package com.joanfuentes.hintcaseassets.shapeanimators;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import com.joanfuentes.hintcase.ShapeAnimator;
import com.joanfuentes.hintcase.Shape;

public class FadeInShapeAnimator extends ShapeAnimator {

    public FadeInShapeAnimator() {
        super();
    }

    public FadeInShapeAnimator(int durationInMilliseconds) {
        super(durationInMilliseconds);
    }

    @Override
    public ValueAnimator getAnimator(View view, Shape shape,
                                     final OnFinishListener onFinishListener) {
        shape.setMinimumValue();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1);
        animator.setDuration(durationInMilliseconds);
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
