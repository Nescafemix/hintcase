package com.joanfuentes.hintcase;

import android.animation.ValueAnimator;
import android.view.View;

public abstract class ShapeAnimator {
    public static final int DEFAULT_ANIMATION_DURATION_IN_MILLISECONDS = 300;
    public static final ShapeAnimator NO_ANIMATOR = null;
    public static final OnFinishListener NO_CALLBACK = null;

    abstract public ValueAnimator getAnimator(View view, Shape shape, OnFinishListener onFinishListener);

    public ValueAnimator getAnimator(View view, Shape shape) {
        return getAnimator(view, shape, NO_CALLBACK);
    }

    public interface OnFinishListener {
        void onFinish();
    }
}