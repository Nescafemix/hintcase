package com.joanfuentes.hintcaseassets.hintcontentholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanfuentes.hintcase.HintCase;

public class ByLayoutHintContentHolder extends HintContentHolder {
    int resLayoutId;

    public ByLayoutHintContentHolder(int resLayoutId) {
        super();
        this.resLayoutId = resLayoutId;
    }

    @Override
    public View getView(Context context, HintCase hintCase, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resLayoutId, parent, false);
        return view;
    }
}
