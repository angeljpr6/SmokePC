package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static org.example.Proveedor.obtenerProductosDesdeBaseDeDatos;

public class InterfazPrincipalProovedor extends JFrame {

    private JPanel mostrarProductos;
    private JPanel panel1;
    private JScrollBar scrollBar1;
    private JButton agregarProductosButton;
    private JTextField textFieldStock;
    private JTextField textFieldMarca;
    private JTextField textFieldPrecio;
    private JTextField textFieldReferencia;
    private JTextField textFieldElimina;
    private JButton eliminar;
    private JButton Retornar;
    private JLabel errorElimProd;
    private JLabel prodEliminado;


    private static Connection c;

    public InterfazPrincipalProovedor() {
        this.setContentPane(panel1);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        agregarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos del producto desde la interfaz (por ejemplo, mediante JTextField)
                int stock = Integer.parseInt(textFieldStock.getText());
                String marca = textFieldMarca.getText();
                float precio = Float.parseFloat(textFieldPrecio.getText());
                String nombre = textFieldReferencia.getText();

                // Llamar al método agregarProducto de la clase Productos
                IniciarSesionProveedor.getProveedor1().añadirProducto(stock, marca, precio, nombre);

                // Actualizar la visualización de los productos (No funciona)
                InterfazPrincipalProovedor ventana = new InterfazPrincipalProovedor();
                ventana.setExtendedState(NORMAL);
                ventana.setVisible(true);

                // Cerrar la ventana actual
                dispose();
            }
        });
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Llamar al método agregarProducto de la clase Productos

                int elimina = Integer.parseInt(textFieldElimina.getText());
                boolean eliminado=IniciarSesionProveedor.getProveedor1().eliminarProducto(elimina);
                if (eliminado){
                    InterfazPrincipalProovedor ventana = new InterfazPrincipalProovedor();
                    ventana.setExtendedState(NORMAL);
                    ventana.setVisible(true);
                    dispose();
                    errorElimProd.setVisible(false);
                    prodEliminado.setVisible(true);
                }else {
                    errorElimProd.setVisible(true);
                    prodEliminado.setVisible(false);
                }

                // Actualizar la visualización de los productos (No funciona)


                // Cerrar la ventana actual

            }
        });

        Retornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new IniciarSesion().setVisible(true);
                // Cerrar la ventana actual
                dispose();
            }
        });
        // Obtener los datos de los productos desde la base de datos
        String[][] productos = obtenerProductosDesdeBaseDeDatos();

        // Crear un modelo de tabla para almacenar los datos de los productos
        String[] columnas = {"id","Precio", "Marca", "Nombre", "stock"};
        DefaultTableModel model = new DefaultTableModel(productos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla sea no editable
            }
        };

        // Crear la tabla utilizando el modelo de tabla
        JTable tablaProductos = new JTable(model);


        // Crear un panel para la tabla y establecer su diseño
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.add(tablaProductos.getTableHeader(), BorderLayout.NORTH);
        panelTabla.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        // Agregar el panelTabla al panel mostrarProductos en el centro
        mostrarProductos.setLayout(new BorderLayout());
        mostrarProductos.add(panelTabla, BorderLayout.CENTER);

    }




}