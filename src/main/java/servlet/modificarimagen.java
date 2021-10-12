package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BaseDatos.ModificacionyConsulta;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumne
 */
@WebServlet(urlPatterns = {"/modificarimagen"})
public class modificarimagen extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ModificacionyConsulta connection = new ModificacionyConsulta();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            //obtener parametros
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String keywords = request.getParameter("keywords");
            String author = request.getParameter("author");
            String capture_date = request.getParameter("capture_date");
            int id = Integer.parseInt(request.getParameter("id"));
            
            Boolean error = connection.updateimagen(title, description, keywords, author, capture_date, id);
            if (error){
                response.sendRedirect("error.jsp?tipo=modificarimagen"); //error
            } else {
                //response.sendRedirect("menu.jsp"); 
                out.println("<html> <body>"
                        + "<h3>Imagen modificada!</h3>"
                        + "<br>"
                        + "<a href='menu.jsp'> Volver al menu</a> "
                        + "<br>"
                        + "</body></html>");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
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
