package com.joanfuentes.hintcaseassets.shapeanimators;

import android.animation.ValueAnimator;
import android.view.View;

import com.joanfuentes.hintcase.ShapeAnimator;
import com.joanfuentes.hintcaseassets.shapes.CircularShape;
import com.joanfuentes.hintcase.Shape;

public class UnrevealCircleShapeAnimator extends ShapeAnimator {

    public UnrevealCircleShapeAnimator() {
        super();
    }

    public UnrevealCircleShapeAnimator(int durationInMilliseconds) {
        super(durationInMilliseconds);
    }

    @Override
    public ValueAnimator getAnimator(final View view, Shape shape,
                                       final OnFinishListener onFinishListener) {
        final CircularShape circularShape = (CircularShape) shape;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(circularShape.getMinRadius(),
                circularShape.getMaxRadius());
        valueAnimator.setDuration(durationInMilliseconds)
                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        circularShape.setCurrentRadius((Float) valueAnimator.getAnimatedValue());
                        if (circularShape.getCurrentRadius() == circularShape.getMaxRadius()) {
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
