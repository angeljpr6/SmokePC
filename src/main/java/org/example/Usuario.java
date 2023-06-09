package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Usuario {

    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private Cesta cesta;
    private double saldo;
    private static Connection c=Principal.getC();

    public Usuario(int id,String nombre,String apellidos,String email){
        this.id=id;
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
        } catch (SQLException e) {
            System.out.println("Ups... algo ha fallado");
        }
    }
    /**
     * Te enseña la Compra de ticket en especifico
     *
     * @param ticket
     */
    public static void verCompra(int ticket){
        try {
            PreparedStatement stm = c.prepareStatement("select * from Compras where ticket=?;");
            stm.setInt(1,ticket);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                System.out.println("Ticket: " + result.getInt("ticket"));
                System.out.println("Fecha y Hora de compra: " + result.getDate("fecha") + " " + result.getTime("hora"));
                System.out.println("Precio Total: " + result.getFloat("precio"));
                System.out.println("Estado: " + result.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Ups... algo ha fallado");
        }
    }

    /**
     * Hace la compra de una cesta a nombre del usuario
     * @param i
     */
    public void comprarCesta(int i,float f){
        try {
            PreparedStatement stm = c.prepareStatement("insert into Compras values(default,?,?,?,?,?,?);");
            stm.setDate(1, Date.valueOf(LocalDate.now()));
            stm.setTime(2,Time.valueOf(LocalTime.now()));
            //stm.setFloat(3,cesta.getPrecioTotal());
            stm.setFloat(3,f);
            stm.setInt(4,i);
            stm.setInt(5,this.id);
            stm.setString(6,"En tramitacion");
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Algo ha ido mal");
        }
    }



    // GETTER & SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cesta getCesta() {
        return cesta;
    }

    public void setCesta(Cesta cesta) {
        this.cesta = cesta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
