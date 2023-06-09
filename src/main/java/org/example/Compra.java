package org.example;
import java.sql.*;
public class Compra {
    private String fecha;
    private String hora;
    private int tickect;
    private double precio;
    private static String[] estado = {"tramitacion", "enviado", "rechazado", "llegando"};

    public static  void cambiarEstado(String estadoActual, boolean cestaConfirmada) {
        if (cestaConfirmada) {
            if (estado[0].equals(estadoActual)) { // "tramitacion"
                estadoActual = estado[3]; // "llegando"
                System.out.println("Estado de la compra actualizado a 'llegando'");
            } else {
                System.out.println("La compra ya ha sido confirmada o tiene un estado inválido");
            }
        } else {
            if (estado[0].equals(estadoActual)) { // "tramitacion"
                estadoActual = estado[2]; // "rechazado"
                System.out.println("Estado de la compra actualizado a 'rechazado'");
            } else {
                System.out.println("La compra ya ha sido confirmada o tiene un estado inválido");
            }
        }

        if (estado[3].equals(estadoActual)) { // "llegando"
            estadoActual = estado[1]; // "enviado"
            System.out.println("Estado de la compra actualizado a 'enviado'");
            System.out.println("El envío se ha efectuado correctamente");
        }
    }
}