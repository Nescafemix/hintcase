package com.joanfuentes.hintcase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DimenRes;
import android.view.View;

import com.joanfuentes.hintcase.utils.DimenUtils;

public class HintCase {
    public static final int DEFAULT_SHAPE_OFFSET_IN_DP = 10;
    public static final int NO_OFFSET_IN_PX = 0;
    public static final int BACKGROUND_COLOR_TRANSPARENT = 0x00000000;
    public static final int HINT_BLOCK_POSITION_LEFT = 0;
    public static final int HINT_BLOCK_POSITION_TOP = 1;
    public static final int HINT_BLOCK_POSITION_RIGHT = 2;
    public static final int HINT_BLOCK_POSITION_BOTTOM = 3;
    public static final int HINT_BLOCK_POSITION_CENTER = 4;
    public static final boolean TARGET_IS_CLICKABLE = true;
    public static final boolean TARGET_IS_NOT_CLICKABLE = false;

    private HintCaseView hintCaseView;
    private Context context;

    public Shape getShape() {
        return this.hintCaseView.getShape();
    }

    public void hide() {
        this.hintCaseView.performHide();
        this.hintCaseView = null;
    }

    public interface OnClosedListener {
        void onClosed();
    }

    public HintCase(View view) {
        this.context = view.getContext();
        this.hintCaseView = new HintCaseView(context, this);
        this.hintCaseView.setTargetInfo(null, new RectangularShape(), NO_OFFSET_IN_PX,
                TARGET_IS_NOT_CLICKABLE);
        this.hintCaseView.setParentView(view);
    }

    public View getView() {
        return this.hintCaseView;
    }

    public HintCase setTarget(View target) {
        int offsetInPx = DimenUtils.dipToPixels(context, HintCase.DEFAULT_SHAPE_OFFSET_IN_DP);
        return setCompleteTarget(target, new RectangularShape(), offsetInPx, TARGET_IS_CLICKABLE);
    }

    public HintCase setTarget(View target,  @DimenRes int offsetResId) {
        int offsetInPx = context.getResources().getDimensionPixelSize(offsetResId);
        return setCompleteTarget(target, new RectangularShape(), offsetInPx, TARGET_IS_CLICKABLE);
    }

    public HintCase setTarget(View target, boolean isTargetClickable) {
        int offsetInPx = DimenUtils.dipToPixels(context, HintCase.DEFAULT_SHAPE_OFFSET_IN_DP);
        return setCompleteTarget(target, new RectangularShape(), offsetInPx, isTargetClickable);
    }

    public HintCase setTarget(View target, boolean isTargetClickable, @DimenRes int offsetResId) {
        int offsetInPx = context.getResources().getDimensionPixelSize(offsetResId);
        return setCompleteTarget(target, new RectangularShape(), offsetInPx, isTargetClickable);
    }

    public HintCase setTarget(View target, Shape shape) {
        int offsetInPx = DimenUtils.dipToPixels(context, HintCase.DEFAULT_SHAPE_OFFSET_IN_DP);
        return setCompleteTarget(target, shape, offsetInPx, TARGET_IS_CLICKABLE);
    }

    public HintCase setTarget(View target, Shape shape, @DimenRes int offsetResId) {
        int offsetInPx = context.getResources().getDimensionPixelSize(offsetResId);
        return setCompleteTarget(target, shape, offsetInPx, TARGET_IS_CLICKABLE);
    }

    public HintCase setTarget(View target, Shape shape, boolean isTargetClickable) {
        int offsetInPx = DimenUtils.dipToPixels(context, HintCase.DEFAULT_SHAPE_OFFSET_IN_DP);
        return setCompleteTarget(target, shape, offsetInPx, isTargetClickable);
    }

    public HintCase setTarget(View target, Shape shape, boolean isTargetClickable,
                              @DimenRes int offsetResId) {
        int offsetInPx = context.getResources().getDimensionPixelSize(offsetResId);
        return setCompleteTarget(target, shape, offsetInPx, isTargetClickable);
    }

    private HintCase setCompleteTarget(View target, Shape shape, int offsetInPx,
                                       boolean isTargetClickable) {
        this.hintCaseView.setTargetInfo(target, shape, offsetInPx, isTargetClickable);
        return this;
    }

    public HintCase setBackgroundColor(int color) {
        this.hintCaseView.setBackgroundColor(color);
        return this;
    }

    public HintCase setBackgroundColorByResourceId(int resourceId) {
        this.hintCaseView.setBackgroundColor(context.getResources().getColor(resourceId));
        return this;
    }

    public HintCase setShapeAnimators(ShapeAnimator showShapeAnimator) {
        return setShapeAnimators(showShapeAnimator, ShapeAnimator.NO_ANIMATOR);
    }

    public HintCase setShapeAnimators(ShapeAnimator showShapeAnimator,
                                      ShapeAnimator hideShapeAnimator) {
        this.hintCaseView.setShape(showShapeAnimator, hideShapeAnimator);
        return this;
    }

    public HintCase setHintBlock(ContentHolder contentHolder) {
        this.hintCaseView.setHintBlock(contentHolder, ContentHolderAnimator.NO_ANIMATOR, ContentHolderAnimator.NO_ANIMATOR);
        return this;
    }

    public HintCase setHintBlock(ContentHolder contentHolder, ContentHolderAnimator showContentHolderAnimator) {
        this.hintCaseView.setHintBlock(contentHolder, showContentHolderAnimator, ContentHolderAnimator.NO_ANIMATOR);
        return this;
    }

    public HintCase setHintBlock(ContentHolder contentHolder, ContentHolderAnimator showContentHolderAnimator,
                                 ContentHolderAnimator hideContentHolderAnimator) {
        this.hintCaseView.setHintBlock(contentHolder, showContentHolderAnimator, hideContentHolderAnimator);
        return this;
    }

    public HintCase setExtraBlock(ContentHolder contentHolder) {
        this.hintCaseView.setExtraBlock(contentHolder, ContentHolderAnimator.NO_ANIMATOR, ContentHolderAnimator.NO_ANIMATOR);
        return this;
    }

    public HintCase setExtraBlock(ContentHolder contentHolder, ContentHolderAnimator showContentHolderAnimator) {
        this.hintCaseView.setExtraBlock(contentHolder, showContentHolderAnimator, ContentHolderAnimator.NO_ANIMATOR);
        return this;
    }

    public HintCase setExtraBlock(ContentHolder contentHolder, ContentHolderAnimator showContentHolderAnimator,
                                             ContentHolderAnimator hideContentHolderAnimator) {
        this.hintCaseView.setExtraBlock(contentHolder, showContentHolderAnimator, hideContentHolderAnimator);
        return this;
    }

    public HintCase setCloseOnTouchView(boolean closeOnTouch) {
        this.hintCaseView.setCloseOnTouch(closeOnTouch);
        return this;
    }

    public HintCase setOverDecorView(boolean setOver, Activity activity) {
        if (activity != null) {
            setOverDecorView(setOver, activity.getWindow().getDecorView());
        } else {
            //TODO throw exception
        }
        return this;
    }

    public HintCase setOverDecorView(boolean setOver, View decorView) {
        if (setOver) {
            this.hintCaseView.setOverDecorView(decorView);
        }
        return this;
    }

    public HintCase setOnClosedListener(OnClosedListener onClosedListener) {
            this.hintCaseView.setOnClosedListener(onClosedListener);
            return this;
    }

    public int getBlockInfoPosition() {
        return this.hintCaseView.getHintBlockPosition();
    }

    public void show() {
        //TODO: Add controls to have minimum info.
        this.hintCaseView.show();
    }
}
