package com.joanfuentes.hintcaseassets.hintcontentholders;

import android.widget.FrameLayout;

import com.joanfuentes.hintcase.ContentHolder;

public abstract class HintContentHolder extends ContentHolder {
    public FrameLayout.LayoutParams getParentLayoutParams(int width, int height, int gravity) {
        return new FrameLayout.LayoutParams(width, height, gravity);
    }

    public FrameLayout.LayoutParams getParentLayoutParams(int width, int height, int gravity,
                                                          int marginLeft, int marginTop,
                                                          int marginRight, int marginBottom) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height, gravity);
        layoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        return layoutParams;
    }
}
