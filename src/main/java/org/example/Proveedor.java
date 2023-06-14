package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author daniel perez
 * Clase proveedor
 */

public class Proveedor {
    private int id;
    private String email;
    private String contraseña;
    private static Connection c=Principal.getC();
    public Proveedor(int id, String email, String contraseña) {
        this.id = id;
        this.email = email;
        this.contraseña = contraseña;

    }

    public Proveedor() {
        this.id=0;
        this.email="";
        this.contraseña="";
    }


    /**
     * Se le pasa un email y una contraseña por parametro e inicia sesion si son correctos
     * @param email
     * @param contraseña
     */
    public void iniciarSesion(String email,String contraseña){
        try {
            PreparedStatement stm = c.prepareStatement("select * from proveedor where contraseña=? and email=?;");
            stm.setString(1,contraseña);
            stm.setString(2,email);
            ResultSet result = stm.executeQuery();
            if (result.next()){
                this.id=result.getInt("id");
                System.out.println("Sesion iniciada");
            }else System.out.println("Email o contraseña incorrectos");



        } catch (SQLException e) {
            System.out.println("Email o contraseña incorrectos");
        }
    }

    /**
     * metodo para registrar proveedor en la base de datos
     * @param email
     * @param contraseña
     */

    public  void registrarProveedor(String email, String contraseña){
        int idProveedor=0;
        try {
            PreparedStatement stm = c.prepareStatement("insert into Proveedor values(default,?,?);");
            stm.setString(1,email);
            stm.setString(2,contraseña);
            stm.execute();
            System.out.println("usuario registrado");
            stm = c.prepareStatement("select id from Proveedor where email=?;");
            stm.setString(1,email);
            ResultSet result = stm.executeQuery();
            while (result.next()){
                idProveedor=result.getInt("id");
            }
            this.id=idProveedor;
            this.email=email;
            this.contraseña=contraseña;

            System.out.println(idProveedor);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo para que el proveedor añada un producto
     * @param stock
     * @param marca
     * @param precio
     * @param nombre
     */

    public void añadirProducto(int stock, String marca, float precio, String nombre){
        int ref=0;
        try {
            PreparedStatement stm = c.prepareStatement("insert into Productos values(default,?,?,?,?);");
            stm.setFloat(1,precio);
            stm.setString(2,marca);
            stm.setString(3,nombre);
            stm.setInt(4,stock);
            stm.execute();
            System.out.println("producto registrado");

            stm = c.prepareStatement("select referencia from Productos where marca=? and nombre=?;");
            stm.setString(1,marca);
            stm.setString(2,nombre);
            ResultSet result = stm.executeQuery();
            while (result.next()){
                ref=result.getInt("referencia");
            }
            System.out.println(ref);
            stm = c.prepareStatement("insert into Proveen values(?,?);");
            stm.setInt(1,this.id);
            stm.setInt(2, ref);
            stm.execute();
            System.out.println("producto relacionado en la base de datos");



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * método que el proovedor deje de proveer un producto
     * @param referencia
     */

    public void eliminarProducto(int referencia){
        try {
            PreparedStatement stm = c.prepareStatement("update productos set stock=0 where referencia=?;");
            stm.setInt(1,referencia);
            stm.execute();
            System.out.println("stock actualizado a 0");

            stm=c.prepareStatement("delete from proveen where refProduct=?;");
            stm.setInt(1,referencia);
            System.out.println("Tabla proveen actualizada");

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }
}
