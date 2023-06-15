package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipalUsuario extends JFrame{
    private JTextField textField1;
    private JButton verMasButton;
    private JButton cestaButton;
    private JPanel panelPrincipal;
    private JButton ajustesButton;
    private JLabel nombre1;
    private JLabel marca1;
    private JLabel precio1;
    private JLabel nombre2;
    private JLabel marca2;
    private JLabel precio2;
    private JLabel nombre3;
    private JLabel marca3;
    private JLabel precio3;
    private JLabel nombre4;
    private JLabel marca4;
    private JLabel precio4;
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
