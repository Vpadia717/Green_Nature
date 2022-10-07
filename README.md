# Green Nature

Here we have made a E - commerce application using java libraries like **Lottie** or **_TensorFlow Lite_**.

It is a *Gardening tool supplier app* where the user will get **_Oragnic and ecofriendly products for home gardening_**.

1. You need to install TensorFlow Lite from [here](https://www.tensorflow.org/)

``` 
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
```python

pygame.init()

pygame.mixer.init()

clock = pygame.time.Clock()

screen = pygame.display.set_mode((gameConfigs["width"], gameConfigs["height"]))

pygame.display.set_caption(gameConfigs["title"])

fontSmall = pygame.font.Font("assets/fonts/bitcell.ttf", 30)

fontLarge = pygame.font.Font("assets/fonts/bitcell.ttf", 200)

run = True
actualLevel = 1
```
Reference Images : <br>
	<br>![alt text](assets/images/game_preview.png)
	
This is the README file for Battle-Space repository. [^1]

[^1]: By : Vivek Padia.
