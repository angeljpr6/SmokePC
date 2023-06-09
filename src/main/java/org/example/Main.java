package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("He!");

        Principal.initConection();

        Usuario usuario=new Usuario(1,"Pepe","Popo","plinplinplonnn","pupu");

        //usuario.registrarUsuario();

        Usuario.iniciarSesion("plinplinplon","pupu");




    }
}