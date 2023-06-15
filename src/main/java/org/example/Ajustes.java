package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ajustes extends JFrame{
    private JPanel panelPrincipal;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField nuevoApellido;
    private JTextField nuevoNombre;
    private JCheckBox cambiarContraseñaCheckBox;
    private JLabel antContraseñaText;
    private JLabel nuevContraseñaText;
    private JTextField nuevoCorreo;
    private JButton guardarCambiosButton;
    private JButton atrasButton;
    private JButton guardarCambiosButton1;
    private JLabel errorContrasenaInvalida;
    private JLabel contrasenaCambiada;
    private JLabel datosCambiados;
    private static Connection c=Principal.getC();


    public Ajustes(){
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println(panelPrincipal.getWidth()+" "+panelPrincipal.getHeight());
    cambiarContraseñaCheckBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cambiarContraseñaCheckBox.isSelected()){
                antContraseñaText.setEnabled(true);
                nuevContraseñaText.setEnabled(true);
                passwordField1.setEnabled(true);
                passwordField2.setEnabled(true);
                guardarCambiosButton1.setEnabled(true);
            }else {
                antContraseñaText.setEnabled(false);
                antContraseñaText.setEnabled(false);
                passwordField1.setEnabled(false);
                passwordField2.setEnabled(false);
                guardarCambiosButton1.setEnabled(false);
            }
        }
    });
        guardarCambiosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nuevoApellido.getText().length()>0){
                    IniciarSesion.getUsuario().cambiarApellidos(nuevoApellido.getText());
                    datosCambiados.setVisible(true);
                }
                if (nuevoNombre.getText().length()>0){
                    IniciarSesion.getUsuario().cambiarNombre(nuevoNombre.getText());
                    datosCambiados.setVisible(true);
                }
                if (nuevoCorreo.getText().length()>0){
                    IniciarSesion.getUsuario().cambiarEmail(nuevoCorreo.getText());
                    datosCambiados.setVisible(true);
                }
            }
        });
        guardarCambiosButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String antiguaContra=new String(passwordField1.getPassword());
                String nuevaContra=new String(passwordField2.getPassword());
                if (antiguaContra.equals(IniciarSesion.getUsuario().getContrasena())){
                    try {
                        PreparedStatement stm = c.prepareStatement("update usuario set contrasena=? where id=?");
                        stm.setString(1,nuevaContra);
                        stm.setInt(2,IniciarSesion.getUsuario().getId());
                        stm.execute();
                        errorContrasenaInvalida.setVisible(false);
                        contrasenaCambiada.setVisible(true);

                    } catch (SQLException i) {
                        throw new RuntimeException(i);
                    }
                }else errorContrasenaInvalida.setVisible(true);
            }
        });
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaPrincipalUsuario().setVisible(true);
                dispose();
            }
        });
    }
}
