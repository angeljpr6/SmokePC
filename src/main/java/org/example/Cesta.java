package org.example;


import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Cesta {
    private ArrayList<Productos> productos;
    private int cantidad;
    private double precioTotal;

    public Cesta() {
        this.productos = new ArrayList<>();
        this.cantidad=0;
        this.precioTotal=0;

    }

    public void anadirProductos(Productos producto){
        this.productos.add(producto);
    }


}
