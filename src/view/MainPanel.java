/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import model.Brick;
import model.IO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

import javax.swing.event.MouseInputListener;

/**
 *
 * @author Mehdi
 */
public class MainPanel extends JPanel implements  MouseInputListener, KeyListener {

    //-----------------
    private Dimension preferredSize = new Dimension(View.PREFERRED_WIDHT, View.PREFERRED_HEIGHT);   
    private boolean gameRunning;
    private boolean gameStarted;
    private boolean drawOption = true;
    
    private Image blueBrick = IO.loadImage("brickBlue.png");
    private Image lightBlueBrick = IO.loadImage("brickLightBlue.png");
    private Image yellowBrick = IO.loadImage("brickYellow.png");
    private Image redBrick = IO.loadImage("brickRed.png");
    private Image pinkBrick = IO.loadImage("brickPink.png");
    private Image greenBrick = IO.loadImage("brickGreen.png");
    private Image purpleBrick = IO.loadImage("brickPurple.png");
    private Image destroybleBrick3 = IO.loadImage("brickDestroyble3.png");
    private Image destroybleBrick2 = IO.loadImage("brickDestroyble2.png");
    private Image destroybleBrick1 = IO.loadImage("brickDestroyble1.png");
    private Image indistruttibile = IO.loadImage("brickIndestructible.png");
    private Image background = IO.loadImage("p1.png");;
    private Image ball = IO.loadImage("ball1.png");;
    private Image paddle = IO.loadImage("Paddle.png");;
    
    private secondPanel losePanel;
    private secondPanel pausePanel;
    private secondPanel startPanel;
    private secondPanel winPanel;
    private secondPanel completePanel;
    
    private int countDown = 3;
    
    //-----------------
    public MainPanel(){
        this.setPreferredSize(preferredSize);        
        this.addMouseListener(this);
	this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        this.losePanel = new secondPanel("isLose");
        this.pausePanel = new secondPanel("isPause");
        this.startPanel = new secondPanel("isStart");
        this.winPanel = new secondPanel("isWin");
        this.completePanel = new secondPanel("isGameComplete");
        this.gameRunning = false;
        this.gameStarted = false;
    }


    //-----------------
    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D)g;
        this.paintBackGround(g2d);
        this.paintBricks(g2d);
        this.paintLifes(g2d);
        this.paintBall(g2d); 
        this.paintPaddle(g2d);
        g2d.setColor(Color.BLACK);
        this.printScore(g2d);
        repaint();
    }
    
    private void paintBackGround(Graphics2D g2d){
        g2d.drawImage(background,0,0,this);
    }
    
    public void selectBackground(int i){
        if(i == 1)
            this.background=IO.loadImage("p1.png");
        else if(i == 2)
            this.background=IO.loadImage("p2.png");
        else if(i == 3)
            this.background=IO.loadImage("p3.png");
        else if(i == 4)
            this.background=IO.loadImage("p4.png");
        else if(i == 5)
            this.background=IO.loadImage("p5.png");
        else if(i == 6)
            this.background=IO.loadImage("p6.png");
        else if(i == 7)
            this.background=IO.loadImage("p7.png");
    }
    
    public void selectBall(int i){
        if(i == 1)
            this.ball=IO.loadImage("ball1.png");
        else if(i == 2)
            this.ball=IO.loadImage("ball12.png");
        else if(i == 3)
            this.ball=IO.loadImage("ball3.png");
        else if(i == 4)
            this.ball=IO.loadImage("ball4.png");
        else if(i == 5)
            this.ball=IO.loadImage("ball5.png");
    }
    
    private void paintLifes(Graphics2D g2d){
        if(getDrawInfo()){
            Image tmp = paddle;
            for(int i = 0; i < Controller.getInstance().getLifes(); i++)
                g2d.drawImage(tmp, (45*i) + 7, 480, 35, 7, this);
        }
    }
    
    private void printScore(Graphics2D g2d){
        if(getDrawInfo()){
            Color oldColor = g2d.getColor();      
            g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            String s = "Score: " + String.valueOf(Controller.getInstance().getScore());
            g2d.drawString(s, 570, 485);
            this.validate();
            g2d.setColor(oldColor);
        }
    }
    
    private void paintPaddle(Graphics2D g2d){
        g2d.drawImage(paddle,
                      (int)this.updateCoordXOfPaddle(),
                      (int)model.Paddle.COORDY_OF_PADDLE,
                      (int)model.Paddle.WIDHT_PADDLE,
                      (int)model.Paddle.HEIGHT_PADDLE,
                       this);
    }
    
    private void paintBricks(Graphics2D g2d){
        int columns = model.Model.MATRIX_COLUMNS;
        int rows = model.Model.MATRIX_ROWS;        
        Image tmp = null;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){            
                if(Controller.getInstance().getBrickMatrix()[i][j].getBrickStatement() != model.Brick.EMPTY_BRICK){
                    tmp = this.setTheBrick(Controller.getInstance().getBrickMatrix()[i][j]);
                    g2d.drawImage(tmp,
                                 (int)(model.Model.X_MARGIN + (i * model.Brick.BRICK_WIDHT) + i),
                                 (int)(model.Model.Y_MARGIN + (j * model.Brick.BRICK_HEIGHT) + j),
                                 (int)model.Brick.BRICK_WIDHT,
                                 (int)model.Brick.BRICK_HEIGHT,
                                 this);                
                }
            }
        }
    }
    
    private Image setTheBrick(Brick b){
        Image tmp = null;
        if(b.getBrickStatement() == Brick.BLUE_BRICK)
            tmp = this.blueBrick;
        if(b.getBrickStatement() == Brick.LIGHT_BLUE_BRICK)
            tmp = this.lightBlueBrick;
        if(b.getBrickStatement() == Brick.YELLOW_BRICK)
            tmp = this.yellowBrick;
        if(b.getBrickStatement() == Brick.RED_BRICK)
            tmp = this.redBrick;
        if(b.getBrickStatement() == Brick.PINK_BRICK)
            tmp = this.pinkBrick;
        if(b.getBrickStatement() == Brick.GREEN_BRICK)
            tmp = this.greenBrick;
        if(b.getBrickStatement() == Brick.PURPLE_BRICK)
            tmp = this.purpleBrick;
        if(b.getBrickStatement() == Brick.DESTROYBLE_BRICK_3)
            tmp = this.destroybleBrick3;
        if(b.getBrickStatement() == Brick.DESTROYBLE_BRICK_2)
            tmp = this.destroybleBrick2;
        if(b.getBrickStatement() == Brick.DESTROYBLE_BRICK_1)
            tmp = this.destroybleBrick1;
        if(b.getBrickStatement() == Brick.INDISTRUTTIBILE)
            tmp = this.indistruttibile;
        return tmp;   
    }
    
    private void paintBall(Graphics2D g2d){
        g2d.drawImage(ball, 
                     (int)(this.updateCoordXOfBall() - model.Ball.BALL_RADIUS), 
                     (int)(this.updateCoordYOfBall() - model.Ball.BALL_RADIUS), 
                     (int)model.Ball.BALL_DIAMETER,
                     (int)model.Ball.BALL_DIAMETER,
                     this);        
    }
    
    
    private boolean getDrawInfo(){
        return drawOption;
    }
    
    private void setDrawInfo(boolean b){
        drawOption = b;
    }

    public double updateCoordXOfBall(){
        return Controller.getInstance().ball().getCoordXOfBall();
    }

    public double updateCoordYOfBall(){
        return Controller.getInstance().ball().getCoordYOfBall();
    }

    public double updateCoordXOfPaddle(){
        return Controller.getInstance().paddle().coordXOfPaddle();
    }
    
    public void addPausePanel(){
        this.add(this.pausePanel);
    }
    
    public void removePausePanel(){
        this.remove(this.pausePanel);
    }
    
    public void addStartPanel(){
        this.add(this.startPanel);
    }
    
    public void removeStartPanel(){
        this.remove(this.startPanel);
    }
    
    public void addLosePanel(){  
        this.add(this.losePanel);
    }
    
    public void removeLosePanel(){
        this.remove(this.losePanel);
    }
    
    public void addWinPanel(){
        this.add(this.winPanel);
    }
    
    public void removeWinPanel(){
        this.remove(this.winPanel);
    }
    
    public void addCompletePanel(){
        this.add(this.completePanel);
    }
    
    public void removeCompletePanel(){
        this.remove(this.completePanel);
    }
    
    public int getCountDown(){
        return countDown;
    }
    
    public void setTextLabel(String s){
        this.startPanel.countDownLabel.setText(s);
    }
    
    
    //IMPLEMENTAZIONE DEI METODI EREDITATI DA KEYLISTENER 
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                Controller.getInstance().handleStartPauseEvent();
                break;
                
            case KeyEvent.VK_A:
                this.setDrawInfo(!this.getDrawInfo());
                break;
        }
    }    
    
    public void keyReleased(KeyEvent e) {
        //do nothing
    }

    public void keyTyped(KeyEvent e) {
        //do nothing
    }
    
    
    //IMPLEMENTAZIONE DEI METODI EREDITATI DA MOUSEIMPUTLISTENER
    public void mouseMoved(MouseEvent e)  {
        Controller.getInstance().handleMouseMoved(e.getX());
    }
    
    public void mouseClicked(MouseEvent e) {
        // do nothing
    }

    public void mouseEntered(MouseEvent e)  {
	// do nothing
    }

    public void mouseExited(MouseEvent e) {
        // do nothing
    }

    public void mousePressed(MouseEvent e) {
	// do nothing
    }

    public void mouseReleased(MouseEvent e)  {
	// do nothing
    }

    public void mouseDragged(MouseEvent e)  {
        // do nothing
    }
    

    //PANNELLO SECONDARIO
    class secondPanel extends JPanel{
        
        private JLabel titleLabel;
        private JLabel countDownLabel;
        private JButton leftButton;
        private JButton rightButton;
        
        private Dimension preferredSize = new Dimension(View.PREFERRED_WIDHT - 10, View.PREFERRED_HEIGHT - 10);
   
        private ActionListener pause = new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    Controller.getInstance().handleStartPauseEvent();
                }
            };
        
        private ActionListener dialog = new ActionListener(){   
            public void actionPerformed(ActionEvent evt) {    
                View.getInstance().openDialog();
            }   
        };
              
        private ActionListener restart = new ActionListener(){    
            public void actionPerformed(ActionEvent evt) {
                View.getInstance().removeLosePanel();
                Controller.getInstance().startNewGame(Controller.getInstance().getLevel());  
            }
        }; 
            
        private ActionListener home = new ActionListener(){ 
            public void actionPerformed(ActionEvent evt) {
                Controller.getInstance().handleNotSaveEvent();
                removeLosePanel();
                removeCompletePanel();
            }
        };

        public secondPanel(String s){
            super(new BorderLayout());           
            this.setPreferredSize(preferredSize);      
            this.setOpaque(false);
            
            this.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
            titleLabel = new JLabel();
            countDownLabel = new JLabel();
            titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40)); 
            countDownLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40)); 
            titleLabel.setText("Pausa"); 
            leftButton = new JButton();
            rightButton = new JButton();
            leftButton.setContentAreaFilled(false);
            rightButton.setContentAreaFilled(false);
            
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
            buttonPane.add(Box.createRigidArea(new Dimension(100, 0)));
            buttonPane.add(leftButton);
            buttonPane.add(Box.createHorizontalGlue());
            buttonPane.add(rightButton);
            buttonPane.add(Box.createRigidArea(new Dimension(100, 0)));
            buttonPane.setBackground(new Color(255, 255, 255, 0));
        
            if(s == "isLose"){
                this.titleLabel.setText("Hai perso");
                titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40)); 
                this.leftButton.setVisible(true);
                this.rightButton.setVisible(true);
                this.leftButton.setIcon(new ImageIcon(getClass().getResource("/data/reset.png")));
                this.leftButton.addActionListener(this.restart);
                this.rightButton.setIcon(new ImageIcon(getClass().getResource("/data/home.png")));     
                this.rightButton.addActionListener(this.home);
                
                this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
                this.add(Box.createRigidArea(new Dimension(0, 80)));
                this.add(titleLabel);
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.add(Box.createRigidArea(new Dimension(0, 80)));
                this.add(buttonPane);

            }else if(s == "isPause"){
                this.titleLabel.setText("Pausa");
                titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40)); 
                this.leftButton.setVisible(true);      
                this.rightButton.setVisible(true);
                this.leftButton.setIcon(new ImageIcon(getClass().getResource("/data/play.png")));
                this.leftButton.addActionListener(this.pause);   
                this.rightButton.setIcon(new ImageIcon(getClass().getResource("/data/home.png")));
                this.rightButton.addActionListener(this.dialog);
              
                this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
                this.add(Box.createRigidArea(new Dimension(0, 80)));
                this.add(titleLabel);
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.add(Box.createRigidArea(new Dimension(0, 80)));
                this.add(buttonPane);
              
            }else if(s == "isStart"){
                this.titleLabel.setText("Premi Spazio per iniziare..");
                titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40)); 
                countDownLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40)); 
                this.titleLabel.setVisible(true);
                this.rightButton.setVisible(true);
                this.rightButton.setIcon(new ImageIcon(getClass().getResource("/data/home.png")));
                this.rightButton.addActionListener(this.home);
                this.leftButton.setVisible(false);
            
                this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
                JPanel p = new JPanel();
                p.setBackground(new Color(255,255,255,0));
                p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
                p.add(Box.createHorizontalGlue());
                p.add(rightButton);
                this.add(p);
            
                this.add(Box.createRigidArea(new Dimension(0, 80)));
                this.add(titleLabel);
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.add(Box.createRigidArea(new Dimension(0, 80)));
                this.add(countDownLabel);
                countDownLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        
            else if(s == "isWin"){
                this.titleLabel.setText("Livello Completato!");
                titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40)); 
                this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
                this.add(Box.createRigidArea(new Dimension(0, 80)));
                this.add(titleLabel);
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        
            else if (s == "isGameComplete"){
                this.titleLabel.setText("Complimenti!");
                titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40)); 
                this.countDownLabel.setText("Hai completato tutti i livelli :)");
                countDownLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30)); 
                this.rightButton.setVisible(true);
                this.rightButton.setIcon(new ImageIcon(getClass().getResource("/data/home.png")));
                this.rightButton.addActionListener(this.home);
                this.leftButton.setVisible(false);
                this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            
                this.add(Box.createRigidArea(new Dimension(0, 80)));
                this.add(titleLabel);
                this.add(Box.createRigidArea(new Dimension(0, 80)));
                this.add(countDownLabel);
                this.add(Box.createRigidArea(new Dimension(0, 40)));
                this.add(rightButton);
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                countDownLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                rightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            }   
        }//Fine costrutture con stringa

         @Override
            protected void paintComponent(Graphics g) {               
                Graphics2D g2d = (Graphics2D)g;
                g.setColor(new Color(255, 255, 255, 128));
                g.fillRect(0, 0, View.PREFERRED_WIDHT, View.PREFERRED_HEIGHT);
                //printPause(g2d);
            }
    }//fine sottoclasse   
 
    
}//end class