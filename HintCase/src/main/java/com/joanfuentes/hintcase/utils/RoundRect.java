package com.joanfuentes.hintcase.utils;

import android.graphics.Path;

public class RoundRect {
    private Path path;

    public RoundRect(float left, float top, float right, float bottom, float rx, float ry) {
        init(left, top, right, bottom, rx, ry, true, true, true, true);
    }

    public RoundRect(float left, float top, float right, float bottom, float rx, float ry,
              boolean tl, boolean tr, boolean br, boolean bl) {
        init(left, top, right, bottom, rx, ry, tl, tr, br, bl);
    }

    private void init(float left, float top, float right, float bottom, float rx, float ry,
                      boolean applyRoundToTopLeft, boolean applyRoundToTopRight,
                      boolean applyRoundToBottomRight, boolean applyRoundToBottomLeft) {
        float width = right - left;
        float height = bottom - top;
        rx = normalizeValue(rx, 0, width / 2);
        ry = normalizeValue(ry, 0, height / 2);
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * ry));
        path = new Path();
        path.moveTo(right, top + ry);
        drawTopRightCorner(rx, ry, applyRoundToTopRight);
        path.rLineTo(-widthMinusCorners, 0);
        drawTopLeftCorner(rx, ry, applyRoundToTopLeft);
        path.rLineTo(0, heightMinusCorners);
        drawBottomLeftCorner(rx, ry, applyRoundToBottomLeft);
        path.rLineTo(widthMinusCorners, 0);
        drawBottomRightCorner(rx, ry, applyRoundToBottomRight);
        path.rLineTo(0, -heightMinusCorners);
        path.close();
    }

    private void drawBottomRightCorner(float rx, float ry, boolean applyRoundToBottomRight) {
        if (applyRoundToBottomRight) {
            path.rQuadTo(rx, 0, rx, -ry);
        } else {
            path.rLineTo(rx, 0);
            path.rLineTo(0, -ry);
        }
    }

    private void drawBottomLeftCorner(float rx, float ry, boolean applyRoundToBottomLeft) {
        if (applyRoundToBottomLeft) {
            path.rQuadTo(0, ry, rx, ry);
        } else {
            path.rLineTo(0, ry);
            path.rLineTo(rx, 0);
        }
    }

    private void drawTopLeftCorner(float rx, float ry, boolean applyRoundToTopLeft) {
        if (applyRoundToTopLeft) {
            path.rQuadTo(-rx, 0, -rx, ry);
        } else {
            path.rLineTo(-rx, 0);
            path.rLineTo(0, ry);
        }
    }

    private void drawTopRightCorner(float rx, float ry, boolean applyRoundToTopRight) {
        if (applyRoundToTopRight) {
            path.rQuadTo(0, -ry, -rx, -ry);
        } else {
            path.rLineTo(0, -ry);
            path.rLineTo(-rx, 0);
        }
    }

    private float normalizeValue(float value, float min, float max) {
        if (value < min) {
            value = 0;
        }
        if (value > max) {
            value = max;
        }
        return value;
    }

    public Path getPath() {
        return path;
    }
}
