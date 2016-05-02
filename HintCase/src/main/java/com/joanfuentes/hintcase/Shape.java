package com.joanfuentes.hintcase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public abstract class Shape {
    private static final int DEFAULT_COLOR = 0xFFFFFF;
    private static final int DEFAULT_ALPHA = 0;
    private int left;
    private int top;
    private int right;
    private int bottom;
    private float centerX;
    private float centerY;
    private float width;
    private float height;
    private Paint shapePaint;

    public Shape() {
        shapePaint = getInitializedTypePaint();
    }

    private Paint getInitializedTypePaint() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(DEFAULT_COLOR);
        paint.setAlpha(DEFAULT_ALPHA);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        return paint;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Paint getShapePaint() {
        return shapePaint;
    }

    abstract public void setMinimumValue();
    abstract public void setShapeInfo(View targetView, ViewGroup parent, int offset, Context context);
    abstract public boolean isTouchEventInsideTheHint(MotionEvent event);
    abstract public void draw(Canvas canvas);
}
