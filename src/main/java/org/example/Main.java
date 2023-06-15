package org.example;

public class Main {
    public static void main(String[] args) {


        Principal.initConection();




        Proveedor proveedor1=new Proveedor(4,"lucas_fumon@hotmail","tuperra");
        Proveedor proveedor2=new Proveedor(15,"Ange@hotmail","1234");
        Proveedor proveedor3=new Proveedor(16,"Rau@hotmail","1234");
        proveedor1.añadirProducto(10,"ar",51,"ff");
        proveedor1.añadirProducto(10,"r",51,"ff");

        proveedor1.registrarProveedor("lucas_fumon@hotmail","tuperra");

        proveedor2.registrarProveedor("Ange@hotmail","1234");
        proveedor3.registrarProveedor("Rau@hotmail","1234");
        proveedor3.añadirProducto(21,"razer",12,"Cascos");
        proveedor2.añadirProducto(11,"razer",12,"Cascos");
        proveedor2.añadirProducto(12,"coca cola",15,"Lata");
        proveedor2.añadirProducto(13,"pepsi",12.3f,"botella");
        proveedor2.añadirProducto(104,"Ovoo",13,"Software");

        Usuario usuario2=new Usuario();


    }
}