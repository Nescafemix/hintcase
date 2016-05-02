package com.joanfuentes.hintcaseassets.extracontentholders;

import android.widget.RelativeLayout;

import com.joanfuentes.hintcase.ContentHolder;

public abstract class ExtraContentHolder extends ContentHolder {
    public RelativeLayout.LayoutParams getParentLayoutParams(int width, int height, int... rules) {
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(width,height);
        for (int rule : rules) {
            layoutParams.addRule(rule);
        }
        return layoutParams;
    }
}
