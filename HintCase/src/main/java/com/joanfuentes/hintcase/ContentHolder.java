package com.joanfuentes.hintcase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class ContentHolder {
    abstract public View getView(Context context, HintCase hintCase, ViewGroup parent);

    public void onLayout() { }
}
