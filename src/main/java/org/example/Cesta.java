package org.example;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cesta {
    private int id;
    private int idUsuario;
    private int cantidad;
    private double precioTotal;
    private Connection c=Principal.getC();

    public Cesta(int id, int idUsuario, int cantidad, double precioTotal, Connection c) {
        PreparedStatement stm = null;
        try {
            stm = c.prepareStatement("select * from Cesta where id = ?; ");
            stm.setInt(1,this.idUsuario);
            ResultSet result= stm.executeQuery();
            this.id=result.getInt("id");
            this.cantidad=result.getInt("cantProductos");
            this.precioTotal=result.getDouble("precioTotal");
        } catch (SQLException e) {
            System.out.println("te fumaste");
        }

        this.id = id;
        this.idUsuario = idUsuario;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.c = c;

    }

    public void anadirProductos(int idCesta, int referencia){
        try {
            PreparedStatement stm = c.prepareStatement("insert into Tiene (idCesta, referencia ) values(?,?);");
            stm.setInt(1,1);
            stm.setInt(2,1);
            stm.execute();
            System.out.println("Ingresado satisfactoriamente");
            stm=c.prepareStatement("update Cesta set cantidad=cantidad+1 where id=?;");
            stm.setInt(1,this.id);
            stm.execute();
            System.out.println("Se ha sumado la cantidad");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }


}
