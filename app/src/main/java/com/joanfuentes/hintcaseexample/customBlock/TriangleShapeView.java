package com.joanfuentes.hintcaseexample.customBlock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class TriangleShapeView extends View {
    private static Direction DEFAULT_TRIANGLE_DIRECTION = Direction.UP;

    public enum Direction { UP, DOWN, RIGHT, LEFT }

    private Path pathBackground = new Path();
    private Path pathLines = new Path();
    private Paint paintBackground = new Paint();
    private Paint paintLines = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int borderColor;
    private int backgroundColor;
    private int borderWith;
    private int shadowSize;
    private boolean useBorder;
    private Direction direction;

    public TriangleShapeView(Context context) {
        super(context);
        init();
    }

    public TriangleShapeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TriangleShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        direction = DEFAULT_TRIANGLE_DIRECTION;
        useBorder = false;
        pathBackground = new Path();
        paintBackground = new Paint();
        pathLines = new Path();
        paintLines = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintLines.setStyle(Paint.Style.STROKE);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        drawBackground(canvas, width, height);
        if (useBorder) {
            drawBorder(canvas, width, height);
        }
        if (direction != Direction.UP) {
            rotateView(direction);
        }
    }

    private void drawBorder(Canvas canvas, float width, float height) {
        paintLines.setColor(borderColor);
        paintLines.setStrokeWidth(borderWith);
        pathLines.reset();
        pathLines.moveTo(0, height);
        pathLines.lineTo(width / 2, height / 3);
        pathLines.lineTo(width, height);
        pathLines.lineTo(width / 2, height / 3);
        pathLines.lineTo(0, height);
        pathLines.close();
        canvas.drawPath(pathLines, paintLines);
    }

    private void drawBackground(Canvas canvas, float width, float height) {
        paintBackground.setColor(backgroundColor);
        pathBackground.reset();
        pathBackground.moveTo(0, height);
        pathBackground.lineTo(width / 2, height / 3);
        pathBackground.lineTo(width, height);
        pathBackground.close();
        paintBackground.setShadowLayer(shadowSize, 1, 1, Color.BLACK);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        canvas.drawPath(pathBackground, paintBackground);
    }

    private void rotateView(Direction direction) {
        switch (direction) {
            case RIGHT:
                this.setRotation(90);
                break;
            case DOWN:
                this.setRotation(180);
                break;
            case LEFT:
                this.setRotation(270);
        }
    }

    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
    }

    public void setBorder(int width, int color) {
        this.useBorder = true;
        this.borderWith = width;
        this.borderColor = color;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
    }
}