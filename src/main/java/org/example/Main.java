package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("He!");

        Principal.initConection();

        Usuario usuario=new Usuario(1,"Pepe","Popo","plinplinplonnn","pupu");

        Usuario usuario1=new Usuario();
        //usuario.registrarUsuario();

        usuario1.iniciarSesion("plinplinplon","pupu");


        Productos.agregarProducto(120,"Razer",125f,3);

    }
}