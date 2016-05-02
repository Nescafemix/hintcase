package com.joanfuentes.hintcaseassets.extracontentholders;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.ViewGroup;

import com.joanfuentes.hintcase.HintCase;

public class SimpleButtonContentHolder extends ExtraContentHolder {
    private int width;
    private int height;
    private int[] rules;
    private CharSequence text;
    private int buttonStyleId;
    private boolean closeHintOnClick;
    private OnClickButtonListener onClickButtonListener;

    SimpleButtonContentHolder() {
        this.closeHintOnClick = false;
    }

    @Override
    public View getView(Context context, final HintCase hintCase, ViewGroup parent) {
        AppCompatButton button;
        if (buttonStyleId != 0) {
            button = new AppCompatButton(context, null, buttonStyleId);
        } else {
            button = new AppCompatButton(context);
        }
        button.setText(text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickButtonListener != null) {
                    onClickButtonListener.onClick();
                }
                if (closeHintOnClick) {
                    hintCase.hide();
                }
            }
        });
        button.setLayoutParams(getParentLayoutParams(width, height, rules));
        return button;
    }

    public static class Builder {
        private SimpleButtonContentHolder buttonBlock;
        private Context context;

        public Builder(Context context) {
            this.context = context;
            this.buttonBlock = new SimpleButtonContentHolder();
        }

        public Builder setWidth(int width) {
            buttonBlock.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            buttonBlock.height = height;
            return this;
        }

        public Builder setRules(int... rules) {
            buttonBlock.rules = rules;
            return this;
        }

        public Builder setButtonText(CharSequence text) {
            buttonBlock.text = text;
            return this;
        }

        public Builder setButtonText(int resId) {
            buttonBlock.text = context.getString(resId);
            return this;
        }

        public Builder setButtonStyle(int resId) {
            buttonBlock.buttonStyleId = resId;
            return this;
        }

        public Builder setOnClick(OnClickButtonListener listener) {
            buttonBlock.onClickButtonListener = listener;
            return this;
        }

        public Builder setCloseHintCaseOnClick(boolean closeHintOnClick) {
            buttonBlock.closeHintOnClick = closeHintOnClick;
            return this;
        }

        public SimpleButtonContentHolder build() {
            return buttonBlock;
        }
    }

    public interface OnClickButtonListener {
        void onClick();
    }
}
