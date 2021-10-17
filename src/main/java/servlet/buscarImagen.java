/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import BaseDatos.ModificacionyConsulta;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dani
 */
@WebServlet(name = "buscarImagen", urlPatterns = {"/buscarImagen"})
public class buscarImagen extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        try  {
            
            
          
            String title = request.getParameter("titulo");
            String description = request.getParameter("descripcion");
            String keywords = request.getParameter("keyword");
            String author = request.getParameter("autor");
            String capture_date = request.getParameter("capture_date");
            capture_date = changeDateFormat(capture_date);
          
            ModificacionyConsulta connection = new ModificacionyConsulta();
            ResultSet rs = connection.search(title, description, author, keywords, capture_date);
            
            printResultSet(response, request, rs);
          
            
        }
        
        
        
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private void printResultSet(HttpServletResponse response, HttpServletRequest request, ResultSet rs) throws IOException, SQLException {
        PrintWriter out = response.getWriter();
        String path = Paths.get(".").toAbsolutePath().toString();
        
        System.out.println(path);
        boolean empty = true;
        HttpSession sess = request.getSession();
        String usuario = sess.getAttribute("user").toString();
        while(rs.next()){
            empty = false;
            String tituloResultado = rs.getString("title");
            System.out.println(tituloResultado);
            String autorResultado = rs.getString("author");
            String descripcionResultado = rs.getString("description");
            int idResultado = rs.getInt("id");
            String creadorResultado = rs.getString("creator");
            
            out.println("Imagen: "+ idResultado + "<br>"+ "Titulo: "+ tituloResultado
                    + "<br>"+ "Autor:"+ autorResultado+ "<br>");
            
            if(usuario.equals(creadorResultado)){
                out.println("<a href='visualizar.jsp?nombreIM=" + rs.getString("filename") + "'> Ver imagen</a>" + "<br>"
                        + "<a href='modificarimagen.jsp?id=" + idResultado + "'>Modificar Imagen</a>" + "<br>"
                                + "<a href='eliminarImagen.jsp?id=" + idResultado + "'>Eliminar Imagen</a>" + "<br>" + "<br>");
                
            }
            
            else{
                out.println("<a href='visualizar.jsp?nombreIM=" + rs.getString("filename") + "'> Ver imagen</a>" + "<br>"
                        + "NO tienes permisos para modificar la imagen" + "<br>"
                        + "NO tienes permisos para eliminar la imagen" + "<br>" + "<br>");
            }
        }
        
        
        if(!rs.next() && empty){
            out.println("Error: No se ha encontrado ninguna imagen que coincida con sus criterios");
            out.println("<br>"+ "<a href='buscarImagen.jsp'>Realizar otra busqueda</a>" + "<br>");
        }
        out.println("<a href ='menu.jsp'> Volver a menu </a>");
    }

    private String changeDateFormat(String capture_date) throws ParseException {
        final String NEW_FORMAT = "dd/MM/yyyy";
        final String OLD_FORMAT = "yyyy-MM-dd";
        
        // August 12, 2010
        String oldDateString = capture_date;
        String newDateString;
        
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        java.util.Date d = sdf.parse(oldDateString);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
        return newDateString;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
