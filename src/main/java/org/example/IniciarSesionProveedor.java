package org.example;

import javax.swing.*;

public class IniciarSesionProveedor extends JFrame{
    private JTextField correo;
    private JPasswordField passwordField1;
    private JButton volverAtrasButton;
    private JButton iniciarButton;
    private JPanel panelPrincipal;

    public IniciarSesionProveedor(){
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
