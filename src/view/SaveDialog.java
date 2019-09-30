/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
/**
 *
 * @author Mehdi
 */
public class SaveDialog extends JDialog{
    
    //-----------------
    private JButton yesButton;
    private JButton noButton;
    private JLabel label;
    
    //-----------------
    public SaveDialog(Frame parent, boolean modal){
        super(parent, modal);
        yesButton = new JButton();
        noButton = new JButton();
        label = new JLabel();

        this.setLocation(420,330);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        yesButton.setText("Si");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Controller.getInstance().handleSaveEvent();
                View.getInstance().removePausePanel();
            }
        });

        noButton.setText("No");
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Controller.getInstance().handleNotSaveEvent(); 
                View.getInstance().removePausePanel();
            }
        });
        label.setText("Desideri salvare il gioco?");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(yesButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(noButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label)
                .addGap(163, 163, 163))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(label)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(yesButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addComponent(noButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        pack();
    } 
    
}
