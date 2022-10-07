package com.example.greennature;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.chaos.view.PinView;

public class OTP_Screen extends AppCompatActivity {
    //Variables
    Button close_btn, verify_Btn;
    PinView pinView;
    TextView user_num;
    String verificationCodeBySystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);

        //hiding the action bar from the activity
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //Setting the lottie animation
        LottieAnimationView animationView = findViewById(R.id.otp_animation_view);
        animationView.addAnimatorUpdateListener((animation) -> {
            // Do something.
        });
        animationView.playAnimation();

        //hooks for the variable declared
        close_btn = findViewById(R.id.close_btn);
        user_num = findViewById(R.id.num_from_login);
        pinView = findViewById(R.id.pinView);
        verify_Btn = findViewById(R.id.verify_btn);

        //getting the phone number from the Login Activity
        String phoneNo = getIntent().getStringExtra("phoneNo");

        //Creating the method for getting OTP
        verify_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        //setting the number of user into textView
        user_num.setText(phoneNo);

        //going back to login screen from OTP activity
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                Pair[] pairs = new Pair[1];

                pairs[0] = new Pair<View, String>(findViewById(R.id.close_btn), "transition_login_from_OTP");

                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(OTP_Screen.this, pairs);
                startActivity(intent, activityOptions.toBundle());
                finish();
            }
        });
    }
}