package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ajustes extends JFrame{
    private JPanel panelPrincipal;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField textField1;
    private JTextField textField2;
    private JCheckBox cambiarContraseñaCheckBox;
    private JLabel antContraseñaText;
    private JLabel nuevContraseñaText;
    private JTextField textField3;


    public Ajustes(){
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    cambiarContraseñaCheckBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cambiarContraseñaCheckBox.isSelected()){
                antContraseñaText.setEnabled(true);
                nuevContraseñaText.setEnabled(true);
                passwordField1.setEnabled(true);
                passwordField2.setEnabled(true);
            }else {
                antContraseñaText.setEnabled(false);
                antContraseñaText.setEnabled(false);
                passwordField1.setEnabled(false);
                passwordField2.setEnabled(false);
            }
        }
    });
}
}
