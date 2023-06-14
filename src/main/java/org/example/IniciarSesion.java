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

    private Usuario usuario;
    public IniciarSesion(){
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
        esProveedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Esto de abajo lo necesito cuando se ejecute InterfazPrincipal Proovedor
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