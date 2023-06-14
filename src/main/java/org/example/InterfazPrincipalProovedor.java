package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                double precio = Double.parseDouble(textFieldPrecio.getText());
                int referencia = Integer.parseInt(textFieldReferencia.getText());

                // Llamar al método agregarProducto de la clase Productos
                Productos.agregarProducto(stock, marca, precio, referencia);

                // Actualizar la visualización de los productos (opcional)
                actualizarProductos();
            }
        });

        // Obtener los datos de los productos desde la base de datos
        String[][] productos = obtenerProductosDesdeBaseDeDatos();

        // Crear un modelo de tabla para almacenar los datos de los productos
        String[] columnas = {"ID", "Nombre", "Precio"};
        DefaultTableModel model = new DefaultTableModel(productos, columnas);

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

    private void actualizarProductos() {
        // Obtener los datos de los productos desde la base de datos
        String[][] productos = obtenerProductosDesdeBaseDeDatos();

        // Obtener el panelTabla desde el panel mostrarProductos
        JPanel panelTabla = (JPanel) mostrarProductos.getComponent(0);

        // Obtener la tabla existente desde el panelTabla
        JTable tablaProductos = (JTable) ((JScrollPane) panelTabla.getComponent(1)).getViewport().getView();

        // Actualizar el modelo de la tabla existente con los nuevos datos
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        model.setDataVector(productos, new String[]{"ID", "Nombre", "Precio"});

        // Actualizar la visualización
        revalidate();
        repaint();
    }

    static String[][] obtenerProductosDesdeBaseDeDatos() {
        String[][] productos = null;

        try {
            // Crear la consulta SQL para obtener los productos
            PreparedStatement stm = Principal.getC().prepareStatement("select * from Productos");
            stm.execute();
            ResultSet resultSet = stm.getResultSet();

            // Obtener el número de columnas del ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnas = metaData.getColumnCount();

            // Crear una lista para almacenar los datos de los productos
            List<String[]> listaProductos = new ArrayList<>();

            // Recorrer el ResultSet y guardar los datos en la lista
            while (resultSet.next()) {
                String[] producto = new String[columnas];
                for (int columna = 0; columna < columnas; columna++) {
                    producto[columna] = resultSet.getString(columna + 1);
                }
                listaProductos.add(producto);
            }

            // Convertir la lista a una matriz
            productos = new String[listaProductos.size()][columnas];
            for (int i = 0; i < listaProductos.size(); i++) {
                productos[i] = listaProductos.get(i);
            }

            // Cerrar el ResultSet y el Statement
            resultSet.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener los productos desde la base de datos.", e);
        }

        return productos;
    }
}
