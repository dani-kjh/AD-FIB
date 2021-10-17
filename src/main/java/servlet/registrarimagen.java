/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import BaseDatos.ModificacionyConsulta;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author alumne
 */
@WebServlet(name = "registrarimagen", urlPatterns = {"/registrarimagen"})
@MultipartConfig
public class registrarimagen extends HttpServlet {

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
        boolean correctUpload = true;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            //obtener parametros
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String keywords = request.getParameter("keywords");
            String author = request.getParameter("author");
            String capture_date = request.getParameter("capture_date");
            String correctDate = changeDateFormat(capture_date);
            //obtener parametros de imagen
            Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();
            //Modificamos el nombre de la imagen para hacerla unica
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");  
            LocalDateTime now = LocalDateTime.now();
            String timestamp = dtf.format(now);
            fileName = timestamp + "_"+  fileName;
            
            //guardar imagen en directorio local
            final String path = "/home/dani/NetBeansProjects/Practica2/src/main/webapp/imagenes"; //revisar path !!!
            correctUpload = saveFile(response, path, fileName, fileContent);
            
            
            HttpSession session = request.getSession();
            String nombreUsuarioActual = session.getAttribute("user").toString();
            //guardar informacion en la base de datos
            //Comprobamos que no ha habido errores durante la subida de las imagenes al directorio local
            if(correctUpload)
                connection.registrarImagen(title,description,keywords,author,nombreUsuarioActual, correctDate,fileName);
            
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            correctUpload = false;
            response.sendRedirect("error.jsp?tipo=registrarimagen");
        }
    }
    
    
//Metodo que guarda el fichero fileName en el directorio especificado por path
    private boolean saveFile(HttpServletResponse response, final String path, String fileName, InputStream fileContent) throws IOException{
        boolean correctUpload = true;
        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();
        try{
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            
          
            int read = 0;
            final byte[] bytes = new byte[1024];
            
            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            
            writer.println("New file " + fileName + " created at " + path);
           LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                   new Object[]{fileName, path});
     
            
        }catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());
            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                    new Object[]{fne.getMessage()});
            response.sendRedirect("error.jsp?tipo=registrarimagen");
            correctUpload = false;
        }
        return correctUpload;
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
         PrintWriter out = response.getWriter();

         
       
         out.println("<html> <body>"
                        + "<br>"
                        + "<a href='registrarimagen.jsp'> Ir a registar imagen </a>"
                        + "<br>"
                        + "<a href='menu.jsp'> Volver al menu</a> "
                        + "<br>"
                        + "</body></html>");
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
