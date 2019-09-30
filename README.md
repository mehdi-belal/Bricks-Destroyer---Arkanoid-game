# Bricks-Destroyer --- Arkanoid game in Java
one of my first projects in Java! a program created for the exam of "Elements of graphic informatics", developed during the three-year degree (30 cum laude :) )

This project is aimed at the development in Java of a graphic application inspired by the Arkanoid and Breakout arcade games.

## The game: Arkanoid and Breakout
In this kind of games the aim is to knock down a brick wall at the top of the screen, at the bottom there is only a small bar that can be moved to the right and left; with this you have to hit a bouncing ball, so that it destroys all the bricks that make up the wall. If the player fails to hit the ball with his own bar, it comes out of the screen and you lose a life, you lose all lives you lose the game; while knocked down all the bricks the player goes to the next level. A score is assigned each time a brick is hit, based on the type of brick, and each time a power-up is awarded (in the case of Arkanoid).

## The Bricks Destroyer application
Bricks Destroyer is not a faithful reproduction of the two applications from which it draws inspiration. The objective of the player is to overcome the 30 levels present and totalize the maximum possible score, destroying all the bricks present in the level without losing all lives, on pain of losing the game; to keep in mind the fact that the score obtained is independent of the difficulty of the level: an extremely difficult level can score a not very high score, what counts is the ability in being able to overcome the maximum number of levels possible with the suns lives available. The player interacts only through the Paddle, or the bar with which you have to avoid dropping the ball on the bottom of the screen, for the rest the movement of the ball is governed by the topology with which the bricks are arranged. Every time the ball hits an obstacle it bounces changing direction, with an angle equal to the angle of incidence; while when the ball hits the paddle it bounces according to the angle with which it is hit, the player must have the ability to position the paddle so that when the ball hits it it goes in the desired direction.

![gameplay](https://github.com/mehdi-belal/Bricks-Destroyer---Arkanoid-game/blob/master/doc/play_game.PNG)

## Software Architecture
For the realization of this project it was decided to stick to the Model-View-Adapter pattern, a variant of the Model-Controller-View in which the Model and View blocks do not interact directly but only communicate through the Controller (except for some cases that will come illustrated in detail below).
The Model encapsulates the state of the game, including all the variables that describe the current state of the game. The View, constituting the graphic interface, is responsible for displaying graphic information, interfacing between the various windows and also animations. The Controller manages the information received as input via the interface provided by the View, and processes it through the data provided by the Model.
Although from the point of view of a videogame this pattern is not ideal, the main purpose of this subdivision is to create a program that is more orderly and geared towards the logic of object-oriented programming, in fact a structure of this type allows greater possibilities for future updates and added features. In figure 2 the general architecture of the application.


![mainArch](https://github.com/mehdi-belal/Bricks-Destroyer---Arkanoid-game/blob/master/doc/structure.PNG)


## Game logic
As for the movement of the ball it should be clear that if it hits an obstacle such as the walls or a brick, after the collision it bounces off as a mirror, while if it hits the paddle it bounces according to the point of contact, to make the directionality of the ball. To make it clearer what is meant by "directionability" of the ball the part of the code that manages the contact between the ball and Paddle is reported:
```java
if (this.paddle().contact(this.ball())){
  this.increaseSpeed();
  this.playSoundEffect(2);
  double tmp = ball().getCoordXOfBall() - this.paddle().coordXOfPaddle() – 
    (Paddle.WIDHT_PADDLE/2);	
  this.ball().setDX(tmp * 0.04);
  this.ball().setDY(-(Math.sqrt(Model.getInstance().getBall().getSpeed() –
    Math.pow(this.dX(), 2)))); 
}

```

This piece of code is part of the checkCollision () method; as already mentioned it deals with managing the contact between the ball and the Paddle, detecting a possible contact and managing the new direction of the ball;
In the first analysis the "contact" between the two elements is verified, if this happens we enter the body of the if;
The speed of the ball is increased, in fact this happens every time the ball hits the Paddle, this to make the game more difficult if it takes a long time to complete the level (see Requirements Specifications).
The sound effect of contact with the paddle is started.
At this point the new direction of the ball is calculated, as follows:
The point at which the ball hits the paddle is calculated, more precisely the distance to the center of the paddle and the point of contact is calculated, and the data is stored in the variable tmp (note that this value can be positive or negative).
The new right value of the ball is calculated by multiplying the value in tmp by a coefficient equal to 0.04 (chosen after a series of experimental tests), while the new value of dy is calculated as:
![paddleLogic](https://github.com/mehdi-belal/Bricks-Destroyer---Arkanoid-game/blob/master/doc/formula.PNG)
So that firstly along the x the ball follows a direction that is proportional to the point of contact, while along y is proportional to the speed and to the right, but always contrary to the direction of arrival (thanks to the minus sign), so as to guarantee the "rebound".

![paddleLogic](https://github.com/mehdi-belal/Bricks-Destroyer---Arkanoid-game/blob/master/doc/paddle_logic.PNG)
