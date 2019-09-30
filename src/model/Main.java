/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.View;
import controller.Controller;
/**
 *
 * @author Mehdi
 */
public class Main {
    
    public static void main(String[] args) {
        Main.startBricksDestroyer();
    }
    
    private static void startBricksDestroyer(){
        IO.getInstance().saveDirectory();
        IO.getInstance().createSaveFile();
        View.getInstance().openStartWindow();
        Controller.getInstance().playSoundTrack();
    }
}
