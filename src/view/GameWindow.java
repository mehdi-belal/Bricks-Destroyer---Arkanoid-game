/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
/**
 *
 * @author Mehdi
 */
public class GameWindow extends JFrame {
    
    //-----------------
    private MainPanel mainPanel = null;

    //-----------------
    public GameWindow(){
        super("Bricks Destroyer");
        createGUI();
    }
    
    //-----------------
    private void createGUI(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(300,150);
        mainPanel = new MainPanel();
        
        Container contPane = this.getContentPane();
	contPane.setLayout(new BorderLayout());
	contPane.add(this.mainPanel, BorderLayout.CENTER);
        this.setResizable(false);
	this.pack();
        
    }//end creatGUI()

   public void addPausePanel(){
        mainPanel.addPausePanel();
    }
    
    public void removePausePanel(){
        mainPanel.removePausePanel();
    }
    
    public void addStartPanel(){
        mainPanel.addStartPanel();
    }
    
    public void removeStartPanel(){
        mainPanel.removeStartPanel();
    }
    
    public void addLosePanel(){
        mainPanel.addLosePanel();
    }
    
    public void removeLosePanel(){
        mainPanel.removeLosePanel();
    }
    
    public void addWinPanel(){
        mainPanel.addWinPanel();
    }
    
    public void removeWinPanel(){
        mainPanel.removeWinPanel();
    }
    
    public void addCompletePanel(){
        mainPanel.addCompletePanel();
    }
    
    public void removeCompletePanel(){
        mainPanel.removeCompletePanel();
    }
    
   public void setTextLabel(String s){
       mainPanel.setTextLabel(s);
   }
   
   public void selectBackground(int i){
       mainPanel.selectBackground(i);
   }
   
   public void selectBall(int i){
       mainPanel.selectBall(i);
   }
    
}//end class