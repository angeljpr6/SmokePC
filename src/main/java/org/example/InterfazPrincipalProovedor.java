package org.example;
import javax.swing.*;
import java.awt.*;

import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InterfazPrincipalProovedor extends JFrame {

    private JPanel mostrarProductos;
    private JPanel panel1;
    private JScrollBar scrollBar1;
    private JButton agregarProductosButton;

    private static Connection c;

    public InterfazPrincipalProovedor()  {
        this.setContentPane(panel1);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mostrarProductos.setLayout(new BorderLayout());

        // Obtener los datos de los productos desde la base de datos
        String[][] productos = obtenerProductosDesdeBaseDeDatos();

        // Crear un modelo de tabla para almacenar los datos de los productos
        String[] columnas = {"ID", "Nombre", "Precio"};
        DefaultTableModel model = new DefaultTableModel(productos, columnas);

        // Crear la tabla utilizando el modelo de tabla
        JTable tablaProductos = new JTable(model);

        // Establecer la tabla dentro del JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaProductos);

        // Agregar el JScrollPane al panel mostrarProductos en la posición deseada (centro)
        mostrarProductos.add(scrollPane, BorderLayout.CENTER);

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
