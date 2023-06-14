package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IniciarSesionProveedor extends JFrame{
    private JTextField email;
    private JPasswordField contraseña;
    private JButton volverAtrasButton;
    private JButton iniciarButton;
    private JPanel panelPrincipal;
    private static Proveedor proveedor1;

    public IniciarSesionProveedor(){
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        proveedor1=new Proveedor();

        //volver atras
        volverAtrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IniciarSesion().setVisible(true);
                dispose();
            }
        });

        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = email.getText();
                String contrasena = new String(contraseña.getPassword());
                proveedor1.iniciarSesion(correo,contrasena);
                System.out.println(proveedor1.getId());
                if (proveedor1.getId()!=0) {
                    new PantallaPrincipalUsuario().setVisible(true);
                }
                dispose();
            }
        });
    }


}
