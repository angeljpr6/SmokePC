package org.example;

import java.sql.*;
public class Productos {
    private String nombre;
    private int stock;
    private String marca;
    private float precio;
    private int referencia;
    private static Connection c = Principal.getC();

    public void crearProducto(String nombre, String marca, float precio, int referencia){
        this.nombre=nombre;
        this.marca=marca;
        this.precio=precio;
        this.referencia=referencia;
    }

    public Productos(int stock, String marca, float precio, int referencia) {
        PreparedStatement stm = null;
        try {
            stm = c.prepareStatement("SELECT * FROM Productos WHERE referencia = ?");
            stm.setInt(1, this.referencia);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                this.stock = result.getInt("stock");
                this.precio = result.getFloat("precio");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar la base de datos: " + e.getMessage());
        }
        this.stock = stock;
        this.marca = marca;
        this.precio = precio;
        this.referencia = referencia;
    }

    public Productos(){
        this.marca="";
        this.nombre="";
        this.precio=0;
        this.referencia=0;
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

    public static Productos[] mostrarProductosIniciales(){
        Productos[] productos=new Productos[4];
        int cont=0;
        try {

            PreparedStatement stm = c.prepareStatement("select * from productos limit 4;");
            ResultSet result = stm.executeQuery();
            while (result.next()){
                Productos p1=new Productos();
                p1.crearProducto(result.getString("nombre"), result.getString("marca"), result.getFloat("precio"), result.getInt("referencia"));
                productos[cont]=p1;
                cont++;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productos;
    }

    public int getStock() {
        return stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }
}