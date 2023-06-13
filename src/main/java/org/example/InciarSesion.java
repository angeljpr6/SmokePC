package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InciarSesion extends JFrame {

    private JTextField correoElectronico;
    private JPasswordField contraseña;
    private JButton iniciarButton;
    private JButton crearCuentaButton;
    private JButton esProveedorButton;
    private JPanel panelPrincipal;

    private Usuario usuario;
    public InciarSesion(){
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        usuario = new Usuario();
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = correoElectronico.getText();
                String contrasena = new String(contraseña.getPassword());
                usuario.iniciarSesion(contrasena, email);
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InciarSesion().setVisible(true);

            }
        });
    }





}