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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    
    public boolean autenticausuario(String usuario, String clave){
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
    
    public boolean registrarImagen(String titulo, String description, String Keywords, String author, String creator, String dataCaptura, String filename){
        String query = "INSERT INTO IMAGE (TITLE, DESCRIPTION, KEYWORDS, AUTHOR, CREATOR, CAPTURE_DATE, STORAGE_DATE, FILENAME) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now();
        if(!existeusuario(creator)){
            return false;
        }
        else{
            try {
            
                statement = connection.prepareStatement(query);
                statement.setString(1, titulo);
                statement.setString(2, description);
                statement.setString(3, Keywords);
                statement.setString(4, author);
                statement.setString(5, creator);
                statement.setString(6, dataCaptura);
                statement.setString(7, dtf.format(now));
                statement.setString(8, filename);

                statement.executeUpdate();
            
        } catch (SQLException ex) {
                Logger.getLogger(ModificacionyConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
         return true;
        }

    }

    private boolean existeusuario(String creator) {
         try {
            String query = "select * from pr3.usuarios where id_usuario = ? ";
            PreparedStatement statement;
            statement = connection.prepareStatement(query);
            statement.setString(1, creator);
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
    
    public boolean updateimagen(String title, String description, String keywords, String author, String capture_date, int id){
        try {
            String query = "update pr3.image set title = ?, description = ?, keywords = ?, author = ?, capture_date = ? where id = ?";
            PreparedStatement statement;
            statement = connection.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, keywords);
            statement.setString(4, author);
            statement.setString(5, capture_date);
            statement.setInt(6, id);
            statement.executeUpdate();
            
        }catch (SQLException ex) {
            Logger.getLogger(ModificacionyConsulta.class.getName()).log(Level.SEVERE, null, ex);
            return true; //hay error
        }
        return false; //no hay error
    }

    public String eliminarImagen(String userChoice) {
        String nombreImagen = "null";
          
        String query = "delete from image where id=?";
        try {
            String queryNombre = "select filename from image where id = ?";
            PreparedStatement statement;
            statement = connection.prepareStatement(queryNombre);
            statement.setInt(1, Integer.parseInt(userChoice));
            ResultSet rs = statement.executeQuery();
            rs.next();
            nombreImagen = rs.getString("filename");
            
            
            statement = connection.prepareStatement(query);
            statement.setInt(1,Integer.parseInt(userChoice));
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ModificacionyConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombreImagen;
    }
    
    public ResultSet list(){
        String query = "select * from pr3.image";
        ResultSet rs = null;
        try{
            PreparedStatement statement;
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ModificacionyConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    
    public ResultSet search(String title, String description, String author, String keyword, String capture_date){
        ResultSet rs = null;
        try {
            String query = "select * from image "
                    + "where title LIKE ? OR description LIKE ? OR keywords LIKE ? OR author LIKE ? OR capture_date = ?  ";
            
            PreparedStatement statement = connection.prepareStatement(query);
            if(!title.isEmpty())
                statement.setString(1,'%'+title+'%' );
            else
                statement.setString(1, title);
            

            if(!description.isEmpty())    
                statement.setString(2, '%' + description + '%');
            else
                statement.setString(2, description);

            
            if(!keyword.isEmpty())
                statement.setString(3, '%' + keyword + '%' );
            else
                statement.setString(3, keyword);

            
            if(!author.isEmpty())
                statement.setString(4, '%' + author+ '%');
            else
                statement.setString(4, author);
            
            
            if(capture_date.equals("01/01/1000"))
                statement.setString(5, "");
            else
                statement.setString(5, capture_date);

           
            rs = statement.executeQuery();
            return rs;
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ModificacionyConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         return rs;  
      
    }
}
