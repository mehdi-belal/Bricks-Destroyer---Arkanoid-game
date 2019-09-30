/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Mehdi
 */
public class Ball {
        
    //-----------------
    public final static double INITIAL_DX = -0.75;
    public final static double INITIAL_DY = -1.0;
    public static final double BALL_RADIUS = 5;
    public static final double BALL_DIAMETER = BALL_RADIUS * 2;
    public final static double INITIAL_COORDX = Paddle.INITIAL_COORDX_PADDLE + (Paddle.WIDHT_PADDLE/2);
    public final static double INITIAL_COORDY = Paddle.COORDY_OF_PADDLE - Ball.BALL_RADIUS;
    public final static double INITIAL_SPEED = 1.25;
    
    private double coordXOfBall;
    private double coordYOfBall;
    private double dX;
    private double dY;
    private double speed; 
    
    //-----------------
    public double getCoordXOfBall(){
        return this.coordXOfBall;
    }
   
    public double getCoordYOfBall(){
        return this.coordYOfBall;
    }
    
    public void setCoordXOfBall(double i){
        this.coordXOfBall = i;
    }
    
    public void setCoordYOfBall(double j){
        this.coordYOfBall = j;
    }
    
    public double dX(){
        return this.dX;        
    }
    
    public double dY(){
        return this.dY;
    }
    
    public void setDX(double i){
        this.dX = i;
    }
    
    public void setDY(double j){
        this.dY = j;
    }
    
    public double getSpeed(){
        return this.speed;
    }
    
    public void setSpeed(double s){
        this.speed = s;
    }
    
    public void move(){
        this.coordXOfBall += this.dX;
        this.coordYOfBall += this.dY;
    }
    
}
