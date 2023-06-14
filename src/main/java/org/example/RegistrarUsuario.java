package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarUsuario extends JFrame{
    private JPanel panelPrincipal;
    private JTextField correoElectronico;
    private JPasswordField contrasena1;
    private JPasswordField contrasena2;
    private JButton crearCuentaButton;
    private JCheckBox cuentaDeProveedorCheckBox;
    private JButton iniciarSesionButton;
    private JTextField nombreUsuario;
    private JTextField apellidosUsuario;

    public RegistrarUsuario(){
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        crearCuentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre=nombreUsuario.getText();
                String apellidos=apellidosUsuario.getText();
                String email= correoElectronico.getText();
                String contrasena= new String(contrasena1.getPassword());
                if (contrasena.equals(new String(contrasena2.getPassword()))){
                    IniciarSesion.getUsuario().registrarUsuario(nombre,apellidos,email,contrasena);
                    if (IniciarSesion.getUsuario().getId()!=0) {
                        new PantallaPrincipalUsuario().setVisible(true);
                    }
                }
            }
        });
    }
}
