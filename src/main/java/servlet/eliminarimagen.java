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
@WebServlet(name = "eliminarimagen", urlPatterns = {"/eliminarimagen"})
public class eliminarimagen extends HttpServlet {

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
        HttpSession session = request.getSession();
             
        try {
            
            
            String userChoice = request.getParameter("seleccion");
            String idImagen = request.getParameter("id");
            String nombreImagen = request.getParameter("nombre");
            
            if(userChoice.equals("no")){
                response.sendRedirect("menu.jsp");
            }
            
            //El usuario ha decidido eliminar la imagen
            else{
                
                connection.eliminarImagen(idImagen);
                eliminarLocalmente(nombreImagen);
            }
        }
        catch (Exception e){
            
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

    private void eliminarLocalmente(String fileName) {
        
        final String path = "/home/dani/NetBeansProjects/Practica2/src/main/resources/imagenes"; //revisar path !!!
        
        File fileToDelete = new File(path + File.separator + fileName );
        if(fileToDelete.delete()){
            System.out.println(fileToDelete.getName() + " deleted");
        }
        else{
            System.out.println("failed");
        }
    }

}
