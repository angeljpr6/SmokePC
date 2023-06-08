package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Usuario {

    private static ArrayList<Integer> allId=new ArrayList<>();
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private Cesta cesta;
    private double saldo;
    private Connection c=Principal.getC();

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
    public void anadirMetodoPago(TarjetaCredito tarjetaCredito){
        String titular=tarjetaCredito.getTitular();
        int cvv=tarjetaCredito.getCvv();
        PreparedStatement stm = c.prepareStatement("insert into tarjetaCredito values()");
    }

}
