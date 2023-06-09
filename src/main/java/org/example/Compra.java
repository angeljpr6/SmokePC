package org.example;
import java.sql.*;
public class Compra {
    private String fecha;
    private String hora;
    private int tickect;
    private double precio;
    private static String[] estado = {"En tramitacion", "enviado", "rechazado", "llegando"};
    private static Connection c = Principal.getC();
    public static void cambiarEstado(int indiceEstado, int ticket) {
        String estadoActual = null;

        // ↓↓Comprobación↓↓
        if (indiceEstado >= 0 && indiceEstado < estado.length) {
            estadoActual = estado[indiceEstado];
        } else {
            estadoActual = estado[2]; // ↢  ↢ ↢"rechazado"
        }
        // ↑↑Comprobación↑↑

        // ↓↓Actualización en la base de datos↓↓
        actualizarEstadoEnBaseDeDatos(estadoActual, ticket);

        //↓↓ Mensaje de confirmación en consola↓↓
        System.out.println("Estado de la compra actualizado: " + estadoActual);
    }

    private static void actualizarEstadoEnBaseDeDatos(String estadoActual, int ticket) {
        try {
            PreparedStatement stm = c.prepareStatement("UPDATE Compras SET estado = ? WHERE ticket = ?");
            stm.setString(1, estadoActual);
            stm.setInt(2, ticket);
            stm.executeUpdate();
            System.out.println("Estado de la compra actualizado en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado en la base de datos: " + e.getMessage());
        }
    }
}