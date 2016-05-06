package com.joanfuentes.hintcaseexample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.joanfuentes.hintcase.ShapeAnimator;
import com.joanfuentes.hintcaseassets.contentholderanimators.FadeInContentHolderAnimator;
import com.joanfuentes.hintcaseassets.contentholderanimators.FadeOutContentHolderAnimator;
import com.joanfuentes.hintcaseassets.contentholderanimators.SlideInFromRightContentHolderAnimator;
import com.joanfuentes.hintcaseassets.contentholderanimators.SlideOutFromRightContentHolderAnimator;
import com.joanfuentes.hintcaseassets.extracontentholders.SimpleButtonContentHolder;
import com.joanfuentes.hintcaseassets.hintcontentholders.SimpleHintContentHolder;
import com.joanfuentes.hintcaseassets.shapeanimators.FadeInShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.FadeOutShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.RevealCircleShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.RevealRectangularShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.UnrevealCircleShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.UnrevealRectangularShapeAnimator;
import com.joanfuentes.hintcase.HintCase;
import com.joanfuentes.hintcaseassets.shapes.CircularShape;
import com.joanfuentes.hintcaseexample.customBlock.CustomHintContentHolder;

public class MainActivity extends AppCompatActivity {

    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        (findViewById(R.id.buttonbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                SimpleButtonContentHolder okBlock = new SimpleButtonContentHolder.Builder(view.getContext())
                        .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                        .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                        .setRules(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.ALIGN_PARENT_RIGHT)
                        .setButtonText("OK")
                        .setCloseHintCaseOnClick(true)
                        .setButtonStyle(R.style.buttonNice)
                        .build();
//                SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
//                        .setContentTitle("Be happy!")
//                        .setContentText("Do you know that you can be more happy switching between ON/OFF?")
//                        .setTitleStyle(R.style.title)
//                        .setContentStyle(R.style.content)
//                        .setImageDrawableId(R.drawable.test_gif)
//                        .build();
                CustomHintContentHolder blockInfo = new CustomHintContentHolder.Builder(view.getContext())
                        .setContentTitle("Be happy!")
                        .setContentText("Do you know that you can be more happy switching between ON/OFF?")
                        .setBorder(R.dimen.bubble_border, android.R.color.holo_blue_dark)
                        .setArrowSize(R.dimen.arrow_width, R.dimen.arrow_height)
                        .setBackgroundColor(Color.WHITE)
                        .setTitleStyle(R.style.title)
                        .setContentStyle(R.style.content)
//                        .setImageDrawableId(R.drawable.test_gif)
                        .setMargin(48, 0, 48, 0)
                        .build();
                new HintCase(getActivity().getWindow().getDecorView())
                        .setTarget(findViewById(R.id.buttonToShow))
                        .setBackgroundColor(0x00000000)
//                        .setShapeAnimators(new RectangularShape())
                        .setHintBlock(blockInfo, new FadeInContentHolderAnimator(), new FadeOutContentHolderAnimator())
                        .setExtraBlock(okBlock, new SlideInFromRightContentHolderAnimator(), new SlideOutFromRightContentHolderAnimator())
                        .setCloseOnTouchView(false)
                        .setOnClosedListener(new HintCase.OnClosedListener() {
                            @Override
                            public void onClosed() {
                                Snackbar.make(view, "TEST", Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        (findViewById(R.id.buttonradio)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Load an image and pass to the HintCaseView
                ImageView imageView = new ImageView(view.getContext());
                imageView.setImageResource(R.drawable.test_gif);

                SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                        .setContentTitle("Be happy!")
                        .setContentText("Do you know that you can be more happy switching between ON/OFF?")
                        .setImageView(imageView)
                        .setTitleStyle(R.style.title)
                        .setContentStyle(R.style.content)
                        .setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP)
                        .setMargin(50, 40, 50, 0)

                        .build();
                new HintCase(view.getRootView())
                        .setTarget(findViewById(R.id.radioButton))
                        .setBackgroundColor(0xCC000000)
                        .setShapeAnimators(new RevealRectangularShapeAnimator(), new UnrevealRectangularShapeAnimator())
                        .setHintBlock(blockInfo, new FadeInContentHolderAnimator())
                        .show();
            }
        });

        (findViewById(R.id.buttonswitch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                        .setContentTitle("Activate your powers!")
                        .setContentText("you have the full control over your power. On to be a Hero, Off to be a looser.")
                        .setTitleStyle(R.style.title_light)
                        .setContentStyle(R.style.content_light)
                        .setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP)
                        .setMargin(50, 40, 50, 0)
                        .build();
                new HintCase(view.getRootView())
                        .setTarget(findViewById(R.id.switch1))
                        .setBackgroundColor(view.getContext().getResources().getColor(android.R.color.holo_blue_dark))
                        .setShapeAnimators(new RevealRectangularShapeAnimator(), new UnrevealRectangularShapeAnimator())
                        .setHintBlock(blockInfo, new FadeInContentHolderAnimator())
                        .show();
            }
        });

        (findViewById(R.id.buttonfab)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                        .setContentTitle("Be happy!")
                        .setContentText("Do you know that you can be more happy switching between ON/OFF?")
                        .setTitleStyle(R.style.title)
                        .setContentStyle(R.style.content)
                        .build();
                new HintCase(view.getRootView())
                        .setTarget(findViewById(R.id.fab), new CircularShape())
                        .setShapeAnimators(new FadeInShapeAnimator(), new FadeOutShapeAnimator())
                        .setHintBlock(blockInfo)
                        .show();
            }
        });

        (findViewById(R.id.buttoniconapp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Load an image with Glide and pass to the HintCaseView
                ImageView animatedImageView = new ImageView(getActivityContext());
//                animatedImageView.setMaxHeight(900);
                Glide.with(getActivityContext())
                        .load(R.drawable.test_gif)
                        .into(animatedImageView);

                SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                        .setContentTitle("The truth behind the veil")
                        .setContentText("True story")
                        .setImageView(animatedImageView)
                        .setTitleStyle(R.style.title)
                        .setContentStyle(R.style.content)
                        .build();
                new HintCase(view.getRootView())
                        .setBackgroundColor(getResources().getColor(R.color.colorPrimary))
//                        .setShapeAnimators(new CircularShape(), new RevealCircleShapeAnimator(),
//                                new UnrevealCircleShapeAnimator())
                        .setShapeAnimators(new FadeInShapeAnimator(), new FadeOutShapeAnimator())
                        .setHintBlock(blockInfo)
                        .show();
            }
        });

        (findViewById(R.id.buttontitle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                        .setContentTitle("Do you want Pills!")
                        .setContentText("Drugs are your best friends when you can sleep, even if you can't XD")
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
                                SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                                        .setContentTitle("More Pills!")
                                        .setContentText("Yipiyaiyi")
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
                        })
                        .show();
            }
        });

    }

    public Activity getActivityContext() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        new Handler().post(new Runnable() {
            @Override
            public void run() {
                View view = findViewById(R.id.action_search);
                if (view != null) {
                    final View viewIcon = view;
                    (findViewById(R.id.buttonicontoolbar)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SimpleHintContentHolder blockInfo = new SimpleHintContentHolder.Builder(view.getContext())
                                    .setContentTitle("Search")
                                    .setContentText("are you lost in your tasks, conversations, chats, dogs, cats and ForLaters?. Use the Search to make your life easier")
                                    .setTitleStyle(R.style.title)
                                    .setContentStyle(R.style.content)
                                    .build();
                            new HintCase(view.getRootView())
                                    .setTarget(viewIcon, new CircularShape())
                                    .setShapeAnimators(new RevealCircleShapeAnimator(), new UnrevealCircleShapeAnimator())
                                    .setHintBlock(blockInfo)
                                    .show();
                        }
                    });
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
