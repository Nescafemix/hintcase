package com.joanfuentes.hintcaseexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureToolbar();
        setViews();
    }

    private void setViews() {
        Button buttonFullScreen = (Button) findViewById(R.id.button_full_screen_hints);
        if (buttonFullScreen!= null) {
            buttonFullScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), FullHintActivity.class));
                }
            });
        }

        Button buttonTargetHints = (Button) findViewById(R.id.button_target_hints);
        if (buttonTargetHints!= null) {
            buttonTargetHints.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), TargetHintActivity.class));
                }
            });
        }

        Button buttonCustomHints = (Button) findViewById(R.id.button_custom_hints);
        if (buttonCustomHints!= null) {
            buttonCustomHints.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), CustomHintActivity.class));
                }
            });
        }
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
