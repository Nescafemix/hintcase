package com.joanfuentes.hintcase;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.joanfuentes.hintcase.utils.DimenUtils;
import com.joanfuentes.hintcase.utils.RoundRect;

public class RectangularShape extends Shape {
    private static final float DEFAULT_MIN_HEIGHT = 50;
    private static final float DEFAULT_MAX_HEIGHT = 10000;

    private float maxHeight = DEFAULT_MAX_HEIGHT;
    private float minHeight = DEFAULT_MIN_HEIGHT;
    private float minWidth = DEFAULT_MIN_HEIGHT;
    private float maxWidth = DEFAULT_MAX_HEIGHT;
    private float currentHeight = DEFAULT_MAX_HEIGHT;
    private float currentWidth = DEFAULT_MAX_HEIGHT;

    public RectangularShape() {
        super();
    }

    @Override
    public void setMinimumValue() {
        currentWidth = minWidth;
        currentHeight = minHeight;
    }

    @Override
    public void setShapeInfo(View targetView, ViewGroup parent, int offset, Context context) {
        if (targetView != null) {
            minHeight = targetView.getMeasuredHeight() + offset;
            minWidth = targetView.getMeasuredWidth() + offset;
            maxHeight = parent.getHeight();
            maxWidth = parent.getWidth();
            int[] targetViewLocationInWindow = new int[2];
            targetView.getLocationInWindow(targetViewLocationInWindow);
            setCenterX(targetViewLocationInWindow[0] + targetView.getWidth() / 2);
            setCenterY(targetViewLocationInWindow[1] + targetView.getHeight() / 2);
            setLeft(targetViewLocationInWindow[0]);
            setRight(targetViewLocationInWindow[0] + ((int) getCenterX() - getLeft()) * 2);
            setTop(targetViewLocationInWindow[1]);
            setBottom(targetViewLocationInWindow[1] + ((int) getCenterY() - getTop()) * 2);
            setWidth(getRight() - getLeft());
            setHeight(getBottom() - getTop());
        } else {
            if (parent != null) {
                minHeight = 0;
                minWidth = 0;
                maxHeight = parent.getHeight();
                maxWidth = parent.getWidth();
                setCenterX(parent.getMeasuredWidth() / 2);
                setCenterY(parent.getMeasuredHeight() / 2);
                setLeft(0);
                setTop(0);
                setRight(0);
                setBottom(0);
            }
        }
        currentHeight = maxHeight;
        currentWidth = maxWidth;
    }

    @Override
    public boolean isTouchEventInsideTheHint(MotionEvent event) {
        return event.getRawX() <= getRight()
                && event.getRawX() >= getLeft()
                && event.getRawY() <= getBottom()
                && event.getRawY() >= getTop();
    }

    @Override
    public void draw(Canvas canvas) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawRoundRectAfterLollipop(canvas);
        } else {
            drawRoundRectPreLollipop(canvas);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void drawRoundRectAfterLollipop(Canvas canvas) {
        canvas.drawRoundRect(getCenterX() - (currentWidth / 2),
                getCenterY() - (currentHeight / 2),
                getCenterX() + (currentWidth / 2),
                getCenterY() + (currentHeight / 2),
                10f,
                10f,
                getShapePaint());
    }

    private void drawRoundRectPreLollipop(Canvas canvas) {
        RoundRect roundRect = new RoundRect(getCenterX() - (currentWidth / 2),
                getCenterY() - (currentHeight / 2),
                getCenterX() + (currentWidth / 2),
                getCenterY() + (currentHeight / 2),
                10f,
                10f);
        canvas.drawPath(roundRect.getPath(), getShapePaint());
    }

    public float getMinHeight() {
        return minHeight;
    }

    public float getMaxHeight() {
        return maxHeight;
    }

    public float getMinWidth() {
        return minWidth;
    }

    public float getMaxWidth() {
        return maxWidth;
    }

    public float getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(float currentHeight) {
        this.currentHeight = currentHeight;
    }

    public void setCurrentWidth(float currentWidth) {
        this.currentWidth = currentWidth;
    }
}
