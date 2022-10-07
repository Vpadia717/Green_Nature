# Green Nature

Here we have made a E - commerce application using java libraries like **Lottie** or **_TensorFlow Lite_**.

It is a *space battle game* where the user will get **_4 Lives_** by default.

1. You need to install Python 3.10.4 from [here](https://www.python.org/downloads/)
2. You need to install Pygame
``` 
pip install pygame
```
2. OR you can use this [Link](https://pypi.org/project/pygame/)
3. And the total Requirements is full filled.

### Important Instructions :

* The User by default gets 4 Lives for the game.
* The Enemy battle ships gets incremented as the game goes on.
* With the help of **Space Bar** the user shoot the bullets to the Enemy Battle Ships.
* With the help of Directional keys the User can control the flow of their own Battle Ship.

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
