package org.example;

import java.sql.*;
public class Productos {
    private int stock;
    private String marca;
    private double precio;
    private int referencia;
    private static Connection c = Principal.getC();

    public Productos(int stock, String marca, double precio, int referencia) {
        PreparedStatement stm = null;
        try {
            stm = c.prepareStatement("SELECT * FROM Productos WHERE referencia = ?");
            stm.setInt(1, this.referencia);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                this.stock = result.getInt("stock");
                this.precio = result.getDouble("precio");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar la base de datos: " + e.getMessage());
        }
        this.stock = stock;
        this.marca = marca;
        this.precio = precio;
        this.referencia = referencia;
    }
    public static void agregarProducto(int stock, String marca, double precio, int referencia) {
        try {
            PreparedStatement stm = c.prepareStatement("INSERT INTO Productos (stock, marca, precio, referencia) VALUES (?, ?, ?, ?)");
            stm.setInt(1, stock);
            stm.setString(2, marca);
            stm.setDouble(3, precio);
            stm.setInt(4, referencia);
            stm.executeUpdate();

            System.out.println("Producto agregado a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al agregar el producto a la base de datos: " + e.getMessage());
        }
    }
    public static void cambiarPrecio(int referencia, double nuevoPrecio) {
        try {
            PreparedStatement stm = c.prepareStatement("UPDATE Productos SET precio = ? WHERE referencia = ?");
            stm.setDouble(1, nuevoPrecio);
            stm.setInt(2, referencia);
            int affectedRows = stm.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No se encontró el producto con la referencia: " + referencia);
            } else {
                System.out.println("Precio actualizado correctamente para el producto con referencia: " + referencia);
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el precio en la base de datos: " + e.getMessage());
        }
    }
    public void aumentarStock(int referencia, int cantidad) {
        try {
            PreparedStatement stm = c.prepareStatement("UPDATE Productos SET stock = stock + ? WHERE referencia = ?");
            stm.setInt(1, cantidad);
            stm.setInt(2, referencia);
            int affectedRows = stm.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No se encontró el producto con la referencia: " + referencia);
            } else {
                System.out.println("Stock aumentado correctamente para el producto con referencia: " + referencia);
                this.stock += cantidad; // Actualizar el stock en el objeto actual
            }
        } catch (SQLException e) {
            System.out.println("Error al aumentar el stock en la base de datos: " + e.getMessage());
        }
    }

    public void disminuirStock(int referencia, int cantidad) {
        try {
            PreparedStatement stm = c.prepareStatement("UPDATE Productos SET stock = stock - ? WHERE referencia = ?");
            stm.setInt(1, cantidad);
            stm.setInt(2, referencia);
            int affectedRows = stm.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No se encontró el producto con la referencia: " + referencia);
            } else {
                System.out.println("Stock disminuido correctamente para el producto con referencia: " + referencia);
                this.stock -= cantidad; // Actualizar el stock en el objeto actual
            }
        } catch (SQLException e) {
            System.out.println("Error al disminuir el stock en la base de datos: " + e.getMessage());
        }
    }

    public int getStock() {
        return stock;
    }
}