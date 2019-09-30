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
public class Brick {
    
    //-----------------
    public final static double BRICK_WIDHT = 50;
    public final static double BRICK_HEIGHT = 20;
    
    public final static int EMPTY_BRICK = 0;
    public final static int BLUE_BRICK = 1;
    public final static int LIGHT_BLUE_BRICK = 2;
    public final static int YELLOW_BRICK = 3;
    public final static int RED_BRICK = 4;
    public final static int PINK_BRICK = 5;
    public final static int GREEN_BRICK = 6;
    public final static int PURPLE_BRICK = 7;
    public final static int DESTROYBLE_BRICK_3 = 13;
    public final static int DESTROYBLE_BRICK_2 = 12;
    public final static int DESTROYBLE_BRICK_1 = 11;
    public final static int INDISTRUTTIBILE = 100;
    
    private double x;
    private double y;
    private int brickStatement;

    //-----------------
    public Brick(){
        //costruttore nullo
    }
    
    public Brick(double x,double y){
        this.x = x;
        this.y = y;   
    }
    
    //-----------------
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    public double getCenterX(){
        return getX() + this.BRICK_WIDHT / 2.0;
    }
    
    public double getCenterY(){
        return getY() + this.BRICK_HEIGHT / 2.0;
    }
    
    public int getBrickStatement(){
        return this.brickStatement;
    }
    
    public void setBrickStatement(int i){
        this.brickStatement = i;
    }
    
    public boolean contact(Ball b) {
        if (this.BRICK_WIDHT <= 0.0 || this.BRICK_HEIGHT <= 0.0) {
            return false;
        }
        double diam = Ball.BALL_DIAMETER;
        if (diam <= 0.0) {
            return false;
        }
        double normx0 = (this.x - (b.getCoordXOfBall() - Ball.BALL_RADIUS) ) / diam - 0.5;
        double normx1 =normx0 + this.BRICK_WIDHT / diam;
        double ellh = Ball.BALL_DIAMETER;
        if (ellh <= 0.0) {
            return false;
        }
        double normy0 = (this.y - (b.getCoordYOfBall() - Ball.BALL_RADIUS) ) / ellh - 0.5;
        double normy1 = normy0 + this.BRICK_HEIGHT / ellh;
        double nearx, neary;
        if (normx0 > 0.0) {
            nearx = normx0;
        } else if (normx1 < 0.0) {
            nearx = normx1;
        } else {
            nearx = 0.0;
        }
        if (normy0 > 0.0) {
            neary = normy0;
        } else if (normy1 < 0.0) {
            neary = normy1;
        } else {
            neary = 0.0;
        }
        return (nearx * nearx + neary * neary) < 0.25;
    }
    
    public double[] intersection(Ball b){
        double[] dest = new double[2];
        double x = Math.max(b.getCoordXOfBall() - Ball.BALL_RADIUS, this.getX());
        double y = Math.max(b.getCoordYOfBall() - Ball.BALL_RADIUS, this.getY());
        double maxx = Math.min(b.getCoordXOfBall() + Ball.BALL_RADIUS, this.getX() + BRICK_WIDHT);
        double maxy = Math.min(b.getCoordYOfBall() + Ball.BALL_RADIUS, this.getY() + BRICK_HEIGHT);
        
        dest[0] = maxx - x;//widht del rettangolo intersezione
        dest[1] = maxy - y;//height del rettangolo intersezione
        return dest;    
    }

    
}//end class