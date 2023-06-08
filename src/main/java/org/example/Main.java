package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("He!");

        Principal.initConection();
        TarjetaCredito tarj =new TarjetaCredito(484373795,"Angel Perez",222,1);
        Usuario.anadirMetodoPago(tarj);

    }
}