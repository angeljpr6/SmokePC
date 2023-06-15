package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                //actualizarProductos();
            }
        });

        // Obtener los datos de los productos desde la base de datos
        String[][] productos = obtenerProductosDesdeBaseDeDatos();

        // Crear un modelo de tabla para almacenar los datos de los productos
        String[] columnas = {"Precio", "Stock", "Marca", "Nombre"};
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

        // Agregar el JScrollBar al panel mostrarProductos en el lado derecho
        mostrarProductos.add(scrollBar1, BorderLayout.EAST);

        // Configurar el GridBagConstraints para el panel mostrarProductos en el panel1
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        // Agregar el panel mostrarProductos al panel1 utilizando GridBagLayout
        panel1.setLayout(new GridBagLayout());
        panel1.add(mostrarProductos, gbc);
    }

  /*
                ¡¡¡¡¡ WORK IN PROGRESS!!!!
     private void actualizarProductos() {
        // Obtener los datos de los productos desde la base de datos
        String[][] productos = obtenerProductosDesdeBaseDeDatos();

        // Obtener el panelTabla desde el panel mostrarProductos
        JPanel panelTabla = (JPanel) mostrarProductos.getComponent(0);

        // Obtener la tabla existente desde el panelTabla
        JTable tablaProductos = (JTable) ((JScrollPane) panelTabla.getComponent(1)).getViewport().getView();

        // Actualizar el modelo de la tabla existente con los nuevos datos
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        model.setDataVector(productos, new String[]{"Precio", "Nombre", "Marca", "Stock"});

        // Actualizar la visualización
        revalidate();
        repaint();
    } */


}