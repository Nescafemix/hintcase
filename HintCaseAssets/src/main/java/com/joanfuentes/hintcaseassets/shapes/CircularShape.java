package com.joanfuentes.hintcaseassets.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.joanfuentes.hintcase.Shape;

public class CircularShape extends Shape {
    private static final float DEFAULT_MIN_RADIUS = 50;
    private static final float DEFAULT_MAX_RADIUS = 10000;

    private float minRadius = DEFAULT_MIN_RADIUS;
    private float maxRadius = DEFAULT_MAX_RADIUS;
    private float currentRadius = DEFAULT_MAX_RADIUS;

    public float getMinRadius() {
        return minRadius;
    }

    public float getMaxRadius() {
        return maxRadius;
    }

    public float getCurrentRadius() {
        return currentRadius;
    }

    public void setCurrentRadius(float currentRadius) {
        this.currentRadius = currentRadius;
    }

    @Override
    public void setMinimumValue() {
        currentRadius = minRadius;
    }

    @Override
    public void setShapeInfo(View targetView, ViewGroup parent, int offset, Context context) {
        if (targetView != null) {
            minRadius = (Math.max(targetView.getMeasuredWidth(),targetView.getMeasuredHeight()) / 2) + offset;
            maxRadius = Math.max(parent.getHeight(), parent.getWidth());
            int[] targetViewLocationInWindow = new int[2];
            targetView.getLocationInWindow(targetViewLocationInWindow);
            setCenterX(targetViewLocationInWindow[0] + targetView.getWidth() / 2);
            setCenterY(targetViewLocationInWindow[1] + targetView.getHeight() / 2);
            setLeft((int) (getCenterX() - minRadius));
            setRight((int) (getCenterX() + minRadius));
            setTop((int) (getCenterY()  - minRadius));
            setBottom((int) (getCenterY() + minRadius));
            setWidth(minRadius * 2);
            setHeight(minRadius * 2);
        } else {
            if (parent != null) {
                minRadius = 0;
                maxRadius = parent.getHeight();
                setCenterX(parent.getMeasuredWidth() / 2);
                setCenterY(parent.getMeasuredHeight() / 2);
                setLeft(0);
                setTop(0);
                setRight(0);
                setBottom(0);
            }
        }
        currentRadius = maxRadius;
    }

    @Override
    public boolean isTouchEventInsideTheHint(MotionEvent event) {
        float xDelta = Math.abs(event.getRawX() - getCenterX());
        float yDelta = Math.abs(event.getRawY() - getCenterY());
        double distanceFromFocus = Math.sqrt(Math.pow(xDelta, 2) + Math.pow(yDelta, 2));
        return distanceFromFocus <= minRadius;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(getCenterX(), getCenterY(), currentRadius, getShapePaint());
    }
}
