/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;

/**
 *
 * @author Mehdi
 */
public class View implements IView {

    //-----------------
    private static IView view = null; //attributo di tipo IView

    //-----------------
    public static final int PREFERRED_HEIGHT = 500;
    public static final int PREFERRED_WIDHT = 700;
    private StartWindow startWindow = null;
    private LevelSelectionWindow levelSelectionWindow = null;
    private GameWindow gameWindow = new GameWindow();
    private SaveDialog dialog = new SaveDialog(gameWindow, true);

    //-----------------
    public static IView getInstance() {
        if (view == null)
            view = new View();
        return view;
    }

    //-----------------
    public void openStartWindow(){
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
		if (startWindow == null)
                    startWindow = new StartWindow();
		startWindow.setVisible(true);
            }
	});
    }
    
    public void closeStartWindow(){
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                startWindow.setVisible(false);
		if (startWindow != null){
                    startWindow = null;
                }
            }
	});
    }
    
    public void openLevelSelectionWindow(){
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
		if (levelSelectionWindow == null)
                    levelSelectionWindow = new LevelSelectionWindow();
		levelSelectionWindow.setVisible(true);
            }
	});
    }
    
    public void closeLevelSelectionWindow(){
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
		if (levelSelectionWindow != null){
                    levelSelectionWindow = null;
                }
                
            }
	});
    }

    public void openGameWindow(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
		//if (gameWindow == null)
                    //gameWindow = new GameWindow();
		gameWindow.setVisible(true);
            }
	});
    }
    
       public void closeGameWindow(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameWindow.setVisible(false);
		//if (gameWindow != null)
                //    gameWindow = null;
                
            }
	});
    }
       
       public void openDialog(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
		if (dialog == null)
                    dialog = new SaveDialog(gameWindow, true);
		dialog.setVisible(true);
            }
	});
    }
    
       public void closeDialog(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dialog.setVisible(false);
            }
	});
    }
    
    public void addPausePanel(){
        gameWindow.addPausePanel();
    }
    
    public void removePausePanel(){
        gameWindow.removePausePanel();
    }
    
    public void addStartPanel(){
        gameWindow.addStartPanel();
    }
    
    public void removeStartPanel(){
        gameWindow.removeStartPanel();
    }
    
    public void addLosePanel(){
        gameWindow.addLosePanel();
    }
    
    public void removeLosePanel(){
        gameWindow.removeLosePanel();
    }
   
    public void addWinPanel(){
        gameWindow.addWinPanel();
    }
    
    public void removeWinPanel(){
        gameWindow.removeWinPanel();
    }
    
    public void addCompletePanel(){
        gameWindow.addCompletePanel();
    }
    
    public void removeCompletePanel(){
        gameWindow.removeCompletePanel();
    }
    
    public void setTextLabel(String s){
        gameWindow.setTextLabel(s);
    }

    public void selectBackground(int i){
        this.gameWindow.selectBackground(i);
    }
    
    public void selectBall(int i){
        this.gameWindow.selectBall(i);
    }
    
}