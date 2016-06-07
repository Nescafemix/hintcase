package com.joanfuentes.hintcaseassets.shapeanimators;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.view.View;

import com.joanfuentes.hintcase.ShapeAnimator;
import com.joanfuentes.hintcase.RectangularShape;
import com.joanfuentes.hintcase.Shape;

public class UnrevealRectangularShapeAnimator extends ShapeAnimator {
    private FloatEvaluator floatEvaluator;

    public UnrevealRectangularShapeAnimator() {
        super();
        init();
    }

    public UnrevealRectangularShapeAnimator(int durationInMilliseconds) {
        super(durationInMilliseconds);
        init();
    }

    private void init() {
        floatEvaluator = new FloatEvaluator();
    }

    @Override
    public ValueAnimator getAnimator(final View view, Shape shape,
                                     final OnFinishListener onFinishListener) {
        final RectangularShape rectangularShape = (RectangularShape) shape;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(rectangularShape.getMinHeight(),
                rectangularShape.getMaxHeight());
        valueAnimator.setStartDelay(startDelayInMilliseconds);
        valueAnimator.setDuration(durationInMilliseconds)
                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        rectangularShape.setCurrentHeight((Float) valueAnimator.getAnimatedValue());
                        float fraction = valueAnimator.getAnimatedFraction();
                        rectangularShape.setCurrentWidth(
                                floatEvaluator.evaluate(fraction, rectangularShape.getMinWidth(),
                                        rectangularShape.getMaxWidth()));
                        if (rectangularShape.getCurrentHeight() == rectangularShape.getMaxHeight()) {
                            if (onFinishListener != null) {
                                onFinishListener.onFinish();
                            }
                        }
                        view.invalidate();
                    }
                });
        return valueAnimator;
    }
}
