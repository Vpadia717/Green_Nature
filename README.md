# Green Nature

Here we have made a E - commerce application using java libraries like **Lottie** or **_TensorFlow Lite_**.

It is a *Gardening tool supplier app* where the user will get **_Oragnic and ecofriendly products for home gardening_**.

1. You need to install TensorFlow Lite from [here](https://www.tensorflow.org/)

```Java
new Handler().postDelayed(new Runnable() {
	@Override
	public void run() {
	
		onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

                if (isFirstTime) {

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();

                    startActivity(new Intent(MainActivity.this, OnBoardingScreen.class));
                    finish();
                } else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
		}
	}
}, SPLASH_TIMER);

```

### Important Instructions :

* The user needs to login to utilize the services.

Reference Code : 
```Java
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
```
Reference Images : <br>
	<br>![alt text](app/src/main/res/drawable-v24/Splash Screen.png)
	<br>![alt text](app/src/main/res/drawable-v24/Screen 4.png)
	
Reference Images : <br>
	<br>![alt text](app/src/main/res/drawable-v24/Screen 3.png)
	<br>![alt text](app/src/main/res/drawable-v24/Screen 2.png)

Reference Images : <br>
	<br>![alt text](app/src/main/res/drawable-v24/Screen 1.png)
	<br>![alt text](app/src/main/res/drawable-v24/Register.png)
	
This is the README file for Green Nature repository. [^1]

[^1]: By : Green Nature.
