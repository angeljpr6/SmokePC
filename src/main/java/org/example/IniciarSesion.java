package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IniciarSesion extends JFrame {

    private JTextField correoElectronico;
    private JPasswordField contraseña;
    private JButton iniciarButton;
    private JButton crearCuentaButton;
    private JButton esProveedorButton;
    private JPanel panelPrincipal;

    private static Usuario usuario;
    public IniciarSesion(){
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Principal.initConection();
        usuario = new Usuario();
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = correoElectronico.getText();
                String contrasena = new String(contraseña.getPassword());
                usuario.iniciarSesion(email,contrasena);
                System.out.println(usuario.getId());
                if (usuario.getId()!=0) {
                    new PantallaPrincipalUsuario().setVisible(true);
                }
            }
        });

        esProveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Esto de abajo lo necesito cuando se ejecute InterfazPrincipal Proovedore
             // Principal.initConection();
                new IniciarSesionProveedor().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IniciarSesion().setVisible(true);

            }
        });
    }
}