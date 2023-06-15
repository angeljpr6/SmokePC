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
    private JLabel correoContrasenaIncor;
    private static Proveedor proveedor1=new Proveedor();

    public IniciarSesionProveedor(){
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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
                    new InterfazPrincipalProovedor().setVisible(true);
                    dispose();
                }else correoContrasenaIncor.setVisible(true);

            }
        });
    }

    public static Proveedor getProveedor1() {
        return proveedor1;
    }

    public static void setProveedor1(Proveedor proveedor1) {
        IniciarSesionProveedor.proveedor1 = proveedor1;
    }
}
