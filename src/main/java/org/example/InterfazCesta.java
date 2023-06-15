package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazCesta extends JFrame {
    private JPanel panelPrincipal;
    private JButton comprarCestaButton;
    private JButton volver;


    public InterfazCesta(){
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        comprarCestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IniciarSesion.getUsuario().comprarCesta(IniciarSesion.getUsuario().getId());

            }
        });
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaPrincipalUsuario().setVisible(true);
                dispose();
            }
        });
    }


}
