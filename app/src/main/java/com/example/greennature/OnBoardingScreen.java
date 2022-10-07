package com.example.greennature;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnBoardingScreen extends AppCompatActivity {
    //Declaring variables
    ViewPager viewPager;
    LinearLayout linearLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGetStarted, chip_btn, nxt_btn, skip_btn;
    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);

        //hiding the action bar from the activity
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //Setting hooks for components in activity
        viewPager = findViewById(R.id.slider);
        linearLayout = findViewById(R.id.slider_dots);
        letsGetStarted = findViewById(R.id.get_started_btn);
        chip_btn = findViewById(R.id.chip_1);
        nxt_btn = findViewById(R.id.next_btn);
        skip_btn = findViewById(R.id.skip_btn);

        //Calling Adapter Class
        sliderAdapter = new SliderAdapter(OnBoardingScreen.this);
        viewPager.setAdapter(sliderAdapter);

        //Calling the dots function
        addDots(0);

        //Calling the variable to set the color of the dots
        viewPager.addOnPageChangeListener(changeListener);

        //let's get started button click event
        letsGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingScreen.this, LoginActivity.class));
                finish();
            }
        });
    }

    //Making Dots visible function
    private void addDots(int position) {
        dots = new TextView[4];

        //removing the
        linearLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            //adding the view in ViewPager
            linearLayout.addView(dots[i]);
        }

        //Setting the color of the dots green if it is not empty
        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.primary_green));
        }
    }

    //Calling ViewPager inbuilt method for changing color of dots dynamically
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            currentPos = position;

            if (position == 0) {
                letsGetStarted.setVisibility(View.INVISIBLE);
                chip_btn.setVisibility(View.VISIBLE);
                nxt_btn.setVisibility(View.VISIBLE);
                skip_btn.setVisibility(View.VISIBLE);
            } else if (position == 1) {
                letsGetStarted.setVisibility(View.INVISIBLE);
                chip_btn.setVisibility(View.VISIBLE);
                nxt_btn.setVisibility(View.VISIBLE);
                skip_btn.setVisibility(View.VISIBLE);
            } else if (position == 2) {
                letsGetStarted.setVisibility(View.INVISIBLE);
                chip_btn.setVisibility(View.VISIBLE);
                nxt_btn.setVisibility(View.VISIBLE);
                skip_btn.setVisibility(View.VISIBLE);
            } else {
                letsGetStarted.setVisibility(View.VISIBLE);
                animation = AnimationUtils.loadAnimation(OnBoardingScreen.this, R.anim.bottom_anim);
                letsGetStarted.setAnimation(animation);
                chip_btn.setVisibility(View.INVISIBLE);
                nxt_btn.setVisibility(View.INVISIBLE);
                skip_btn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void skip(View view) {
        startActivity(new Intent(OnBoardingScreen.this, LoginActivity.class));
        finish();
    }

    public void next(View view) {
        viewPager.setCurrentItem(currentPos + 1);
    }
}