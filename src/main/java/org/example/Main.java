package org.example;

public class Main {
    public static void main(String[] args) {


        Principal.initConection();


        Usuario usuario2=new Usuario();
        usuario2.iniciarSesion("bla","jeje");
        usuario2.cambiarCesta();


    }
}