package org.example;

import javax.swing.*;
import java.awt.*;

public class InciarSesion extends JFrame {

    private JTextField correoElectronico;
    private JPasswordField contraseña;
    private JButton iniciarButton;
    private JButton crearCuentaButton;
    private JButton esProveedorButton;
    private JPanel panelPrincipal;
    public InciarSesion(){
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
