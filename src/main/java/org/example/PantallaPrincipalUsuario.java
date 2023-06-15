package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipalUsuario extends JFrame{
    private JTextField textField1;
    private JButton agregarALaCestaButton;
    private JButton cestaButton;
    private JPanel panelPrincipal;
    private JButton ajustesButton;
    private JLabel nombre1;
    private JLabel marca1;
    private JLabel precio1;
    private JLabel nombre2;
    private JLabel marca2;
    private JLabel precio2;
    private JLabel nombre3;
    private JLabel marca3;
    private JLabel precio3;
    private JLabel nombre4;
    private JLabel marca4;
    private JLabel precio4;
    private JButton Cerrar;
    private JScrollBar scrollBar1;

    public PantallaPrincipalUsuario() {
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Productos[] productos=new Productos[4];
        productos=Productos.mostrarProductosIniciales();
        nombre1.setText(productos[0].getNombre());
        nombre2.setText(productos[1].getNombre());
        nombre3.setText(productos[2].getNombre());
        nombre4.setText(productos[3].getNombre());
        marca1.setText(productos[0].getMarca());
        marca2.setText(productos[1].getMarca());
        marca3.setText(productos[2].getMarca());
        marca4.setText(productos[3].getMarca());
        precio1.setText(String.valueOf(productos[0].getPrecio())+" €");
        precio2.setText(String.valueOf(productos[1].getPrecio())+" €");
        precio3.setText(String.valueOf(productos[2].getPrecio())+" €");
        precio4.setText(String.valueOf(productos[3].getPrecio())+" €");


        //Te lleva a la cesta
        cestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new InterfazCesta().setVisible(true);
            }
        });
        //Te lleva  de nuevo al menu principal
        Cerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new IniciarSesion().setVisible(true);
                // Cerrar la ventana actual
                dispose();
            }
        });
        ajustesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Ajustes().setVisible(true);
                dispose();
            }
        });
    }
}
