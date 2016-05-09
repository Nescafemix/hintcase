package com.joanfuentes.hintcase;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.joanfuentes.hintcase.utils.DimenUtils;

import java.util.ArrayList;
import java.util.List;

class HintCaseView extends RelativeLayout {
    private static final int  DEFAULT_BACKGROUND_COLOR = 0xCC000000;
    private static final int DEFAULT_HINT_BLOCK_POSITION = HintCase.HINT_BLOCK_POSITION_BOTTOM;
    private static final Shape DEFAULT_SHAPE = new RectangularShape();
    private static final ContentHolder NO_BLOCK_INFO = null;
    private static final View NO_BLOCK_INFO_VIEW = null;
    private static final View NO_TARGETVIEW = null;

    private View targetView;
    private Bitmap bitmap;
    private Shape shape = DEFAULT_SHAPE;
    private ContentHolder hintBlock;
    private Paint basePaint;
    private View hintBlockView;
    private ViewGroup parent;
    private int parentIndex;
    private int backgroundColor;
    private int offset;
    private int hintBlockPosition;
    private HintCase.OnClosedListener onClosedListener;
    private ShapeAnimator showShapeAnimator;
    private ShapeAnimator hideShapeAnimator;
    private ContentHolderAnimator showContentHolderAnimator;
    private ContentHolderAnimator hideContentHolderAnimator;
    private List<ContentHolder> extraBlocks;
    private List<View> extraBlockViews;
    private List<ContentHolderAnimator> showExtraContentHolderAnimators;
    private List<ContentHolderAnimator> hideExtraContentHolderAnimators;
    private boolean closeOnTouch;
    private HintCase hintCase;
    private boolean isTargetClickable;
    private Point navigationBarSizeIfExistAtTheBottom;
    private Point navigationBarSizeIfExistOnTheRight;

    public View getHintBlockView() {
        return hintBlockView;
    }

    public HintCaseView(Context context, HintCase hintCase) {
        super(context);
        init(hintCase);
    }

    private void init(HintCase hintCase) {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        this.hintCase = hintCase;
        closeOnTouch = true;
        showExtraContentHolderAnimators = new ArrayList<>();
        hideExtraContentHolderAnimators = new ArrayList<>();
        hintBlock = NO_BLOCK_INFO;
        hintBlockView = NO_BLOCK_INFO_VIEW;
        extraBlocks = new ArrayList<>();
        extraBlockViews = new ArrayList<>();
        backgroundColor = DEFAULT_BACKGROUND_COLOR;
        offset = DimenUtils.dipToPixels(getContext(), HintCase.DEFAULT_SHAPE_OFFSET_IN_DP);
        hintBlockPosition = DEFAULT_HINT_BLOCK_POSITION;
        basePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        navigationBarSizeIfExistAtTheBottom = DimenUtils.getNavigationBarSizeIfExistAtTheBottom(getContext());
        navigationBarSizeIfExistOnTheRight = DimenUtils.getNavigationBarSizeIfExistOnTheRight(getContext());
    }

    private void buildBaseBitmap() {
        if (bitmap != null) {
            bitmap.recycle();
        }
        if (parent.getMeasuredWidth() > 0 && parent.getMeasuredHeight() > 0) {
            bitmap = Bitmap.createBitmap(parent.getMeasuredWidth(),
                    parent.getMeasuredHeight(),
                    Bitmap.Config.ARGB_8888);
        }
    }

    private void performShow() {
        parent.addView(this, parentIndex);
        if (showShapeAnimator != ShapeAnimator.NO_ANIMATOR) {
            ValueAnimator animator = showShapeAnimator.getAnimator(this, shape, new ShapeAnimator.OnFinishListener() {
                @Override
                public void onFinish() {
                    performShowBlocks();
                }
            });
            animator.start();
        } else {
            shape.setMinimumValue();
            performShowBlocks();
        }
    }

    private void performShowBlocks() {
        List<Animator> animators = new ArrayList<>();
        if (showContentHolderAnimator != ContentHolderAnimator.NO_ANIMATOR) {
            animators.add(showContentHolderAnimator.getAnimator(hintBlockView));
        }
        if (!showExtraContentHolderAnimators.isEmpty()) {
            for (int i = 0; i < extraBlocks.size(); i++) {
                ContentHolderAnimator animator = showExtraContentHolderAnimators.get(i);
                if (animator != ContentHolderAnimator.NO_ANIMATOR) {
                    animators.add(animator.getAnimator(extraBlockViews.get(i)));
                }
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();
        if (animators.isEmpty()) {
            if (existHintBlock()) {
                getHintBlockView().setAlpha(1);
            }
            for (View view: extraBlockViews) {
                view.setAlpha(1);
            }
        } else {
            animatorSet.playTogether(animators);
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) { }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (existHintBlock()
                            && showContentHolderAnimator == ContentHolderAnimator.NO_ANIMATOR) {
                        getHintBlockView().setAlpha(1);
                    }
                    for (int i = 0; i < showExtraContentHolderAnimators.size(); i++) {
                        ContentHolderAnimator animator = showExtraContentHolderAnimators.get(i);
                        if (animator == ContentHolderAnimator.NO_ANIMATOR) {
                            extraBlockViews.get(i).setAlpha(1);
                        }
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) { }

                @Override
                public void onAnimationRepeat(Animator animation) { }
            });
            animatorSet.start();
        }
    }

    void performHide() {
        List<Animator> animators = new ArrayList<>();
        if (hideContentHolderAnimator != ContentHolderAnimator.NO_ANIMATOR) {
            animators.add(hideContentHolderAnimator.getAnimator(hintBlockView));
        } else {
            if (existHintBlock()) {
                getHintBlockView().setAlpha(0);
            }
        }
        if (!hideExtraContentHolderAnimators.isEmpty()) {
            for (int i = 0; i < extraBlocks.size(); i++) {
                ContentHolderAnimator animator = hideExtraContentHolderAnimators.get(i);
                if (animator != ContentHolderAnimator.NO_ANIMATOR) {
                    animators.add(animator.getAnimator(extraBlockViews.get(i)));
                }
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();
        if (animators.isEmpty()) {
            for (View view: extraBlockViews) {
                view.setAlpha(0);
            }
            performHideShape();
        } else {
            animatorSet.playTogether(animators);
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) { }

                @Override
                public void onAnimationEnd(Animator animation) {
                   performHideShape();
                }

                @Override
                public void onAnimationCancel(Animator animation) { }

                @Override
                public void onAnimationRepeat(Animator animation) { }
            });
            animatorSet.start();
        }
    }

    private void performHideShape() {
        List<Animator> animators = new ArrayList<>();
        if (hideShapeAnimator != ShapeAnimator.NO_ANIMATOR) {
            animators.add(hideShapeAnimator.getAnimator(this, shape));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        if (animators.isEmpty()) {
            close();
        } else {
            animatorSet.playSequentially(animators);
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) { }

                @Override
                public void onAnimationEnd(Animator animation) {
                    close();
                }

                @Override
                public void onAnimationCancel(Animator animation) { }

                @Override
                public void onAnimationRepeat(Animator animation) { }
            });
            animatorSet.start();
        }
    }

    private void close() {
        removeView();
        clearData();
        if (onClosedListener != null) {
            onClosedListener.onClosed();
        }
    }

    private void clearData() {
        if (bitmap != null) {
            bitmap.recycle();
        }
        bitmap = null;
        parent = null;
        hintCase = null;
    }

    private void removeView() {
        if (parent != null) {
            parent.removeView(this);
        }
    }

    private void setViews() {
        if (existHintBlock()) {
            FrameLayout frameLayout = getHintBlockFrameLayout();
            if (hintBlockView == NO_BLOCK_INFO_VIEW) {
                hintBlockView = hintBlock.getView(getContext(), hintCase, frameLayout);
                hintBlockView.setAlpha(0);
            }
            frameLayout.addView(hintBlockView);
            addView(frameLayout);
        }
        if (existExtraBlock()) {
            RelativeLayout relativeLayout = getExtraContentHolderRelativeLayout();
            for (int i = 0; i < extraBlocks.size(); i++) {
                View view = extraBlocks.get(i).getView(getContext(), hintCase, this);
                if (showExtraContentHolderAnimators.get(i) != ContentHolderAnimator.NO_ANIMATOR) {
                    view.setAlpha(0);
                }
                extraBlockViews.add(view);
                relativeLayout.addView(view);
            }
            addView(relativeLayout);
        }
    }

    private boolean existHintBlock() {
        return hintBlock != NO_BLOCK_INFO;
    }

    private boolean existExtraBlock() {
        return !extraBlocks.isEmpty();
    }

    @NonNull
    private FrameLayout getHintBlockFrameLayout() {
        int blockWidth = 0;
        int blockHeight = 0;
        int blockAlign = 0;
        switch (hintBlockPosition) {
            case HintCase.HINT_BLOCK_POSITION_TOP:
                blockWidth = parent.getWidth();
                blockHeight = shape.getTop()
                        - parent.getTop()
                        - DimenUtils.getStatusBarHeight(getContext());
                blockAlign = RelativeLayout.ALIGN_PARENT_TOP;
                break;
            case HintCase.HINT_BLOCK_POSITION_BOTTOM:
                blockWidth = parent.getWidth();
                blockHeight = parent.getBottom()
                        - navigationBarSizeIfExistAtTheBottom.y
                        - shape.getBottom();
                blockAlign = RelativeLayout.ALIGN_PARENT_BOTTOM;
                break;
            case HintCase.HINT_BLOCK_POSITION_LEFT:
                blockWidth = shape.getLeft() - parent.getLeft();
                blockHeight = parent.getHeight() - DimenUtils.getStatusBarHeight(getContext());
                blockAlign = RelativeLayout.ALIGN_PARENT_LEFT;
                break;
            case HintCase.HINT_BLOCK_POSITION_RIGHT:
                blockWidth = parent.getRight()
                        - navigationBarSizeIfExistOnTheRight.x
                        - shape.getRight();
                blockHeight = parent.getHeight() - DimenUtils.getStatusBarHeight(getContext());
                blockAlign = RelativeLayout.ALIGN_PARENT_RIGHT;
                break;
            case HintCase.HINT_BLOCK_POSITION_CENTER:
                blockWidth = parent.getWidth() - navigationBarSizeIfExistOnTheRight.x;
                blockHeight = parent.getHeight()
                        - navigationBarSizeIfExistAtTheBottom.y
                        - DimenUtils.getStatusBarHeight(getContext());
                blockAlign = RelativeLayout.ALIGN_PARENT_BOTTOM;
                break;

        }
        LayoutParams relativeLayoutParams =
                new LayoutParams(blockWidth, blockHeight);
        relativeLayoutParams.addRule(blockAlign);
        relativeLayoutParams.topMargin = DimenUtils.getStatusBarHeight(getContext());
        relativeLayoutParams.bottomMargin = navigationBarSizeIfExistAtTheBottom.y;
        relativeLayoutParams.rightMargin = navigationBarSizeIfExistOnTheRight.x;
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(relativeLayoutParams);
        return frameLayout;
    }

    @NonNull
    private RelativeLayout getExtraContentHolderRelativeLayout() {
        LayoutParams relativeLayoutParams =
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayoutParams.topMargin = DimenUtils.getStatusBarHeight(getContext());
        relativeLayoutParams.bottomMargin = navigationBarSizeIfExistAtTheBottom.y;
        relativeLayoutParams.rightMargin = navigationBarSizeIfExistOnTheRight.x;
        relativeLayout.setLayoutParams(relativeLayoutParams);
        return relativeLayout;
    }

    private void calculateHintBlockPosition(ViewGroup parent, Shape shape) {
        if (targetView == NO_TARGETVIEW) {
            hintBlockPosition = HintCase.HINT_BLOCK_POSITION_CENTER;
        } else {
            int[] areas = new int[4];
            areas[HintCase.HINT_BLOCK_POSITION_LEFT] = shape.getLeft() - parent.getLeft();
            areas[HintCase.HINT_BLOCK_POSITION_TOP] = shape.getTop() - parent.getTop();
            areas[HintCase.HINT_BLOCK_POSITION_RIGHT] = parent.getRight() - shape.getRight();
            areas[HintCase.HINT_BLOCK_POSITION_BOTTOM] = parent.getBottom() - shape.getBottom();
            hintBlockPosition = HintCase.HINT_BLOCK_POSITION_LEFT;
            for(int i = 1; i < areas.length; i++) {
                if(areas[i] >= areas[hintBlockPosition]) {
                    hintBlockPosition = i;
                }
            }
        }
    }

    public void setOnClosedListener(HintCase.OnClosedListener onClickListener) {
        this.onClosedListener = onClickListener;
    }

    public void setTargetInfo(View view, Shape shape, boolean isTargetClickable) {
        this.targetView = view;
        this.shape = shape;
        this.isTargetClickable = isTargetClickable;
    }

    public void setOverDecorView(@NonNull View decorView) {
        parent = ((ViewGroup) decorView);
        parentIndex = -1;
    }

    public void setParentView(View parentView) {
        parent = (ViewGroup) parentView;
        parentIndex = parent.getChildCount();
    }

    public void setCloseOnTouch(boolean closeOnTouch) {
        this.closeOnTouch = closeOnTouch;
    }

    public void show() {
        initializeView();
        performShow();
    }

    public void initializeView() {
        shape.setShapeInfo(targetView, parent, offset, getContext());
        calculateHintBlockPosition(parent, shape);
        setViews();
        buildBaseBitmap();
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(int offset, ShapeAnimator showShapeAnimator,
                         ShapeAnimator hideShapeAnimator) {
        this.offset = offset;
        this.showShapeAnimator = showShapeAnimator;
        this.hideShapeAnimator = hideShapeAnimator;
    }

    public void setHintBlock(ContentHolder contentHolder, ContentHolderAnimator showContentHolderAnimator,
                             ContentHolderAnimator hideContentHolderAnimator) {
        this.hintBlock = contentHolder;
        this.showContentHolderAnimator = showContentHolderAnimator;
        this.hideContentHolderAnimator = hideContentHolderAnimator;
    }

    public int getHintBlockPosition() {
        return hintBlockPosition;
    }

    public void setExtraBlock(ContentHolder contentHolder, ContentHolderAnimator showContentHolderAnimator,
                              ContentHolderAnimator hideContentHolderAnimator) {
        if (contentHolder != null) {
            this.extraBlocks.add(contentHolder);
            this.showExtraContentHolderAnimators.add(showContentHolderAnimator);
            this.hideExtraContentHolderAnimators.add(hideContentHolderAnimator);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (hintBlock != NO_BLOCK_INFO) {
            hintBlock.onLayout();
        }
        for (ContentHolder extraBlock: extraBlocks) {
            extraBlock.onLayout();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean consumeTouchEvent = true;
        if (closeOnTouch) {
            if (MotionEvent.ACTION_UP == event.getAction()) {
                performHide();
            }
        }
        if (targetView != null && isTargetClickable && shape.isTouchEventInsideTheHint(event)) {
            targetView.dispatchTouchEvent(event);
        }
        return consumeTouchEvent;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (bitmap == null) {
            super.dispatchDraw(canvas);
        } else {
            bitmap.eraseColor(backgroundColor);
            if (shape != null
                    && (showShapeAnimator != ShapeAnimator.NO_ANIMATOR
                    || hideShapeAnimator != ShapeAnimator.NO_ANIMATOR)) {
                Canvas canvasShape = new Canvas(bitmap);
                shape.draw(canvasShape);
            }
            canvas.drawBitmap(bitmap, 0, 0, basePaint);
            super.dispatchDraw(canvas);
        }
    }

    @Override
    public void setBackgroundColor(int color) {
        backgroundColor = color;
    }
}