package com.joanfuentes.hintcaseexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.joanfuentes.hintcase.HintCase;
import com.joanfuentes.hintcaseassets.contentholderanimators.FadeInContentHolderAnimator;
import com.joanfuentes.hintcaseassets.hintcontentholders.SimpleHintContentHolder;
import com.joanfuentes.hintcaseassets.shapeanimators.RevealCircleShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.RevealRectangularShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.UnrevealCircleShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.UnrevealRectangularShapeAnimator;
import com.joanfuentes.hintcaseassets.shapes.CircularShape;

public class TargetHintActivity extends AppCompatActivity {

    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_hint);
        configureToolbar();
        setViews();
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Hintcase - Target View");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_target_hint, menu);
        launchAutomaticHint();
        return true;
    }

    private void launchAutomaticHint() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                View actionSearchView = findViewById(R.id.action_search);
                if (actionSearchView != null) {
                    SimpleHintContentHolder blockInfo =
                            new SimpleHintContentHolder.Builder(actionSearchView.getContext())
                                    .setContentTitle("Search")
                                    .setContentText("This is an automatic example of a hint over a toolbar item")
                                    .setTitleStyle(R.style.title)
                                    .setContentStyle(R.style.content)
                                    .setMarginByResourcesId(R.dimen.activity_vertical_margin,
                                            R.dimen.activity_horizontal_margin,
                                            R.dimen.activity_vertical_margin,
                                            R.dimen.activity_horizontal_margin)
                                    .build();
                    new HintCase(actionSearchView.getRootView())
                            .setTarget(actionSearchView, new CircularShape())
                            .setShapeAnimators(new RevealCircleShapeAnimator(),
                                    new UnrevealCircleShapeAnimator())
                            .setHintBlock(blockInfo)
                            .show();
                }
            }
        }, 500);
    }

    private void setViews() {
        Button buttonExample1 = (Button) findViewById(R.id.button_example_1);
        if(buttonExample1 != null) {
            buttonExample1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                            .setContentTitle("Attention!")
                            .setContentText("This is a hint related with a button.")
                            .setTitleStyle(R.style.title)
                            .setContentStyle(R.style.content)
                            .build();
                    new HintCase(view.getRootView())
                            .setTarget(findViewById(R.id.button),HintCase.TARGET_IS_NOT_CLICKABLE)
                            .setBackgroundColorByResourceId(R.color.colorPrimary)
                            .setShapeAnimators(new RevealRectangularShapeAnimator(), new UnrevealRectangularShapeAnimator())
                            .setHintBlock(blockInfo, new FadeInContentHolderAnimator())
                            .show();
                }
            });
        }

        Button buttonExample2 = (Button) findViewById(R.id.button_example_2);
        if(buttonExample2 != null) {
            buttonExample2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                            .setContentTitle("Attention!")
                            .setContentText("This is a hint related with a text.. Please, be careful")
                            .setTitleStyle(R.style.title)
                            .setContentStyle(R.style.content)
                            .build();
                    new HintCase(view.getRootView())
                            .setTarget(findViewById(R.id.textView), new CircularShape(), HintCase.TARGET_IS_NOT_CLICKABLE)
                            .setBackgroundColorByResourceId(R.color.colorPrimary)
                            .setShapeAnimators(new RevealCircleShapeAnimator(), new UnrevealCircleShapeAnimator())
                            .setHintBlock(blockInfo, new FadeInContentHolderAnimator())
                            .show();
                }
            });
        }

        Switch switchButton = (Switch) findViewById(R.id.switch_button);
        if (switchButton != null) {
            switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Snackbar.make(getActivity().getWindow().getDecorView(), "Switch was changed", Snackbar.LENGTH_SHORT).show();
                }
            });
        }

        Button buttonExample3 = (Button) findViewById(R.id.button_example_3);
        if(buttonExample3 != null) {
            buttonExample3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                            .setContentTitle("Activate your powers!")
                            .setContentText("you have the full control over your power. On to be a Hero, Off to be a looser.")
                            .setTitleStyle(R.style.title_light)
                            .setContentStyle(R.style.content_light)
                            .setGravity(Gravity.CENTER)
                            .setMarginByResourcesId(R.dimen.activity_vertical_margin,
                                    R.dimen.activity_horizontal_margin,
                                    R.dimen.activity_vertical_margin,
                                    R.dimen.activity_horizontal_margin)
                            .build();
                    new HintCase(view.getRootView())
                            .setTarget(findViewById(R.id.switch_button), HintCase.TARGET_IS_CLICKABLE)
                            .setBackgroundColorByResourceId(android.R.color.holo_blue_dark)
                            .setShapeAnimators(new RevealRectangularShapeAnimator(),
                                    new UnrevealRectangularShapeAnimator())
                            .setHintBlock(blockInfo, new FadeInContentHolderAnimator())
                            .show();
                }
            });
        }

        Button buttonExample4 = (Button) findViewById(R.id.button_example_4);
        if(buttonExample4 != null) {
            buttonExample4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                            .setContentTitle("Attention!")
                            .setContentText("This is a hint related with a radio button.")
                            .setTitleStyle(R.style.title)
                            .setContentStyle(R.style.content)
                            .setGravity(Gravity.CENTER)
                            .setMarginByResourcesId(R.dimen.activity_vertical_margin,
                                    R.dimen.activity_horizontal_margin,
                                    R.dimen.activity_vertical_margin,
                                    R.dimen.activity_horizontal_margin)
                            .build();
                    new HintCase(view.getRootView())
                            .setTarget(findViewById(R.id.radio_button), HintCase.TARGET_IS_CLICKABLE)
                            .setBackgroundColor(0xCC000000)
                            .setShapeAnimators(new RevealRectangularShapeAnimator(), new UnrevealRectangularShapeAnimator())
                            .setHintBlock(blockInfo, new FadeInContentHolderAnimator())
                            .show();
                }
            });
        }

        Button buttonExample5 = (Button) findViewById(R.id.button_example_5);
        if(buttonExample5 != null) {
            buttonExample5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                            .setContentTitle("FAB button power!")
                            .setContentText("The FAB button is gonna help you with the main action of every screen.")
                            .setTitleStyle(R.style.title)
                            .setContentStyle(R.style.content)
                            .build();
                    new HintCase(view.getRootView())
                            .setTarget(findViewById(R.id.fab), new CircularShape())
                            .setShapeAnimators(new RevealCircleShapeAnimator(),
                                    new UnrevealCircleShapeAnimator())
                            .setHintBlock(blockInfo)
                            .show();
                }
            });
        }

        Button buttonExample6 = (Button) findViewById(R.id.button_example_6);
        if(buttonExample6 != null) {
            buttonExample6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View actionCameraView = findViewById(R.id.action_camera);
                    if (actionCameraView != null) {
                        actionCameraView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Snackbar.make(getActivity().getWindow().getDecorView(), "Camera was clicked", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                        SimpleHintContentHolder blockInfo =
                                new SimpleHintContentHolder.Builder(actionCameraView.getContext())
                                        .setContentTitle("Camera icon!")
                                        .setContentText("This is an example of a hint over a toolbar item")
                                        .setTitleStyle(R.style.title)
                                        .setContentStyle(R.style.content)
                                        .setMarginByResourcesId(R.dimen.activity_vertical_margin,
                                                R.dimen.activity_horizontal_margin,
                                                R.dimen.activity_vertical_margin,
                                                R.dimen.activity_horizontal_margin)
                                        .build();
                        new HintCase(actionCameraView.getRootView())
                                .setTarget(actionCameraView, new CircularShape(), HintCase.TARGET_IS_CLICKABLE)
                                .setShapeAnimators(new RevealCircleShapeAnimator(),
                                        new UnrevealCircleShapeAnimator())
                                .setHintBlock(blockInfo)
                                .show();
                    }
                }
            });
        }
    }
}
