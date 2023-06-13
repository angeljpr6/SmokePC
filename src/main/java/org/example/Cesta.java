package org.example;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author daniel perez
 * clase cesta
 */

public class Cesta {
    private static Connection c=Principal.getC();


     /**
     * Método para crear una cesta vinculada a un usuario en la base de datos
     * @param idUsuario
     * @return devuelve el ID de la cesta
     */
    public static int crearCesta( int idUsuario) {
        int idCesta=0;

        try {
            PreparedStatement stm = c.prepareStatement("insert into cesta (id,precioTotal,cantProductos, idUsuario) values (default,?,?,?);");
            stm.setFloat(1, 0);
            stm.setInt(2, 0);
            stm.setInt(3, idUsuario);
            stm.executeUpdate();
            System.out.println("cesta creada");
            stm = c.prepareStatement("select id from cesta where idUsuario=? order by id desc limit 1;");
            stm.setInt(1,idUsuario);
            ResultSet result = stm.executeQuery();
            while (result.next()){
                idCesta=result.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(idCesta);

        return idCesta;

    }

    /**
     * metodo para añadir producto a la cesta
     * @param idCesta
     * @param referencia
     */


    public static void anadirProductos(int idCesta, int referencia){
        try {
            PreparedStatement stm = c.prepareStatement("insert into Tiene (idCesta, referenciaProducto ) values(?,?);");
            stm.setInt(1,idCesta);
            stm.setInt(2,referencia);
            stm.execute();
            System.out.println("Ingresado satisfactoriamente");
            stm=c.prepareStatement("update Cesta set cantproductos=cantproductos+1 where id=?;");
            stm.setInt(1,idCesta);
            stm.execute();
            System.out.println("Se ha sumado la cantidad");
            stm=c.prepareStatement("update cesta set precioTotal=precioTotal+(select precio from productos where referencia=?) where id=?;");
            stm.setInt(1,referencia);
            stm.setInt(2,idCesta);
            stm.execute();
            System.out.println("se ha cambiado el precio de la cesta");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * metodo para eliminar producto de la cesta
     * @param idCesta
     * @param referencia
     */

    public static void eliminarProductoCesta(int idCesta, int referencia){
        try {
            PreparedStatement stm = c.prepareStatement("delete from tiene where idCesta=? and referenciaProducto=?");
            stm.setInt(1,idCesta);
            stm.setInt(2,referencia);
            stm.execute();
            System.out.println("producto eliminado");
            stm=c.prepareStatement("update Cesta set cantproductos=cantproductos-1 where id=?;");
            stm.setInt(1,idCesta);
            stm.execute();
            System.out.println("cantidad actualizada");
            stm=c.prepareStatement("update cesta set precioTotal=precioTotal-(select precio from productos where referencia=?) where id=?;");
            stm.setInt(1,referencia);
            stm.setInt(2,idCesta);
            stm.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
