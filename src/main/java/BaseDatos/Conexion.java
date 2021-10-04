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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author alumne
 */
public class Conexion {
    Connection connection;
    
    
    public Connection iniciarconexion(){
        try{
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr3;user=pr3;password=pr3");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return connection;
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
}


