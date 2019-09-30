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
public class Paddle {
    
    //-----------------
    private double coordXOfPaddle;
    public final static double INITIAL_COORDX_PADDLE = 320;
    public final static double COORDY_OF_PADDLE = 450;    
    public final static double WIDHT_PADDLE = 70;
    public final static double HEIGHT_PADDLE = 14;
    
    //-----------------
    public double coordXOfPaddle(){
        return this.coordXOfPaddle;
    }
    
    public void setCoordXOfPaddle(double i){
        this.coordXOfPaddle= i;
    }
    
    //restituisce se la pallina ha colpito la barra
    public boolean contact(Ball b) {
        double tw = this.WIDHT_PADDLE;
        double th = this.HEIGHT_PADDLE;
        double bw = Ball.BALL_DIAMETER;
        double bh = Ball.BALL_DIAMETER;
        if (bw <= 0 || bh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        double tx = this.coordXOfPaddle;
        double ty = this.COORDY_OF_PADDLE;
        double rx = b.getCoordXOfBall() - Ball.BALL_RADIUS; //coordinata x della pallina (del rettangolo che la racchiude)
        double ry = b.getCoordYOfBall() - Ball.BALL_RADIUS; //coordinata y della pallina (del rettangolo che la racchiude)
        bw += rx;
        bh += ry;
        tw += tx;
        th += ty;
        return ((bw < rx || bw > tx) &&
                (bh < ry || bh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }
    
}
