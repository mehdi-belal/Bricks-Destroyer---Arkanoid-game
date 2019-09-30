/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author Mehdi
 */
public class Model implements IModel {
    
    //-----------------
    private static IModel model = null; //attributo di tipo IModel  
        
    public final static double X_MARGIN = 50;
    public final static double Y_MARGIN = 50;
    public final static int MATRIX_ROWS = 12;
    public final static int MATRIX_COLUMNS = 8;
    
    public static final String PATH = "data/";
     
    private int numOfBricks;
    private int level;
    private int lifes;
    private int score;
    private boolean gameStarted;
    private boolean gameRunning;
    
    private int bestScore;
    private int maxLevel;
    
    private Ball ball = new Ball();
    private Paddle paddle = new Paddle();
    private Brick[][] bricksMatrix = new Brick[MATRIX_ROWS][MATRIX_COLUMNS];
    
    private static Clip[] soundEffect = new Clip[3];
    private static Clip soundTrack = null;
    private int volumeSoundTrack = 100;
    private int volumeSoundEffect = 100;

    
    //---------------------------------------
    public static IModel getInstance() {
        if (model == null)
            model = new Model();
        return model;
    }
    
    //---------------------------------------
    public int getLevel(){
        return this.level;
    }
    
    public void setLevel(int i){
        this.level = i;
    }
        
    public int getMaxLevelCompeted(){
        return IO.getInstance().getMaxLevelSaved();
    }
    
    public int getLifes(){
        return this.lifes;
    }
    
    public void setLifes(int i){
        this.lifes = i;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public void setScore(int i){
        this.score = i;
    }

    public Ball getBall(){
        return this.ball;
    }

    public Paddle getPaddle(){
        return this.paddle;
    }
    
    public Brick[][] getBrickMatrix(){    
       return this.bricksMatrix;
    }
       
    
    public void setBrickMatrix(String[] s){
        int[] m = new int[MATRIX_COLUMNS * MATRIX_ROWS];
        for (int i = 0; i < s.length; i++)
             m[i] = Integer.parseInt(s[i]);
        int tmp = 0;
        for(int i = 0; i < MATRIX_ROWS; i++){
            for(int j = 0; j < MATRIX_COLUMNS; j++){         
                this.bricksMatrix[i][j] = new Brick(X_MARGIN + (Brick.BRICK_WIDHT * i) + i, 
                                                    Y_MARGIN + (Brick.BRICK_HEIGHT * j) + j);
                this.bricksMatrix[i][j].setBrickStatement(m[tmp]);
                tmp++;
            }  
        }
    }
    
    public int getNumOfBricks(){
        return this.numOfBricks;
    }
    
    public void setNumOfBricks(int i){
        this.numOfBricks = i;
    }
   
    public boolean getGameStarted(){
        return this.gameStarted;
    }
    
    public void setGameStarted(boolean b){
        this.gameStarted = b;
    }
    
    public boolean getGameRunning(){
        return this.gameRunning;
    }
    
    public void setGameRunning(boolean b){
        this.gameRunning = b;
    }
    
    public Clip getSoundEffect(int i){
        return soundEffect[i];
    }
    
    public void setSoundEffect(Clip[] c){
        for(int i = 0; i < c.length; i++)
            this.soundEffect[i] = c[i];
    }
    
    public void setVolumeSoundEffect(int i){
        if(Model.getInstance().getSoundEffect(0)!=null){
            this.volumeSoundEffect = i;     
            FloatControl volume1 = (FloatControl) soundEffect[0].getControl(FloatControl.Type.MASTER_GAIN);
            FloatControl volume2 = (FloatControl) soundEffect[1].getControl(FloatControl.Type.MASTER_GAIN);//valore in decibel
            FloatControl volume3 = (FloatControl) soundEffect[2].getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(i/100.0) / Math.log(10.0) * 20.0); 
            volume1.setValue(dB);
            volume2.setValue(dB);
            volume3.setValue(dB);
        }     
    }
    
    public int getVolumeSoundEffect(){
        return this.volumeSoundEffect;
    }
    
    public Clip getSoundTrack(){
        return this.soundTrack;
    }
    
    public void setSoundTrack(Clip c){
        this.soundTrack = c;
    }
    
    public void setVolumeSoundTrack(int i){	
        if(Model.getInstance().getSoundTrack()!=null){	
            this.volumeSoundTrack = i;
            //getControl restituisce elemento di tipo control, forzo conversione in floatControl
            FloatControl volume = (FloatControl) soundTrack.getControl(FloatControl.Type.MASTER_GAIN); //valore in decibel
            float dB = (float) (Math.log(i/100.0) / Math.log(10.0) * 20.0); 
            volume.setValue(dB);
        }
    }
    
    public int getVolumeSoundTrack(){
        return this.volumeSoundTrack;
    }

}//end class