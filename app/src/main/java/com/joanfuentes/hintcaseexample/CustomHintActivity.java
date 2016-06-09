package com.joanfuentes.hintcaseexample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.joanfuentes.hintcase.HintCase;
import com.joanfuentes.hintcaseassets.contentholderanimators.FadeInContentHolderAnimator;
import com.joanfuentes.hintcaseassets.contentholderanimators.FadeOutContentHolderAnimator;
import com.joanfuentes.hintcaseassets.contentholderanimators.SlideInFromRightContentHolderAnimator;
import com.joanfuentes.hintcaseassets.contentholderanimators.SlideOutFromRightContentHolderAnimator;
import com.joanfuentes.hintcaseassets.extracontentholders.SimpleButtonContentHolder;
import com.joanfuentes.hintcaseexample.customBlock.CustomHintContentHolder;

public class CustomHintActivity extends AppCompatActivity {

    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_hint);
        configureToolbar();
        setViews();
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Hintcase - Custom hints");
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
        Button buttonUp = (Button) findViewById(R.id.button_up);
        if(buttonUp != null) {
            buttonUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showHint(view);
                }
            });
        }

        Button buttonDown = (Button) findViewById(R.id.button_down);
        if(buttonDown != null) {
            buttonDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showHint(view);
                }
            });
        }

        Button buttonRight = (Button) findViewById(R.id.button_right);
        if(buttonRight != null) {
            buttonRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showHint(view);
                }
            });
        }

        Button buttonLeft = (Button) findViewById(R.id.button_left);
        if(buttonLeft != null) {
            buttonLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showHint(view);
                }
            });
        }
    }

    private void showHint(final View view) {
        SimpleButtonContentHolder okBlock = new SimpleButtonContentHolder.Builder(view.getContext())
                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setRules(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.ALIGN_PARENT_RIGHT)
                .setButtonText("OK")
                .setCloseHintCaseOnClick(true)
                .setButtonStyle(R.style.buttonNice)
                .build();
        CustomHintContentHolder blockInfo = new CustomHintContentHolder.Builder(view.getContext())
                .setContentTitle("Custom Hint Content Holder!")
                .setContentText("This hint was done with a custom hint content holder and it only can be closed clicking over the blue OK button")
                .setBorder(R.dimen.bubble_border, android.R.color.holo_blue_dark)
                .setArrowSize(R.dimen.arrow_width, R.dimen.arrow_height)
                .setBackgroundColor(Color.WHITE)
                .setTitleStyle(R.style.title)
                .setMargingByResourcesId(R.dimen.activity_vertical_margin,
                        R.dimen.activity_horizontal_margin,
                        R.dimen.activity_vertical_margin,
                        R.dimen.activity_horizontal_margin)
                .setContentPaddingByResourcesId(R.dimen.small_margin,
                        R.dimen.small_margin,
                        R.dimen.small_margin,
                        R.dimen.small_margin)
                .setContentStyle(R.style.content_dark)
                .build();
        new HintCase(getActivity().getWindow().getDecorView())
                .setTarget(view, R.dimen.zero_margin)
                .setBackgroundColor(0x00000000)
                .setHintBlock(blockInfo, new FadeInContentHolderAnimator(),
                        new FadeOutContentHolderAnimator())
                .setExtraBlock(okBlock, new SlideInFromRightContentHolderAnimator(),
                        new SlideOutFromRightContentHolderAnimator())
                .setCloseOnTouchView(false)
                .show();
    }
}
