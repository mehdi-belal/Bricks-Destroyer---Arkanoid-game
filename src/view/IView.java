/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Mehdi
 */
public interface IView {
    
    public void openStartWindow();
    
    public void closeStartWindow();
    
    public void openGameWindow();
    
    public void closeGameWindow();
    
    public void openDialog();
    
    public void closeDialog();
    
    public void openLevelSelectionWindow();
    
    public void closeLevelSelectionWindow();
    
    public void addPausePanel();
    
    public void removePausePanel();
    
    public void addStartPanel();
    
    public void removeStartPanel();
    
    public void addLosePanel();
    
    public void removeLosePanel();
   
    public void addWinPanel();
    
    public void removeWinPanel();
    
    public void addCompletePanel();
    
    public void removeCompletePanel();

    public void setTextLabel(String s);
    
    public void selectBackground(int i);
    
    public void selectBall(int i);
}
