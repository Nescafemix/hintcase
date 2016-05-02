package com.joanfuentes.hintcaseassets.hintcontentholders;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joanfuentes.hintcase.HintCase;

public class SimpleHintContentHolder extends HintContentHolder {
    public static final int NO_IMAGE = -1;

    private ImageView imageView;
    private int imageResourceId;
    private CharSequence contentTitle;
    private CharSequence contentText;
    private int titleStyleId;
    private int textStyleId;
    private int marginLeft;
    private int marginTop;
    private int marginRight;
    private int marginBottom;
    private int gravity;

    @Override
    public View getView(Context context, final HintCase hintCase, ViewGroup parent) {
        FrameLayout.LayoutParams frameLayoutParamsBlock = getParentLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                gravity, marginLeft, marginTop, marginRight, marginBottom);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(frameLayoutParamsBlock);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        if (contentTitle != null) {
            linearLayout.addView(getTextViewTitle(context));
        }
        if (existImage()) {
            linearLayout.addView(getImage(context, imageView, imageResourceId));
        }
        if (contentText != null) {
            linearLayout.addView(getTextViewDescription(context));
        }
        linearLayout.setAlpha(0);

        return linearLayout;
    }

    private ImageView getImage(Context context, ImageView image, int imageResourceId) {
        LinearLayout.LayoutParams linearLayoutParamsImage =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f);
        linearLayoutParamsImage.setMargins(0, 20, 0, 50);
        if (image == null) {
            image = new ImageView(context);
        }
        if (imageResourceId != SimpleHintContentHolder.NO_IMAGE) {
            image.setImageResource(imageResourceId);
        }
        image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        image.setAdjustViewBounds(true);
        image.setLayoutParams(linearLayoutParamsImage);
        return image;
    }

    private View getTextViewTitle(Context context) {
        LinearLayout.LayoutParams linearLayoutParamsTitle =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParamsTitle.setMargins(0, 20, 0, 20);
        TextView textViewTitle = new TextView(context);
        textViewTitle.setLayoutParams(linearLayoutParamsTitle);
        textViewTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        SpannableString spannableStringTitle= new SpannableString(contentTitle);
        TextAppearanceSpan titleTextAppearanceSpan = new TextAppearanceSpan(context, titleStyleId);
        spannableStringTitle.setSpan(titleTextAppearanceSpan, 0, spannableStringTitle.length(), 0);
        textViewTitle.setText(spannableStringTitle);
        return textViewTitle;
    }

    private View getTextViewDescription(Context context) {
        LinearLayout.LayoutParams linearLayoutParamsText =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textViewDescription = new TextView(context);
        textViewDescription.setLayoutParams(linearLayoutParamsText);
        textViewDescription.setGravity(Gravity.CENTER_HORIZONTAL);
        SpannableString spannableStringText= new SpannableString(contentText);
        TextAppearanceSpan textTextAppearanceSpan = new TextAppearanceSpan(context, textStyleId);
        spannableStringText.setSpan(textTextAppearanceSpan, 0, spannableStringText.length(), 0);
        textViewDescription.setText(spannableStringText);
        return textViewDescription;
    }

    private boolean existImage() {
        return imageView != null ||
                imageResourceId != SimpleHintContentHolder.NO_IMAGE;
    }

    public static class Builder {
        private SimpleHintContentHolder blockInfo;
        private Context context;

        public Builder(Context context) {
            this.context = context;
            this.blockInfo = new SimpleHintContentHolder();
            this.blockInfo.imageResourceId = NO_IMAGE;
            this.blockInfo.gravity = Gravity.CENTER;
        }

        public Builder setImageDrawableId(int resourceId) {
            blockInfo.imageResourceId = resourceId;
            return this;
        }

        public Builder setImageView(ImageView imageView) {
            blockInfo.imageView = imageView;
            return this;
        }

        public Builder setContentTitle(CharSequence title) {
            blockInfo.contentTitle = title;
            return this;
        }

        public Builder setContentTitle(int resId) {
            blockInfo.contentTitle = context.getString(resId);
            return this;
        }

        public Builder setTitleStyle(int resId) {
            blockInfo.titleStyleId = resId;
            return this;
        }

        public Builder setContentText(CharSequence text) {
            blockInfo.contentText = text;
            return this;
        }

        public Builder setContentText(int resId) {
            blockInfo.contentText = context.getString(resId);
            return this;
        }

        public Builder setContentStyle(int resId) {
            blockInfo.textStyleId = resId;
            return this;
        }

        public Builder setGravity(int gravity) {
            blockInfo.gravity = gravity;
            return this;
        }

        public Builder setMargin(int left, int top, int right, int bottom) {
            blockInfo.marginLeft = left;
            blockInfo.marginTop = top;
            blockInfo.marginRight = right;
            blockInfo.marginBottom = bottom;
            return this;
        }

        public Builder setMarginByResourcesId(int left, int top, int right, int bottom) {
            blockInfo.marginLeft = context.getResources().getDimensionPixelSize(left);
            blockInfo.marginTop = context.getResources().getDimensionPixelSize(top);
            blockInfo.marginRight = context.getResources().getDimensionPixelSize(right);
            blockInfo.marginBottom = context.getResources().getDimensionPixelSize(bottom);
            return this;
        }

        public SimpleHintContentHolder build() {
            return blockInfo;
        }
    }
}
