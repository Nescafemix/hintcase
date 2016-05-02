package com.joanfuentes.hintcase;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.joanfuentes.hintcase.utils.DimenUtils;

public class HintCase {
    public static final int DEFAULT_SHAPE_OFFSET_IN_DP = 10;
    public static final int BACKGROUND_COLOR_TRANSPARENT = 0x00000000;
    public static final int HINT_BLOCK_POSITION_LEFT = 0;
    public static final int HINT_BLOCK_POSITION_TOP = 1;
    public static final int HINT_BLOCK_POSITION_RIGHT = 2;
    public static final int HINT_BLOCK_POSITION_BOTTOM = 3;
    public static final int HINT_BLOCK_POSITION_CENTER = 4;

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
        this.hintCaseView.setTargetInfo(null, new RectangularShape());
        this.hintCaseView.setParentView(view);
    }

    public View getView() {
        return this.hintCaseView;
    }

    public HintCase setTarget(View target) {
        return setTarget(target, new RectangularShape());
    }

    public HintCase setTarget(View target, Shape shape) {
        this.hintCaseView.setTargetInfo(target, shape);
        return this;
    }

    public HintCase setBackgroundColor(int color) {
        this.hintCaseView.setBackgroundColor(color);
        return this;
    }

    public HintCase setShapeAnimators(ShapeAnimator showShapeAnimator) {
        return setShapeAnimators(showShapeAnimator, ShapeAnimator.NO_ANIMATOR);
    }

    public HintCase setShapeAnimators(ShapeAnimator showShapeAnimator,
                                      ShapeAnimator hideShapeAnimator) {
        int offsetInPx = DimenUtils.dipToPixels(context, HintCase.DEFAULT_SHAPE_OFFSET_IN_DP);
        this.hintCaseView.setShape(offsetInPx, showShapeAnimator, hideShapeAnimator);
        return this;
    }

    public HintCase setShapeAnimators(int offsetResId, ShapeAnimator showShapeAnimator,
                                      ShapeAnimator hideShapeAnimator) {
        int offsetInPx = context.getResources().getDimensionPixelSize(offsetResId);
        this.hintCaseView.setShape(offsetInPx, showShapeAnimator, hideShapeAnimator);
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
