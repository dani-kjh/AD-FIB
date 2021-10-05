/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumne
 */
public class ModificacionyConsulta {
    Connection connection;
    
    public ModificacionyConsulta(){
        try{
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr3;user=pr3;password=pr3");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void cerrarconexion(){
        try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
    }
    
    public boolean existeusuario(String usuario, String clave){
        try {
            String query = "select * from pr3.usuarios where id_usuario = ? and password = ?";
            PreparedStatement statement;
            statement = connection.prepareStatement(query);
            statement.setString(1, usuario);
            statement.setString(2, clave);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                //reenviar a menu
                return true;
            } else {
                //reenviar a error, error.java/jsp?codigo, usar get.parameter para obtener "codigo"
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModificacionyConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public void registrarimagen(){
        
    }
}
