/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.io.FileNotFoundException;

import javax.swing.GroupLayout;;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;

import controller.Controller;

/**
 *
 * @author Mehdi
 */
public class LevelSelectionWindow extends JFrame{
    
    //-----------------
    private JButton backButton;
    private JLabel titleLabel;
    private JButton[] jbutLevel = new JButton[30];
        
    //-----------------
    public LevelSelectionWindow(){
        super("Seleziona il livello..");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);  
        Container contPane = this.getContentPane();
	GroupLayout layout = new GroupLayout(contPane);
	contPane.setLayout(layout);
                
        Dimension d = new Dimension(1000, 600);
        this.setSize(d);
        this.setLocationRelativeTo(null);
        this.backButton = new JButton("Indietro");
        this.backButton.setFocusable(false);
	this.backButton.setBorderPainted(false);
        this.backButton.setFocusPainted(false);
        this.titleLabel = new JLabel("Seleziona il livello..", null, JLabel.CENTER);
        this.titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
	this.backButton.addActionListener(new ActionListener(){	
            @Override
            public void actionPerformed(ActionEvent e){
                handleBackEvent();
            }
	});

        for (int i = 0; i < 30; i++){
            this.jbutLevel[i] = new JButton(String.valueOf(i+1));
            this.jbutLevel[i].setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            this.jbutLevel[i].setFocusable(false);
            this.jbutLevel[i].setBorderPainted(false);
            this.jbutLevel[i].setFocusPainted(false);
            if( i >= Controller.getInstance().getMaxLevelCompeted())
                this.jbutLevel[i].setEnabled(false);
	}
        
        jbutLevel[0].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(1);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});	
        jbutLevel[1].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(2);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[2].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(3);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
		}});
        jbutLevel[3].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(4);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[4].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(5);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[5].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(6);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[6].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(7);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[7].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(8);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[8].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(9);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[9].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(10);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[10].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(11);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[11].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(12);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[12].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(13);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[13].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(14);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[14].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(15);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[15].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(16);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[16].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(17);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[17].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(18);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[18].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(19);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[19].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(20);
                } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[20].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(21);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }});
        jbutLevel[21].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(22);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }});
        jbutLevel[22].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
                try {
                    handleGameStartEvent(23);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }});
        jbutLevel[23].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(24);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[24].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(25);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[25].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(26);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[26].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(27);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[27].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(28);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[28].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(29);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});
        jbutLevel[29].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
            try {
                handleGameStartEvent(30);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }});            
        
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);		
                layout.setHorizontalGroup(    
                layout.createSequentialGroup()
                    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)     
                                .addComponent(titleLabel, 200, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    		.addGroup(layout.createSequentialGroup()
	    			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(jbutLevel[0], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jbutLevel[5], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[10], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[15], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[20], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[25], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))                
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(jbutLevel[1], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jbutLevel[6], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[11], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[16], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[21], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[26], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)) 
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                .addComponent(jbutLevel[2], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jbutLevel[7], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[12], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[17], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[22], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[27], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)) 
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                .addComponent(jbutLevel[3], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jbutLevel[8], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[13], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[18], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[23], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[28], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)) 
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                .addComponent(jbutLevel[4], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jbutLevel[9], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[14], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[19], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[24], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbutLevel[29], 80, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)) 
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(backButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 200))
		);
                layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(titleLabel)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jbutLevel[0],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[1],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[2],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[3],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbutLevel[4],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jbutLevel[5],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[6],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[7],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[8],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbutLevel[9],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jbutLevel[10],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[11],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[12],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[13],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbutLevel[14],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jbutLevel[15],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[16],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[17],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[18],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbutLevel[19],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jbutLevel[20],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[21],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[22],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[23],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbutLevel[24],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jbutLevel[25],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[26],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[27],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jbutLevel[28],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbutLevel[29],40, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(backButton))
		);
    };//fine costruttore
    
    //-----------------
    private void handleGameStartEvent(int level) throws FileNotFoundException, IOException {
        this.setVisible(false);
        View.getInstance().closeLevelSelectionWindow();
        Controller.getInstance().startNewGame(level);
    }
	
    private void handleBackEvent() {		
        this.setVisible(false); 
        View.getInstance().openStartWindow();
        View.getInstance().closeLevelSelectionWindow();		
    }

}// end class