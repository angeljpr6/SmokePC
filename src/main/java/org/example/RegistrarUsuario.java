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
    private JLabel errCompCam;
    private JLabel errCont;
    private static boolean botonProveedor=false;

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
                errCont.setVisible(false);
                if (nombre.length()<1 || apellidos.length()<1 || email.length()<1){
                    errCompCam.setVisible(true);
                }else {
                    errCompCam.setVisible(false);
                    String contrasena = new String(contrasena1.getPassword());
                    if (contrasena.equals(new String(contrasena2.getPassword())) && contrasena.length()>0) {
                        if (cuentaDeProveedorCheckBox.isSelected() == true) {
                            IniciarSesionProveedor.getProveedor1().registrarProveedor(email, contrasena);
                            new InterfazPrincipalProovedor().setVisible(true);
                            dispose();
                        } else {
                            IniciarSesion.getUsuario().registrarUsuario(nombre, apellidos, email, contrasena);
                            if (IniciarSesion.getUsuario().getId() != 0) {
                                new PantallaPrincipalUsuario().setVisible(true);
                                dispose();
                            }
                        }
                    } else errCont.setVisible(true);
                }
            }
        });

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IniciarSesion().setVisible(true);
                // Cerrar la ventana actual
                dispose();

            }
        });
        cuentaDeProveedorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
    }
}
