package com.example.greennature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    //Declaring variables
    Button signup_login_btn, signup_btn;
    EditText phone_txt, pass_txt, user_txt, email_txt;
    TextInputLayout pname, pass, uname, ename;
    String username, email, phone, password;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //hiding the action bar from the activity
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //making lottie animation enable
        LottieAnimationView animationView = findViewById(R.id.signup_animation_view);
        animationView.playAnimation();

        //hooks for the variables
        signup_login_btn = findViewById(R.id.gn_signup_login_btn);
        signup_btn = findViewById(R.id.signup_btn);
        phone_txt = findViewById(R.id.signup_phone);
        pass_txt = findViewById(R.id.signup_password);
        user_txt = findViewById(R.id.signup_username);
        email_txt = findViewById(R.id.signup_email);
        pname = findViewById(R.id.signup_phone_label);
        pass = findViewById(R.id.signup_password_label);
        uname = findViewById(R.id.signup_username_label);
        ename = findViewById(R.id.signup_email_label);
        mAuth = FirebaseAuth.getInstance();

        //SignUp Button Calling
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateSiginUsernameData() | !validateSiginEmailNoData() | !validateSiginPhoneNoData() | !validateSiginPasswordData()) {
                    return;
                }
                SignIn();
            }
        });

        //Calling login activity from sign in screen
        signup_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                Pair[] pairs = new Pair[1];

                pairs[0] = new Pair<View, String>(findViewById(R.id.gn_signup_login_btn), "transition_signup");

                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this, pairs);
                startActivity(intent, activityOptions.toBundle());
                finish();
            }
        });
    }

    //Signing In using authentication
    private void SignIn() {
        username = user_txt.getText().toString().trim();
        email = email_txt.getText().toString().trim();
        phone = "+91" + phone_txt.getText().toString().trim();
        password = pass_txt.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("Users");

                    UserHelperClass addNewUser = new UserHelperClass(username, email, phone, password);

                    String userid = email.replaceAll("@gmail.com", " ").replaceAll("@yahoo.com", " ");

                    reference.child(userid).setValue(addNewUser);

                    startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                } else {
                    Toast.makeText(SignUpActivity.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Validating Username
    private boolean validateSiginUsernameData() {
        String val = user_txt.getText().toString().trim();
        String userPattern = "[a-zA-Z ]+";
        if (val.isEmpty()) {
            uname.setError("Username cannot be empty");
            return false;
        } else if (val.matches(userPattern)) {
            uname.setError(null);
            uname.setErrorEnabled(false);
            return true;
        } else {
            uname.setError("Not a valid Username");
            return false;
        }
    }

    //Validating Email
    private boolean validateSiginEmailNoData() {
        String val = email_txt.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            ename.setError("Email cannot be empty");
            return false;
        } else if (val.matches(emailPattern)) {
            ename.setError(null);
            ename.setErrorEnabled(false);
            return true;
        } else {
            ename.setError("Not a valid Email");
            return false;
        }
    }

    //Validating Phone Number
    private boolean validateSiginPhoneNoData() {
        String val = phone_txt.getText().toString().trim();
        if (val.isEmpty()) {
            pname.setError("Phone cannot be empty");
            return false;
        } else if (val.length() < 11 & val.length() > 9) {
            pname.setError(null);
            pname.setErrorEnabled(false);
            return true;
        } else {
            pname.setError("Not a valid number");
            return false;
        }
    }

    //Validating password
    private boolean validateSiginPasswordData() {
        String val = pass_txt.getText().toString().trim();
        if (val.isEmpty()) {
            pass.setError("Password cannot be empty");
            return false;
        } else if (val.length() < 9 & val.length() > 7) {
            pass.setError(null);
            pass.setErrorEnabled(false);
            return true;
        } else {
            pass.setError("Password must be of 8 characters");
            return false;
        }
    }
}