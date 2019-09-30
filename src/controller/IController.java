/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Ball;
import model.Brick;
import model.Paddle;

/**
 *
 * @author Mehdi
 */
public interface IController {
      
    public Ball ball();
    
    public Paddle paddle();
    
    public Brick[][] getBrickMatrix();
    
    public int getLifes();
    
    public int getScore();
    
    public void next();

    public void handleMouseMoved(double j);
    
    public void handleStartPauseEvent();
        
    public int getLevel();
    
    public boolean getGameStarted();
    
    public boolean getGameRunning();

    public void startTimer();
    
    public void stopTimer();
    
    public int getMaxLevelCompeted();
    
    public void handleHomeEvent();
        
    public void startNewGame(int level);
        
    public void loadLevel(int level);
    
    public void handleSaveEvent();
    
    public void handleNotSaveEvent();
    
    public boolean isSavedGame();
    
    public int getBestScore();
    
    public void startSavedGame();
    
    public void deleteSave();
             
    public void playSoundTrack();
         
    public void setVolumeSoundEffect(int i);
    
    public int getVolumeSoundEffect();
         
    public void setVolumeSoundTrack(int i);
    
    public int getVolumeSoundTrack();
         
}