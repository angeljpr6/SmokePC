package org.example;

import java.util.ArrayList;

public class Usuario {

    private static ArrayList<Integer> allId=new ArrayList<>();
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private Cesta cesta;
    private double saldo;
    private ArrayList<TarjetaBancaria> metodosPago;

    public Usuario(String nombre,String apellidos,String email){
        this.id=allId.size();
        allId.add(this.id);
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.email=email;
        this.saldo=0;
    }

    public void anadirMetodoPago(TarjetaBancaria tarjetaBancaria){
        this.metodosPago.add(tarjetaBancaria);
    }

}
