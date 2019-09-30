/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Model;
import model.IO;
import model.Ball;
import model.Brick;
import model.Paddle;
import view.View;

import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

/**
 *
 * @author Mehdi
 */
public class Controller implements IController, ActionListener {
   
    //-----------------
    private static IController controller = null;
    private boolean countDownOnWork = false;
    protected Timer timer = new Timer(10, this);

    
    //-----------------
    public static IController getInstance() {
        if (controller == null)
            controller = new Controller();
        return controller;
    }

    //-----------------
    public Ball ball(){
        return Model.getInstance().getBall();
    }
    
    public Paddle paddle(){
        return Model.getInstance().getPaddle();
    }
    
    public Brick[][] getBrickMatrix(){
        return Model.getInstance().getBrickMatrix();
    }
    
    public double dX(){
        return Model.getInstance().getBall().dX();
    }
    
    public double dY(){
        return Model.getInstance().getBall().dY();
    } 
    
    public int getLevel(){
        return Model.getInstance().getLevel();
    }
    
    public int getMaxLevelCompeted(){
        return Model.getInstance().getMaxLevelCompeted();
    }
    
    public int getLifes(){
        return Model.getInstance().getLifes();
    }
    
    public int getScore(){
        return Model.getInstance().getScore();
    }
    
    public boolean getGameStarted(){
        return Model.getInstance().getGameStarted();
    }
    
    public boolean getGameRunning(){
        return Model.getInstance().getGameRunning();
    }
    
    public boolean isSavedGame() {
        return IO.getInstance().isSavedGame();
    }
    
    public int getBestScore(){
        return IO.getInstance().getBestScoreSaved();
    }
    
    //-----------------
    //gestione della partita
    public void next(){
        this.ball().move();
        this.checkCollision();
    }
    
    public void handleMouseMoved(double i){
        if(this.getGameRunning() && 
                this.getGameStarted() && 
                i - (Paddle.WIDHT_PADDLE/2) >= 0 && 
                i + (Paddle.WIDHT_PADDLE/2) <= View.PREFERRED_WIDHT)
            this.paddle().setCoordXOfPaddle(i - (Paddle.WIDHT_PADDLE/2));
    }
    
    public void increaseSpeed(){
        Model.getInstance().getBall().setSpeed(Model.getInstance().getBall().getSpeed()+0.1);
    }
    
    public void startTimer(){
        this.timer.start();
    }
    
    public void stopTimer(){
        this.timer.stop();
    }
    
    public void handleStartPauseEvent(){
        if(!Model.getInstance().getGameStarted()){
            if(!countDownOnWork){
                countDownOnWork = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int c=3;
                        while(c>0){
                            View.getInstance().setTextLabel(String.valueOf(c));
                            System.out.println("i = " +c);
                            c--;
                            try{ Thread.sleep(1000); }catch(Exception e){}
                        }
                        //se è presente solo la stringa vuota o null o solo lo spazio non funziona
                        View.getInstance().setTextLabel("Pronto?");
                        Controller.getInstance().startTimer();
                        View.getInstance().removeStartPanel();
                        Model.getInstance().setGameStarted(true);
                        Model.getInstance().setGameRunning(true);
                        countDownOnWork = false;
                    }
                }).start();  
            }
        }else if(!Model.getInstance().getGameRunning()){
            Model.getInstance().setGameRunning(true);
            View.getInstance().removePausePanel();
            this.timer.start();
            
        }else if(Model.getInstance().getGameRunning()){
            Model.getInstance().setGameRunning(false);
            View.getInstance().addPausePanel();
            this.timer.stop();
        }
    }
    
    public void startNewGame(int level){
        Model.getInstance().setGameStarted(false);
        Model.getInstance().setGameRunning(false);
        Model.getInstance().setLifes(3);
        Model.getInstance().setScore(0);
        this.loadLevel(level);
        View.getInstance().openGameWindow();
        View.getInstance().addStartPanel();
        this.timer.stop();
    }
    
    public void startSavedGame(){
        this.loadSavedLevel();
        View.getInstance().closeStartWindow();
        View.getInstance().openGameWindow();
        View.getInstance().addStartPanel();
    }
    
    public void loadLevel(int level){
        this.timer.stop();
        Model.getInstance().setLevel(level);
        IO.getInstance().setMaxLevel();
        Model.getInstance().setGameStarted(false);
        Model.getInstance().setGameRunning(false);
        Model.getInstance().setNumOfBricks(IO.getNumOfBricksAtLevel(String.valueOf(level)));
        Model.getInstance().getBall().setCoordXOfBall(Ball.INITIAL_COORDX);
        Model.getInstance().getBall().setCoordYOfBall(Ball.INITIAL_COORDY);
        Model.getInstance().getBall().setDX(Ball.INITIAL_DX);
        Model.getInstance().getBall().setDY(Ball.INITIAL_DY);
        Model.getInstance().getBall().setSpeed(Ball.INITIAL_SPEED);
        Model.getInstance().getPaddle().setCoordXOfPaddle(Paddle.INITIAL_COORDX_PADDLE);
        Model.getInstance().setBrickMatrix(IO.getBricksStatementsAtLevel(String.valueOf(level)));
    }
    
    private void loadSavedLevel(){
        Model.getInstance().setLevel(Integer.parseInt(IO.getInstance().getLevelSaved()));
        IO.getInstance().setMaxLevel();
        Model.getInstance().setGameStarted(false);
        Model.getInstance().setGameRunning(false);
        Model.getInstance().setNumOfBricks(IO.getInstance().getNumOfBricksSaved());
        Model.getInstance().getBall().setCoordXOfBall(Double.parseDouble(IO.getInstance().getCoordXOfBallSaved()));
        Model.getInstance().getBall().setCoordYOfBall(Double.parseDouble(IO.getInstance().getCoordYOfBallSaved()));
        Model.getInstance().getBall().setDX(Double.parseDouble(IO.getInstance().getDXOfBallSaved()));
        Model.getInstance().getBall().setDY(Double.parseDouble(IO.getInstance().getDYOfBallSaved()));
        Model.getInstance().getBall().setSpeed(Double.parseDouble(IO.getInstance().getSpeedOfBallSaved()));
        Model.getInstance().getPaddle().setCoordXOfPaddle(Double.parseDouble(IO.getInstance().getCoordXOfPaddleSaved()));
        Model.getInstance().setBrickMatrix(IO.getInstance().getBrickStatementSaved());
        Model.getInstance().setScore(IO.getInstance().getScoreSaved());
        Model.getInstance().setLifes(IO.getInstance().getLifesSaved());
    }
    
    public void saveGame(){
        IO.getInstance().saveLevel(Model.getInstance().getLevel());
        IO.getInstance().setCoordXOfBall();
        IO.getInstance().setCoordYOfBall();
        IO.getInstance().setDXOfBall();
        IO.getInstance().setDYOfBall();
        IO.getInstance().setSpeedOfBall();
        IO.getInstance().setCoordXOfPaddle();
        IO.getInstance().setBrickStatement();
        IO.getInstance().setNumOfBricksSaved();
        IO.getInstance().setScore();
        IO.getInstance().setLifes();
    }
    
     public void deleteSave(){
        IO.getInstance().deleteSaveFile();
    }
    
    public void handleSaveEvent(){
        this.saveGame();
        IO.getInstance().setBestScore();
        View.getInstance().closeDialog();
        View.getInstance().closeGameWindow();
        View.getInstance().openStartWindow();
    }
    
    public void handleNotSaveEvent(){ 
        IO.getInstance().setBestScore();
        View.getInstance().closeDialog();
        View.getInstance().closeGameWindow();
        View.getInstance().openStartWindow();
    }
    
    public void handleLose(){
        IO.getInstance().setBestScore();
        if(isGameOver())
            this.handleGameOver();
        else{
            Model.getInstance().setLifes(Model.getInstance().getLifes() - 1);
            Model.getInstance().setGameStarted(false);
            Model.getInstance().setGameRunning(false);
            Model.getInstance().getBall().setCoordXOfBall(Ball.INITIAL_COORDX);
            Model.getInstance().getBall().setCoordYOfBall(Ball.INITIAL_COORDY);
            Model.getInstance().getBall().setDX(Ball.INITIAL_DX);
            Model.getInstance().getBall().setDY(Ball.INITIAL_DY);
            Model.getInstance().getBall().setSpeed(Ball.INITIAL_SPEED);
            Model.getInstance().getPaddle().setCoordXOfPaddle(Paddle.INITIAL_COORDX_PADDLE);
            this.timer.stop();
            View.getInstance().addStartPanel();
        }
    }
    
    public void handleGameOver(){
        this.timer.stop();
        Model.getInstance().setGameRunning(false);
        Model.getInstance().setGameStarted(false);
        View.getInstance().addLosePanel();
    }
    
    public void levelCompeted(){    
       Model.getInstance().setGameStarted(false);
       Model.getInstance().setGameRunning(false); 
       this.timer.stop();
        this.nextLevel();
    } 
    
    public void nextLevel(){  
        if(Model.getInstance().getLevel()<30){
            countDownOnWork = true;
            new Thread(new Runnable() {
                @Override
                public void run(){
                    View.getInstance().addWinPanel(); 
                    try{ Thread.sleep(1000); }catch(Exception e){}
                    View.getInstance().removeWinPanel();
                    Model.getInstance().setLevel(Model.getInstance().getLevel()+1);; 
                    Controller.getInstance().loadLevel(Model.getInstance().getLevel());
                    View.getInstance().addStartPanel();
                    countDownOnWork = false;
                }
            }).start();   
        }else
            View.getInstance().addCompletePanel();
    }
        
    public void checkCollision(){
        //Se mattinicini finiti, gioco finito
        if(Model.getInstance().getNumOfBricks() == 0){ 
            System.out.println("Livello completato");
            levelCompeted();
        }
        //Gestione contatto con Paddle
        if (this.paddle().contact(this.ball())){
            this.increaseSpeed();
            this.playSoundEffect(2);
            double tmp = ball().getCoordXOfBall() - this.paddle().coordXOfPaddle() - (Paddle.WIDHT_PADDLE/2);
            this.ball().setDX(tmp * 0.04);
            this.ball().setDY(-(Math.sqrt(Math.pow(Model.getInstance().getBall().getSpeed(),2) - Math.pow(this.dX(), 2)))); 
        }
        //Gestione contatto con Mattoncini
        for(int i = 0; i < Model.MATRIX_ROWS; i++)                            
            for(int j = 0; j < Model.MATRIX_COLUMNS; j++)    
                if(this.getBrickMatrix()[i][j].contact(this.ball())){   
                    
                    //collione con un mattonicino non nullo
                    if (this.getBrickMatrix()[i][j].getBrickStatement() != 0){
                        if (this.getBrickMatrix()[i][j].intersection(ball())[0] < this.getBrickMatrix()[i][j].intersection(ball())[1]){
                            if (ball().getCoordXOfBall() < this.getBrickMatrix()[i][j].getCenterX()){
                                ball().setCoordXOfBall(this.getBrickMatrix()[i][j].getX()-Ball.BALL_RADIUS);
                                ball().setCoordYOfBall(ball().getCoordYOfBall());
                            }else{
                                ball().setCoordXOfBall(this.getBrickMatrix()[i][j].getX()+Brick.BRICK_WIDHT+Ball.BALL_RADIUS);
                                ball().setCoordYOfBall(ball().getCoordYOfBall());
                            }
                            this.ball().setDX(-this.ball().dX());   
                        }else{
                            if(ball().getCoordYOfBall() < this.getBrickMatrix()[i][j].getCenterY()){
                                ball().setCoordXOfBall(ball().getCoordXOfBall());
                                ball().setCoordYOfBall(this.getBrickMatrix()[i][j].getY()-Ball.BALL_RADIUS);
                            }else{
                                ball().setCoordXOfBall(ball().getCoordXOfBall());
                                ball().setCoordYOfBall(this.getBrickMatrix()[i][j].getY()+Brick.BRICK_HEIGHT+Ball.BALL_RADIUS);
                            }
                        this.ball().setDY(-this.ball().dY()); 
                        }

                        //Gestione eliminazione mattoncino             
                        if (this.getBrickMatrix()[i][j].getBrickStatement() != 100){
                            if (this.getBrickMatrix()[i][j].getBrickStatement() <= 11){
                                playSoundEffect(0);
                                if (this.getBrickMatrix()[i][j].getBrickStatement() < 11)
                                    Model.getInstance().setScore(Model.getInstance().getScore() + 10);
                                if(this.getBrickMatrix()[i][j].getBrickStatement()==11)
                                    Model.getInstance().setScore(Model.getInstance().getScore() + 100);
                                this.getBrickMatrix()[i][j].setBrickStatement(0);
                                Model.getInstance().setNumOfBricks(Model.getInstance().getNumOfBricks() - 1); 
                                                
                            }else if (this.getBrickMatrix()[i][j].getBrickStatement() > 11){
                                this.getBrickMatrix()[i][j].setBrickStatement(this.getBrickMatrix()[i][j].getBrickStatement() - 1);
                                Model.getInstance().setNumOfBricks(Model.getInstance().getNumOfBricks() - 1);
                                playSoundEffect(1);
                            }
                            
                        } 
                    }//fine if mattoncino non nullo
                }//fine if contatto brick-ball
         
        //Gestione contatto con le pareti
        if(this.ball().getCoordXOfBall() - Ball.BALL_RADIUS <= 0 || this.ball().getCoordXOfBall() + Ball.BALL_RADIUS >= View.PREFERRED_WIDHT)
            this.ball().setDX(this.ball().dX() *(-1));
        
        if(this.ball().getCoordYOfBall() - Ball.BALL_RADIUS <= 0)
            this.ball().setDY(this.ball().dY() *(-1));     
        
        if(this.ball().getCoordYOfBall() + Ball.BALL_RADIUS >= View.PREFERRED_HEIGHT)
            handleLose();
    }
    
    public void handleHomeEvent(){
        this.saveGame();
        View.getInstance().closeGameWindow();
        View.getInstance().openStartWindow();
            
    }
    
    public boolean isGameOver(){
        boolean tmp = false;
        if(Model.getInstance().getLifes() == 0)
            tmp = true;
        return tmp;
    }
    
    //--------------------
    //Gestione suoni & effetti sonori
    public void playSoundEffect(final int k){
        new Thread(new Runnable(){
            public void run(){
                if (Model.getInstance().getSoundEffect(0)==null || Model.getInstance().getSoundEffect(1)==null){
                    System.out.println("No sound effects - Caricamennto effetti sonori");
                    try {   
                        Model.getInstance().setSoundEffect(IO.getInstance().loadSoundEffect());
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {	
                        e.printStackTrace();
                    }
                }
                Model.getInstance().getSoundEffect(k).setFramePosition(0);
                Model.getInstance().getSoundEffect(k).start();
            }
        }).start();
    }
    
    public void setVolumeSoundEffect(int i){
        Model.getInstance().setVolumeSoundEffect(i);
    }
    
    public int getVolumeSoundEffect(){
        return Model.getInstance().getVolumeSoundEffect();
    }
    
    public void playSoundTrack(){
        new Thread(new Runnable(){
            public void run(){
                if ( Model.getInstance().getSoundTrack()==null){	
                    try {   
                        Model.getInstance().setSoundTrack(IO.getInstance().loadSoundTrack());
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {	
                        e.printStackTrace();
                    }
                }		
                Model.getInstance().getSoundTrack().start();
                Model.getInstance().getSoundTrack().loop(Clip.LOOP_CONTINUOUSLY);
            
            }
        }).start();
    }
    
    public void setVolumeSoundTrack(int i){
        Model.getInstance().setVolumeSoundTrack(i);
    }
    
    public int getVolumeSoundTrack(){
        return Model.getInstance().getVolumeSoundTrack();
    }

    //-------------------------
    public void actionPerformed(ActionEvent e) {
	this.next();
    }
    
}//end class
