package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;



public class UsuarioTest {

    private static Connection c;

    @Test
    public void cambiarEmail() {
        String nuevoEmail="coquito";
        String emailActual="";
        Usuario usuario=new Usuario();

        int idusuario=1;
        try {
            PreparedStatement stm=c.prepareStatement("update usuario set email=? where id=?;");
            stm.setString(1,nuevoEmail);
            stm.setInt(2,idusuario);
            stm.execute();

            stm=c.prepareStatement("select email from usuario where id=?;");
            stm.setInt(1,idusuario);
            ResultSet result = stm.executeQuery();
            if (result.next()){
                emailActual=result.getString("email");
            }

            assertEquals(nuevoEmail,emailActual);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}