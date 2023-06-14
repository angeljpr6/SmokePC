package org.example;

import java.sql.*;

public class Principal {

    //credenciales
    private static String usuario="postgres";
    private static String password="1234";
    private static Connection c;

    public static Connection getC() {return c;}

    public static void initConection() {
        try {

            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smokepc", usuario, password);
            System.out.println("ÉXITO");
        } catch (Exception e) {
            System.out.println("NOP");
        }
    }






}
