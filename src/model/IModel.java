/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.sound.sampled.Clip;

/**
 *
 * @author Mehdi
 */
public interface IModel {
    
    public int getLevel();
    
    public void setLevel(int i);
    
    public int getMaxLevelCompeted();
    
    public int getLifes();
    
    public void setLifes(int i);
    
    public int getScore();
    
    public void setScore(int i);
    
    public Ball getBall();
    
    public Paddle getPaddle();  
    
    public Brick[][] getBrickMatrix();
    
    public void setBrickMatrix(String[] s);
    
    public int getNumOfBricks(); 
    
    public void setNumOfBricks(int i);
    
    public boolean getGameStarted();
    
    public void setGameStarted(boolean b);
    
    public boolean getGameRunning();
    
    public void setGameRunning(boolean b);
    
    public Clip getSoundEffect(int i);
    
    public void setSoundEffect(Clip[] c);
    
    public void setVolumeSoundEffect(int i);
    
    public int getVolumeSoundEffect();
     
    public Clip getSoundTrack();
     
    public void setSoundTrack(Clip c);
     
    public void setVolumeSoundTrack(int i);
        
    public int getVolumeSoundTrack();
}
