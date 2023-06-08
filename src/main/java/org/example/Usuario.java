package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Usuario {

    private static ArrayList<Integer> allId=new ArrayList<>();
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private Cesta cesta;
    private double saldo;
    private static Connection c=Principal.getC();

    public Usuario(String nombre,String apellidos,String email){
        this.id=allId.size();
        allId.add(this.id);
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.email=email;
        this.saldo=0;
    }

    /**
     * Se añade una tarjeta bancaria a la base de datos y se vincula con el usuario
     *
     * @param tarjetaCredito
     */
    public static void anadirMetodoPago(TarjetaCredito tarjetaCredito){

        try {
            PreparedStatement stm = c.prepareStatement("insert into TarjetaCredito values(?,?,?,?);");
            stm.setInt(1,tarjetaCredito.getNumTarjeta());
            stm.setInt(2,tarjetaCredito.getIdUsuario());
            stm.setString(3,tarjetaCredito.getTitular());
            stm.setInt(4,tarjetaCredito.getCvv());
            stm.execute();
            System.out.println("Esto funciona");
        } catch (SQLException e) {
            System.out.println("Ups... algo ha fallado");
        }
    }

}
