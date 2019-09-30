/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Mehdi
 */
public class StartWindow extends JFrame {
    
    //-----------------
    private JButton helpButton;
    private JLabel helpLabel;
    private JLabel titleLabel;
    private JButton loadGameButton;
    private JLabel loadGameLabel;
    private JButton newGameButton;
    private JLabel newGameLabel;
    private JButton settingButton;
    private JLabel settingLabel; 
    private JLabel bestScoreLabel;
    private SettingsDialog settingsDialog;
    private HelpDialog helpDialog;
    
    //-----------------
    public StartWindow(){
        super("Bricks Destroyer: Iniziamo...");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setLocation(300,100);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/data/startBackground.png")));
        this.setContentPane(background);
  
        setResizable(false);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        newGameButton = new JButton();
        settingButton = new JButton();
        loadGameButton = new JButton();
        helpButton = new JButton();
        newGameLabel = new JLabel();
        loadGameLabel = new JLabel();
        settingLabel = new JLabel();
        helpLabel = new JLabel();
        titleLabel = new JLabel();
        bestScoreLabel = new JLabel();
        
        newGameButton.setContentAreaFilled(false);
        loadGameButton.setContentAreaFilled(false);
        helpButton.setContentAreaFilled(false);
        settingButton.setContentAreaFilled(false);
        
        newGameButton.setIcon(new ImageIcon(getClass().getResource("/data/playIcon.png")));
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                handleNewGameEvent();
            }
        });

        settingButton.setIcon(new ImageIcon(getClass().getResource("/data/settingsIcon.png")));
        loadGameButton.setIcon(new ImageIcon(getClass().getResource("/data/saveIcon.png")));
        helpButton.setIcon(new ImageIcon(getClass().getResource("/data/helpIcon.png")));

        if(!Controller.getInstance().isSavedGame())
            loadGameButton.setEnabled(false);
        
        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                handleLoadGameEvent();
            }
        });
        
        settingButton.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
		if (settingsDialog == null){
                    settingsDialog = new SettingsDialog(null, true);
                }
                
		settingsDialog.setVisible(true);
            }
	});
            }
        });
        
        helpButton.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
		if (helpDialog == null){
                    helpDialog = new HelpDialog(null, true);
                }
		helpDialog.setVisible(true);
                
            }
	});
            }
        });

        newGameLabel.setText("Inizia una nuova partita");
        loadGameLabel.setText("Carica partita salvata");
        settingLabel.setText("Impostazioni");
        helpLabel.setText("Istruzioni");
        titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/title1.png"))); // NOI18N
         
        bestScoreLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18));
        bestScoreLabel.setText("Punteggio Migliore: " + Controller.getInstance().getBestScore() + " Pt.");
       

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(loadGameLabel)
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(newGameLabel)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loadGameButton)
                            .addComponent(newGameButton))
                        .addGap(156, 156, 156)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(helpLabel)
                            .addComponent(settingLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(settingButton)
                            .addComponent(helpButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bestScoreLabel)
                            .addComponent(titleLabel))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(settingButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(newGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(newGameLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(settingLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loadGameButton)
                            .addComponent(helpButton))
                        .addGap(95, 95, 95))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(helpLabel)
                        .addGap(122, 122, 122))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(loadGameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bestScoreLabel)
                        .addContainerGap())))
        );
        
        pack();
    }
    
    //-----------------
    public void handleNewGameEvent(){
        View.getInstance().openLevelSelectionWindow();
        View.getInstance().closeStartWindow();
    }
    
    public void handleLoadGameEvent(){
        this.setVisible(false);
        Controller.getInstance().startSavedGame();
    }
    
  
    //---------------------------------------------------
    //SOTTOCLASSI
    //---------------------------------------------------

    class SettingsDialog extends JDialog{
        
        //-----------------
        private JButton deleteButton;
        private JLabel deleteLabel;
        private JLabel musicLabel;
        private JLabel effectLabel;
        private JSlider musicSlider;
        private JSlider effectSlider;
    
        private JButton[] back = new JButton[4];
        private JButton[] ball = new JButton[5];
       
        //-----------------
        public SettingsDialog(JFrame parent, boolean modal){     
            super(parent, modal);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setLocation(400,150);

            deleteButton = new JButton();
            deleteLabel = new JLabel();
            musicSlider = new JSlider(0,100,Controller.getInstance().getVolumeSoundTrack());
            musicLabel = new JLabel();
            effectSlider = new JSlider(0,100,Controller.getInstance().getVolumeSoundEffect());
            effectLabel = new JLabel(); 
        
       
            for(int i = 0; i < back.length; i++)
                back[i] = new JButton(new ImageIcon(getClass().getResource("/data/i"+String.valueOf(i+1)+".png")));

            for(int i = 0; i < ball.length; i++)
                ball[i] = new JButton(new ImageIcon(getClass().getResource("/data/a"+String.valueOf(i+1)+".png")));

            back[0].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        View.getInstance().selectBackground(1);
                    }
                });
            back[1].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        View.getInstance().selectBackground(2);
                    }
                });
            back[2].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        View.getInstance().selectBackground(3);
                    }
                });
            back[3].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        View.getInstance().selectBackground(4);
                    }
                });

            ball[0].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        View.getInstance().selectBall(1);
                    }
                });

            ball[1].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        View.getInstance().selectBall(2);
                    }
                });

            ball[2].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        View.getInstance().selectBall(3);
                    }
                });

            ball[3].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        View.getInstance().selectBall(4);
                    }
                });

            ball[4].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        View.getInstance().selectBall(5);
                    }
                });

            deleteButton.setText("Elimina");
             deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    Controller.getInstance().deleteSave();
                    loadGameButton.setEnabled(false);
                    bestScoreLabel.setText("Punteggio Migliore: 0 Pt.");
                }
            });
            deleteLabel.setText("Elimina tutti i progressi di gioco");
            musicLabel.setText("Volume musica");
            effectLabel.setText("Volume effetti sonori");

            this.musicSlider.addChangeListener(new ChangeListener(){
                    public void stateChanged(ChangeEvent e) {
                            JSlider source = (JSlider)e.getSource();
                            if (source.getValueIsAdjusting()) {
                                    int vol = (int)source.getValue();
                                    Controller.getInstance().setVolumeSoundTrack(vol);
                                    System.out.println("Volume musica: "+vol);
                            }    
                        }
            });
            this.effectSlider.addChangeListener(new ChangeListener(){
                    public void stateChanged(ChangeEvent e) {
                            JSlider source = (JSlider)e.getSource();
                            if (source.getValueIsAdjusting()) {
                                    int vol = (int)source.getValue();
                                    Controller.getInstance().setVolumeSoundEffect(vol);
                                    System.out.println("Volume effetti sonori: "+vol);
                            }    
                        }
            });

            this.musicSlider.setMajorTickSpacing(50);	
            this.musicSlider.setMinorTickSpacing(10);	
            this.musicSlider.setPaintTicks(true); //pone i tick
            this.musicSlider.setPaintLabels(true); //numeri sotto tick

            this.effectSlider.setMajorTickSpacing(50);	
            this.effectSlider.setMinorTickSpacing(10);	
            this.effectSlider.setPaintTicks(true); //pone i tick
            this.effectSlider.setPaintLabels(true); //numeri sotto tick

        Container contPane = this.getContentPane();
            JPanel p1 = new JPanel();
            JPanel p2 = new JPanel();
            JPanel p3 = new JPanel();
            JPanel p4 = new JPanel();
            JPanel p5 = new JPanel();
            p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
            p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
            p3.setLayout(new BoxLayout(p3, BoxLayout.LINE_AXIS));
            p4.setLayout(new BoxLayout(p4, BoxLayout.LINE_AXIS));
            p5.setLayout(new BoxLayout(p5, BoxLayout.LINE_AXIS));
            p1.add(deleteLabel);
            p1.add(Box.createHorizontalGlue());
            p1.add(deleteButton);

            p2.add(musicLabel);
            p2.add(Box.createHorizontalGlue());
            p2.add(musicSlider);

            p3.add(effectLabel);
            p3.add(Box.createHorizontalGlue());
            p3.add(effectSlider);

            for (int j = 0; j <back.length; j++){
                p4.add(Box.createRigidArea(new Dimension(10, 0)));
                p4.add(back[j]);
            }

            for (int j = 0; j <ball.length; j++){
                p5.add(Box.createRigidArea(new Dimension(10, 0)));
                p5.add(ball[j]);
            }

            contPane.setLayout(new BoxLayout(contPane, BoxLayout.PAGE_AXIS));
            contPane.add(p1);
            contPane.add(p2);
            contPane.add(p3);
            contPane.add(new JLabel("Seleziona lo sfondo"));
            contPane.add(p4);
            contPane.add(new JLabel("Seleziona la palla"));
            contPane.add(p5);
            pack(); 
        }
    }
    
    
    class HelpDialog extends JDialog{
        
        //-----------------
        private JLabel gameLabel = new JLabel();
        private JLabel settingLabel = new JLabel();
        private JLabel pointLabel = new JLabel(new ImageIcon(getClass().getResource("/data/img1.png")));
        private JLabel controlLabel = new JLabel(new ImageIcon(getClass().getResource("/data/img2.png")));
        private JLabel titleSettingLabel = new JLabel();
        private JLabel titleGameLabel = new JLabel();
        private JButton engButton = new JButton(new ImageIcon(getClass().getResource("/data/United-Kingdom.png")));
        private JButton itaButton = new JButton(new ImageIcon(getClass().getResource("/data/Italy.png")));
        private JPanel contentPane = new JPanel();
        private JScrollPane scrollPane = new JScrollPane();
        
        //-----------------
        public HelpDialog(JFrame parent, boolean modal) {
            super(parent, modal);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            this.itaButton.setContentAreaFilled(false);
            this.engButton.setContentAreaFilled(false);
            
            this.setLocation(310,110);
            this.setPreferredSize(new Dimension(800,600));
            titleGameLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
            titleGameLabel.setText("GIOCO:");

            gameLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
            gameLabel.setText("<html>Lo scopo del gioco è quello di usare la pallina per distruggere i mattoncini sullo<BR>schermo. Evita di far cadere la pallina pena la perdita di una vita, se perdi tutte le<BR>tue vite hai perso! Per muovere il Paddle scorri il mouse sullo schermo, seguirà il<BR>tuo movimento. Guadagni punti per ogni mattoncino distrutto, i mattoncini colorati<BR>ti fanno guadagnare 10 punti, vengono distrutti non appena li colpisci, Mentre i<BR>mattoncini a forma di muro devono essere colpiti 3 volte per essere distrutti,<BR>e ti fanno guadagnare 100 punti!<BR>Ricordati che la velocità della palllina aumenta sempre di più!</html>");

            titleSettingLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 30));
            titleSettingLabel.setText("IMPOSTAZIONI:");
        
            settingLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 20));
            settingLabel.setText("<html>Premi il tasto \"A\" per far comparire/scomparire le informazioni dal pannello di<BR>gioco.<BR>Premi \"Spazio\" per mettere in pausa/riprendere il gioco o per iniziare un livello.<BR>Puoi decidere di salvare la tua partita per riprenderla in un secondo momento,<BR>ricordati che ogni salvataggio sovrascrive il precedente!<BR>Puoi decidere di eliminare tutti i tuoi progressi (il livello salvato e il punteggio<BR>migliore) dalla schermata principale >> Impostazioni >> Elimina Progressi</html>");

            engButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    titleGameLabel.setText("GAME:");
                    gameLabel.setText("<html>Your goal is to destroy the bricks on the screen using the ball. Try to avoid<BR>dropping the ball at the bottom or risk losing a life, if you lose all your lives<BR>you've lost! To move the Paddle roll your mouse on the screen, it follows your<BR>movement. You earn points for every brick destroyed, the colored bricks<BR>make you gain 10 points,they are destroyed as soon as you hit them, while the<BR>bricks in the shape of the wall must be hit three times to be destroyed, and make<BR>you earn 100 points!<BR>Remember that the speed of the ball increases more and more!</html>");
                    titleSettingLabel.setText("SETTINGS:");
                    settingLabel.setText("<html>Press the \"A\" button to display/disappear the information from the board.<BR>Press \"Space\" to pause/resume play, or to start a level.<BR>You can choose to save your game and resume it later, remember that each<BR>save overwrites the previous!<BR>You can choose to delete all your progress (the saved level, and the highest<BR>score) from the home screen >> Settings >> Delete Progressi</html>");
                }
            });
        
            itaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                titleGameLabel.setText("GIOCO:");
                gameLabel.setText("<html>Lo scopo del gioco è quello di usare la pallina per distruggere i mattoncini sullo<BR>schermo. Evita di far cadere la pallina pena la perdita di una vita, se perdi tutte le<BR>tue vite hai perso! Per muovere il Paddle scorri il mouse sullo schermo, seguirà il<BR>tuo movimento. Guadagni punti per ogni mattoncino distrutto, i mattoncini colorati<BR>ti fanno guadagnare 10 punti, vengono distrutti non appena li colpisci, Mentre i<BR>mattoncini a forma di muro devono essere colpiti 3 volte per essere distrutti,<BR>e ti fanno guadagnare 100 punti!<BR>Ricordati che la velocità della palllina aumenta sempre di più!</html>");
                titleSettingLabel.setText("IMPOSTAZIONI:");
                 settingLabel.setText("<html>Premi il tasto \"A\" per far comparire/scomparire le informazioni dal pannello di<BR>gioco.<BR>Premi \"Spazio\" per mettere in pausa/riprendere il gioco o per iniziare un livello.<BR>Puoi decidere di salvare la tua partita per riprenderla in un secondo momento,<BR>ricordati che ogni salvataggio sovrascrive il precedente!<BR>Puoi decidere di eliminare tutti i tuoi progressi (il livello salvato e il punteggio<BR>migliore) dalla schermata principale >> Impostazioni >> Elimina Progressi</html>");
                }
        
            });
       
            contentPane.setBackground(new java.awt.Color(153, 204, 255));
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(new java.awt.Color(153, 204, 255));
            buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
            buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            buttonPane.add(Box.createHorizontalGlue());
            buttonPane.add(engButton);
            buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
            buttonPane.add(itaButton);
            contentPane.add(buttonPane, BorderLayout.PAGE_END); 
            contentPane.add(titleGameLabel);
            titleGameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            contentPane.add(gameLabel);
            gameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
            contentPane.add(pointLabel);
            contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
            contentPane.add(titleSettingLabel);
            contentPane.add(settingLabel);
            contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
            contentPane.add(controlLabel);
            buttonPane.setAlignmentX(Component.LEFT_ALIGNMENT);    
            
            scrollPane.setViewportView(contentPane);
            this.add(scrollPane);
            pack();
        }
    }
}//end class
