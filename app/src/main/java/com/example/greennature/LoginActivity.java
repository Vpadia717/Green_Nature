package com.example.greennature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //Variables
    Button reg_btn, login_btn;
    EditText email_txt, pass_txt;
    TextInputLayout ename, pass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //hiding the action bar from the activity
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //Setting lottie animation
        LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.playAnimation();

        //hooks
        reg_btn = findViewById(R.id.gn_login_reg_btn);
        login_btn = findViewById(R.id.login_btn);
        email_txt = findViewById(R.id.login_email);
        pass_txt = findViewById(R.id.login_password);
        ename = findViewById(R.id.login_email_label);
        pass = findViewById(R.id.login_password_label);
        mAuth = FirebaseAuth.getInstance();

        //Login button
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isConnected(LoginActivity.this)) {
                    showCustomDialog();
                }

                //Applying Validation on run time data
                if (!validateLoginEmailData() | !validateLoginPasswordData()) {
                    return;
                }

                String email = email_txt.getText().toString().trim();
                String pass = pass_txt.getText().toString().trim();
                SignIn(email, pass);
            }
        });

        //Calling register activity
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);

                Pair[] pairs = new Pair[1];

                pairs[0] = new Pair<View, String>(findViewById(R.id.gn_login_reg_btn), "transition_register");

                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(intent, activityOptions.toBundle());
            }
        });
    }


    //showing custom dialog
    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Connect to internet to proceed further").setCancelable(false).setPositiveButton("Connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }

    //checking for is Connection available
    private boolean isConnected(LoginActivity loginActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) loginActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    //signing In
    private void SignIn(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //checking if the user is already logged in or not
    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
            finish();
        }
    }

    //Validating Email
    private boolean validateLoginEmailData() {
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

    //Validating Password
    private boolean validateLoginPasswordData() {
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