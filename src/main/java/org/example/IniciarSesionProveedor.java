package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IniciarSesionProveedor extends JFrame{
    private JTextField correo;
    private JPasswordField passwordField1;
    private JButton volverAtrasButton;
    private JButton iniciarButton;
    private JPanel panelPrincipal;

    public IniciarSesionProveedor() {
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        volverAtrasButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
