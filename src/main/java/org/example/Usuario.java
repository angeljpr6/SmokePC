package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Usuario {

    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private float saldo;
    private static Connection c=Principal.getC();
    private String contrasena;

    public Usuario(int id,String nombre,String apellidos,String email,String contrasena){
        this.id=id;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.email=email;
        this.saldo=0;
        this.contrasena=contrasena;
    }
    public Usuario(){
        this.id=0;
        this.nombre="";
        this.apellidos="";
        this.email="";
        this.saldo=0;
    }

    /**
     * Se le pasa un email y una contraseña por parametro e inicia sesion si son correctos
     * @param email
     * @param contrasena
     */
    public void iniciarSesion(String email,String contrasena){
        try {
            PreparedStatement stm = c.prepareStatement("select * from usuario where contrasena=? and email=?;");
            stm.setString(1,contrasena);
            stm.setString(2,email);
            ResultSet result = stm.executeQuery();
            if (result.next()){
                this.id=result.getInt("id");
                this.nombre=result.getString("nombre");
                this.apellidos=result.getString("apellidos");
                this.saldo=result.getFloat("saldo");
                this.contrasena=result.getString("contrasena");
                System.out.println("Sesion iniciada");
            }else System.out.println("Email o contraseña incorrectos");



        } catch (SQLException e) {
            System.out.println("Email o contraseña incorrectos");
        }
    }

    /**
     * Se le pasa los datos principales del usuario por parametro entonces estos se insertan en la base de datos
     * y despues se añaden a los atributos del usuario
     *
     * @param nombre
     * @param apellidos
     * @param email
     * @param contrasena
     */
    public void registrarUsuario(String nombre,String apellidos,String email,String contrasena){
        try {
            PreparedStatement stm = c.prepareStatement("insert into Usuario values(default,?,?,?,0,?,null);");
            stm.setString(1,nombre);
            stm.setString(2,apellidos);
            stm.setString(3,email);
            stm.setString(4,contrasena);
            stm.execute();

            this.nombre=nombre;
            this.apellidos=apellidos;
            this.email=email;
            this.contrasena=contrasena;

            stm =c.prepareStatement("select id from usuario where email=?");
            stm.setString(1,email);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                this.id=result.getInt("id");
            }
            this.saldo=0;

            //Metodo para crear una cesta que se le añadira al usuario

        } catch (SQLException e) {
            System.out.println("Algo falla registrarUsuario");
        }
    }

    public void cambiarCesta(){

        //Se usará el metodo crear cesta Dani cabron hazlo

        try {
            PreparedStatement stm = c.prepareStatement("update Usuario set idCesta=? where idUsuario=?");
            //stm.setInt(1, EL ID DE LA CESTA QUE SE CREARA EN EL METODO ANTERIOR);
            stm.setInt(2,this.id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Se el pasa un String por parametro que pasara a ser el nuevo email del usuario
     * @param nuevoEmail
     */
    public void cambiarEmail(String nuevoEmail){
        this.email=nuevoEmail;
        try {
            PreparedStatement stm = c.prepareStatement("update usuario set email=? where id=?");
            stm.setString(1,nuevoEmail);
            stm.setInt(2,this.id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cambiarNombre(String nuevoNombre){
        this.nombre=nuevoNombre;
        try {
            PreparedStatement stm = c.prepareStatement("update usuario set nombre=? where id=?");
            stm.setString(1,nuevoNombre);
            stm.setInt(2,this.id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void cambiarApellidos(String nuevosApellidos){
        this.apellidos=nuevosApellidos;
        try {
            PreparedStatement stm = c.prepareStatement("update usuario set apellidos=? where id=?");
            stm.setString(1,nuevosApellidos);
            stm.setInt(2,this.id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Se añade una tarjeta bancaria a la base de datos y se vincula con el usuario
     *
     * @param tarjetaCredito
     */
    public static void anadirMetodoPago(TarjetaCredito tarjetaCredito){

        try {
            PreparedStatement stm = c.prepareStatement("insert into TarjetaCredito values(?,?,?,?);");
            stm.setInt(1,tarjetaCredito.getNumTarjeta());
            stm.setInt(2,tarjetaCredito.getIdUsuario());
            stm.setString(3,tarjetaCredito.getTitular());
            stm.setInt(4,tarjetaCredito.getCvv());
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Ups... algo ha fallado");
        }
    }
    /**
     * Te enseña la Compra de ticket en específico
     *
     * @param ticket
     */
    public static void verCompra(int ticket){
        try {
            PreparedStatement stm = c.prepareStatement("select * from Compras where ticket=?;");
            stm.setInt(1,ticket);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                System.out.println("Ticket: " + result.getInt("ticket"));
                System.out.println("Fecha y Hora de compra: " + result.getDate("fecha") + " " + result.getTime("hora"));
                System.out.println("Precio Total: " + result.getFloat("precio"));
                System.out.println("Estado: " + result.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Ups... algo ha fallado");
        }
    }

    /**
     * Hace la compra de una cesta a nombre del usuario
     * @param i
     */
    public void comprarCesta(int i,float f){
        try {
            PreparedStatement stm = c.prepareStatement("insert into Compras values(default,?,?,?,?,?,?);");
            stm.setDate(1, Date.valueOf(LocalDate.now()));
            stm.setTime(2,Time.valueOf(LocalTime.now()));
            //stm.setFloat(3,cesta.getPrecioTotal());
            stm.setFloat(3,f);
            stm.setInt(4,i);
            stm.setInt(5,this.id);
            stm.setString(6,"En tramitacion");
            stm.execute();
        } catch (SQLException e) {
            System.out.println("Algo ha ido mal");
        }
    }



    // GETTER & SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
