package org.example;

public class Main {
    public static void main(String[] args) {


        Principal.initConection();

        IniciarSesion.main(args);

        Usuario usuario=new Usuario(1,"Pepe","Popo","plinplinplonnn","pupu");

        Usuario usuario1=new Usuario();

        usuario1.iniciarSesion("plinplinplon","pupu");
        Proveedor proveedor1=new Proveedor(4,"lucas_fumon@hotmail","tuperra");
        proveedor1.añadirProducto(10,"ar",51,"ff");
        proveedor1.añadirProducto(10,"r",51,"ff");




        Usuario usuario2=new Usuario();


    }
}