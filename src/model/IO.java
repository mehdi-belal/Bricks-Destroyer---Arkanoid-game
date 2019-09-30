/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import java.awt.Image;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Mehdi
 */
public class IO {
   
    //-----------------
    private static Properties config = new Properties();
    private static Properties save = new Properties();
    private AudioInputStream audioInputStream;
    private File saveFile;
    
    private static IO io = null;

    //-----------------
    public static IO getInstance() {	
        if (io == null)	
            io = new IO();	
        return io;
    	}
    
    //-----------------   
    public void saveDirectory(){
        String homePath = System.getProperty("user.home");
        String directory = "Bricks Destroyer";
        File file = new File(homePath, directory);
        if(!file.exists()){
            boolean success = (file).mkdir();
            if (success)
                System.out.println("Creata la cartella di salvataggio");
            else
                System.out.println("Impossibile creare la cartella di salvataggio");
        }else
            System.out.println("La cartella di Salvataggio esiste");
	}

    public void createSaveFile(){
        File saveFile = new File(System.getProperty("user.home")+"\\Bricks Destroyer", "save.txt");
        if (saveFile.exists())
            System.out.println("Il File di salvataggio esiste");
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
                System.out.println("File di salvataggio creato");
                setMaxLevel();
                setBestScore();
            } catch (IOException ex) {
                saveDirectory();
            }
        }
    }
    
    public void deleteSaveFile(){
        File saveFile = new File(System.getProperty("user.home")+"\\Bricks Destroyer", "save.txt");
        if (!saveFile.exists())
            System.out.println("Il file salvataggio non esiste");
        if (saveFile.exists()){
                saveFile.delete();
                saveFile = null;
                System.out.println("File di salvataggio eliminiato");
        }
        createSaveFile();
        setMaxLevel();
        setBestScore();
    }
    
    public boolean isSavedGame(){
        boolean tmp = true;
        if(getPropertiesFromSave("ball_CoordX")==null) //se non è presente questa proprietà non è presente un salvataggio
            tmp = false;
        return tmp;
    }
    
    private static synchronized String getPropertiesFromConfig (String p){
        String result = "";
         try{
            config.load(new InputStreamReader(IO.class.getResourceAsStream("/data/config.txt")));
            result = config.getProperty(p);
        }
        catch(FileNotFoundException fnfe) {  
            fnfe.printStackTrace();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        } 
        return result;
    }
        
    public synchronized void saveProperties (String p, String value) {

        FileOutputStream dest;        
        try{ 
            //è necesario chiudere FileOutputStream altrimenti non è possibile eliminare il file di salvataggio dalle impostazioni
            dest = new FileOutputStream(System.getProperty("user.home")+"\\Bricks Destroyer\\save.txt", false);
            save.setProperty(p, value); 
            save.store(dest, "File di salvataggio - Bricks Destroyer");
            dest.close();
        }
        catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    private synchronized String getPropertiesFromSave(String p) {
        String result = "";
        try{
            InputStreamReader in;
            in = new InputStreamReader(new FileInputStream(System.getProperty("user.home")+"\\Bricks Destroyer\\save.txt"));
            save.load(in);
            result = save.getProperty(p);
            in.close();
        }
        catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
    
    public static String[] getBricksStatementsAtLevel(String i){  
        String s = IO.getPropertiesFromConfig(i);
        String[] element = s.split(",");
        return element;       
    }
    
    public static int getNumOfBricksAtLevel(String i){
        String s = IO.getPropertiesFromConfig(i);
        String[] element = s.split(",");
        int tmp = 0;
        for(int k = 0; k < element.length; k++)
            if(Integer.parseInt(element[k]) != 100 && Integer.parseInt(element[k])!= 0 && Integer.parseInt(element[k]) < 10){
                tmp = tmp + 1;
            }else if(Integer.parseInt(element[k]) != 100 && Integer.parseInt(element[k])!= 0 && Integer.parseInt(element[k]) > 10){
                tmp = tmp + Integer.parseInt(element[k]) - 10;
            }
        return tmp;
    }

    public void saveLevel(int level){
        saveProperties("current_Level", String.valueOf(level));
    }
    
    public String getLevelSaved() {
        return getPropertiesFromSave("current_Level");
    }

    public  void setMaxLevel(){
        if(getPropertiesFromSave("max_Level") == null){
            saveProperties("max_Level", String.valueOf(1));
        }else if(Model.getInstance().getLevel() > Integer.parseInt(getPropertiesFromSave("max_Level"))){
            saveProperties("max_Level", String.valueOf(Model.getInstance().getLevel())); 
        }
    }
    
    public  int getMaxLevelSaved(){
        if(getPropertiesFromSave("max_Level") != null)
            return Integer.parseInt(getPropertiesFromSave("max_Level"));
        else {
            setMaxLevel();
            return 1;
        }
    }
    
    public void setCoordXOfBall(){
        saveProperties("ball_CoordX", String.valueOf(Model.getInstance().getBall().getCoordXOfBall()));
    }
    
    public  String getCoordXOfBallSaved(){
        return getPropertiesFromSave("ball_CoordX");
    }
    
    public void setCoordYOfBall(){
        saveProperties("ball_CoordY", String.valueOf(Model.getInstance().getBall().getCoordYOfBall()));
    }
    
    public String getCoordYOfBallSaved(){
        return getPropertiesFromSave("ball_CoordY");
    }
    
    public void setDXOfBall(){
        saveProperties("ball_DX", String.valueOf(Model.getInstance().getBall().dX()));
    }
    
    public String getDXOfBallSaved(){
        return getPropertiesFromSave("ball_DX");
    }
    
    public void setDYOfBall(){
        saveProperties("ball_DY", String.valueOf(Model.getInstance().getBall().dY()));
    }
    
    public String getDYOfBallSaved(){
        return getPropertiesFromSave("ball_DY");
    }
    
    public void setSpeedOfBall(){
        saveProperties("ball_Speed", String.valueOf(Model.getInstance().getBall().getSpeed()));
    }
    
    public String getSpeedOfBallSaved(){
        return getPropertiesFromSave("ball_Speed");
    }
    
    public void setCoordXOfPaddle(){
        saveProperties("paddle_CoordX", String.valueOf(Model.getInstance().getPaddle().coordXOfPaddle()));
    }
    
    public String getCoordXOfPaddleSaved(){
        return getPropertiesFromSave("paddle_CoordX");
    }
    
    public void setScore(){
        saveProperties("score", String.valueOf(Model.getInstance().getScore()));
    }
    
    public int getScoreSaved(){
        return Integer.parseInt(getPropertiesFromSave("score"));
    }
    
    public void setLifes(){
        saveProperties("Lifes", String.valueOf(Model.getInstance().getLifes()));
    }
    
    public int getLifesSaved(){
        return Integer.parseInt(getPropertiesFromSave("Lifes"));
    }

    public void setBrickStatement(){
        String s = "";
        for(int i = 0; i < Model.MATRIX_ROWS; i++)
            for(int j = 0; j < Model.MATRIX_COLUMNS; j++){
                int k = Model.getInstance().getBrickMatrix()[i][j].getBrickStatement();
                s += String.valueOf(k) + ",";
            }
        saveProperties("bricks_Statement", s);
    }
    
    public String[] getBrickStatementSaved(){
       String s = getPropertiesFromSave("bricks_Statement");
       String[] element = s.split(",");      
       return element;  
    }
    
    public void setNumOfBricksSaved(){
        saveProperties("Num_Of_Bricks", String.valueOf(Model.getInstance().getNumOfBricks()));
    }
    
    public int getNumOfBricksSaved(){
         return Integer.parseInt(getPropertiesFromSave("Num_Of_Bricks"));
    }
    
    public void setBestScore(){
        if(getPropertiesFromSave("Best_Score") == null)
            saveProperties("Best_Score", String.valueOf(0));
        else if(Integer.parseInt(getPropertiesFromSave("Best_Score")) < Model.getInstance().getScore())
            saveProperties("Best_Score", String.valueOf(Model.getInstance().getScore()));
    }
    
    public int getBestScoreSaved(){
        if(getPropertiesFromSave("Best_Score") != null)
            return Integer.parseInt(getPropertiesFromSave("Best_Score"));
        else{
            setBestScore();
            return 0;
        }
        
    }
    
    
    //Metodo per il caricamento delle immagini
    public static Image loadImage(String name) {
        Image result = null;
        try {
            result = ImageIO.read(IO.class.getClassLoader().getResourceAsStream("data/" + name));
        } catch (Exception ex) {
        }      
        return result;
    }
    
    public Clip loadSoundTrack() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Clip soundTrack;
        try{
            audioInputStream = AudioSystem.getAudioInputStream(IO.class.getResource("/data/SoundTrack.wav"));
            soundTrack = AudioSystem.getClip();
            soundTrack.open(audioInputStream);
        }
        catch(FileNotFoundException  e){
            soundTrack=null;
        }
        return soundTrack;	
    }
    
    public Clip[] loadSoundEffect() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Clip[] soundEffect = new Clip[3];
        try{ 
            for(int i = 0; i < soundEffect.length; i++){
                audioInputStream = AudioSystem.getAudioInputStream(IO.class.getResource("/data/effect0"+String.valueOf(i+1)+".wav"));
                soundEffect[i] = AudioSystem.getClip(); 
                soundEffect[i].open(audioInputStream); 
            }
        }
        catch(FileNotFoundException ex){
            
        }
        return soundEffect;
    }
    
}//end class