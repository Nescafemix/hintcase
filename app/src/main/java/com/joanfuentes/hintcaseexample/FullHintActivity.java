package com.joanfuentes.hintcaseexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.transcode.BitmapToGlideDrawableTranscoder;
import com.joanfuentes.hintcase.HintCase;
import com.joanfuentes.hintcase.RectangularShape;
import com.joanfuentes.hintcase.ShapeAnimator;
import com.joanfuentes.hintcaseassets.contentholderanimators.FadeInContentHolderAnimator;
import com.joanfuentes.hintcaseassets.contentholderanimators.FadeOutContentHolderAnimator;
import com.joanfuentes.hintcaseassets.contentholderanimators.SlideInFromRightContentHolderAnimator;
import com.joanfuentes.hintcaseassets.contentholderanimators.SlideOutFromRightContentHolderAnimator;
import com.joanfuentes.hintcaseassets.hintcontentholders.SimpleHintContentHolder;
import com.joanfuentes.hintcaseassets.shapeanimators.FadeInShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.FadeOutShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.RevealCircleShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.RevealRectangularShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.UnrevealCircleShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.UnrevealRectangularShapeAnimator;
import com.joanfuentes.hintcaseassets.shapes.CircularShape;

public class FullHintActivity extends AppCompatActivity {
    private static final View  NO_VIEW_AS_TARGET = null;

    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_hint);
        configureToolbar();
        setViews();
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Hintcase - Full Screen");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setViews() {
        Button buttonExample1 = (Button) findViewById(R.id.button_example_1);
        if(buttonExample1 != null) {
            buttonExample1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView animatedImageView = getGifLoadedUsingGlide();
                    SimpleHintContentHolder blockInfo =
                            getSimpleHintContentHolder(view, animatedImageView);
                    new HintCase(view.getRootView())
                            .setBackgroundColor(getResources().getColor(R.color.colorPrimary))
                            .setShapeAnimators(new FadeInShapeAnimator(), new FadeOutShapeAnimator())
                            .setHintBlock(blockInfo)
                            .show();
                }
            });
        }

        Button buttonExample2 = (Button) findViewById(R.id.button_example_2);
        if(buttonExample2 != null) {
            buttonExample2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView animatedImageView = getGifLoadedUsingGlide();
                    SimpleHintContentHolder blockInfo =
                            getSimpleHintContentHolder(view, animatedImageView);
                    new HintCase(view.getRootView())
                            .setBackgroundColor(getResources().getColor(R.color.colorPrimary))
                            .setTarget(NO_VIEW_AS_TARGET, new CircularShape())
                            .setShapeAnimators(new RevealCircleShapeAnimator(),
                                    new UnrevealCircleShapeAnimator())
                            .setHintBlock(blockInfo)
                            .show();

                }
            });
        }

        Button buttonExample3 = (Button) findViewById(R.id.button_example_3);
        if(buttonExample3 != null) {
            buttonExample3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView animatedImageView = getGifLoadedUsingGlide();
                    SimpleHintContentHolder blockInfo =
                            getSimpleHintContentHolder(view, animatedImageView);
                    new HintCase(view.getRootView())
                            .setBackgroundColor(getResources().getColor(R.color.colorPrimary))
                            .setTarget(NO_VIEW_AS_TARGET, new RectangularShape())
                            .setShapeAnimators(new RevealRectangularShapeAnimator(),
                                    new UnrevealRectangularShapeAnimator())
                            .setHintBlock(blockInfo)
                            .show();
                }
            });
        }

        Button buttonExample4 = (Button) findViewById(R.id.button_example_4);
        if(buttonExample4 != null) {
            buttonExample4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView animatedImageView = getGifLoadedUsingGlide();
                    SimpleHintContentHolder blockInfo =
                            getSimpleHintContentHolder(view, animatedImageView);
                    new HintCase(view.getRootView())
                            .setBackgroundColor(getResources().getColor(R.color.colorPrimary))
                            .setShapeAnimators(new FadeInShapeAnimator(), new FadeOutShapeAnimator())
                            .setHintBlock(blockInfo, new FadeInContentHolderAnimator(),
                                    new FadeOutContentHolderAnimator())
                            .show();
                }
            });
        }

        Button buttonExample5 = (Button) findViewById(R.id.button_example_5);
        if(buttonExample5 != null) {
            buttonExample5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView animatedImageView = getGifLoadedUsingGlide();
                    SimpleHintContentHolder blockInfo =
                            getSimpleHintContentHolder(view, animatedImageView);
                    new HintCase(view.getRootView())
                            .setBackgroundColor(getResources().getColor(R.color.colorPrimary))
                            .setTarget(NO_VIEW_AS_TARGET, new CircularShape())
                            .setShapeAnimators(new RevealCircleShapeAnimator(),
                                    new UnrevealCircleShapeAnimator())
                            .setHintBlock(blockInfo, new FadeInContentHolderAnimator(),
                                    new FadeOutContentHolderAnimator())
                            .show();

                }
            });
        }

        Button buttonExample6 = (Button) findViewById(R.id.button_example_6);
        if(buttonExample6 != null) {
            buttonExample6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView animatedImageView = getGifLoadedUsingGlide();
                    SimpleHintContentHolder blockInfo =
                            getSimpleHintContentHolder(view, animatedImageView);
                    new HintCase(view.getRootView())
                            .setBackgroundColor(getResources().getColor(R.color.colorPrimary))
                            .setTarget(NO_VIEW_AS_TARGET, new CircularShape())
                            .setShapeAnimators(new RevealCircleShapeAnimator(),
                                    new UnrevealCircleShapeAnimator())
                            .setHintBlock(blockInfo, new SlideInFromRightContentHolderAnimator(),
                                    new SlideOutFromRightContentHolderAnimator())
                            .show();

                }
            });
        }

        Button buttonExample7 = (Button) findViewById(R.id.button_example_7);
        if(buttonExample7 != null) {
            buttonExample7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    launchFirstHint(view);
                }
            });
        }
    }

    private void launchFirstHint(final View view) {
        SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                .setContentTitle("Attention!")
                .setContentText("This is your first advice. Please, be careful")
                .setTitleStyle(R.style.title)
                .setContentStyle(R.style.content)
                .build();
        new HintCase(view.getRootView())
                .setTarget(findViewById(R.id.textView), new CircularShape(), HintCase.TARGET_IS_NOT_CLICKABLE)
                .setBackgroundColor(getResources().getColor(R.color.colorPrimary))
                .setShapeAnimators(new RevealCircleShapeAnimator(), ShapeAnimator.NO_ANIMATOR)
                .setHintBlock(blockInfo, new FadeInContentHolderAnimator(), new SlideOutFromRightContentHolderAnimator())
                .setOnClosedListener(new HintCase.OnClosedListener() {
                    @Override
                    public void onClosed() {
                        launchSecondHint(view);
                    }
                })
                .show();
    }

    private void launchSecondHint(View view) {
        SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                .setContentTitle("Attention again!")
                .setContentText("Are you really reading these messages?")
                .setTitleStyle(R.style.title)
                .setContentStyle(R.style.content)
                .build();
        new HintCase(view.getRootView())
                .setTarget(findViewById(R.id.textView), new CircularShape())
                .setBackgroundColor(getResources().getColor(R.color.colorPrimary))
                .setShapeAnimators(ShapeAnimator.NO_ANIMATOR, new UnrevealCircleShapeAnimator())
                .setHintBlock(blockInfo, new SlideInFromRightContentHolderAnimator())
                .show();
    }

    @NonNull
    private ImageView getGifLoadedUsingGlide() {
        ImageView animatedImageView = new ImageView(getActivity());
        animatedImageView.setMaxHeight(900);
        Glide.with(getActivity())
                .load(R.drawable.animated_image)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .thumbnail(Glide.with(getActivity())
                        .load(R.drawable.animated_image)
                        .asBitmap()
                        .transcode(new BitmapToGlideDrawableTranscoder(getActivity()), GlideDrawable.class)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(animatedImageView);
        return animatedImageView;
    }

    private SimpleHintContentHolder getSimpleHintContentHolder(View view, ImageView animatedImageView) {
        return new SimpleHintContentHolder.Builder(view.getContext())
                .setContentTitle("The truth behind the veil")
                .setContentText("True story")
                .setImageView(animatedImageView)
                .setTitleStyle(R.style.title)
                .setContentStyle(R.style.content)
                .setMarginByResourcesId(R.dimen.activity_horizontal_margin,
                        R.dimen.activity_vertical_margin, R.dimen.activity_horizontal_margin,
                        R.dimen.activity_vertical_margin)
                .build();
    }
}
