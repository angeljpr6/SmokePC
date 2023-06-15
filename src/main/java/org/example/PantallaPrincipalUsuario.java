package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class PantallaPrincipalUsuario extends JFrame{
    private JTextField textField1;
    private JButton verMasButton;
    private JLabel producto1;
    private JButton cestaButton;
    private JPanel panelPrincipal;
    private JButton ajustesButton;
    private JScrollBar scrollBar1;

    public PantallaPrincipalUsuario() {
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Te lleva a la cesta
        cestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new InterfazCesta().setVisible(true);
            }
        });
        ajustesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Ajustes().setVisible(true);
                dispose();
            }
        });
    }
}
