<%-- 
    Document   : listar
    Created on : 15-oct-2021, 16:00:38
    Author     : alumne
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="BaseDatos.ModificacionyConsulta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if(session.getAttribute("user") == null) response.sendRedirect("login.jsp"); %>
        <h1>Lista de imagenes</h1>
        
        <%
            //obtenemos usuario de la sesion
            HttpSession s = request.getSession();
            String usu = s.getAttribute("user").toString();
            
            //preparamos la conexion con base de datos + query
            ModificacionyConsulta connection = new ModificacionyConsulta();
            ResultSet rs = connection.list();
            
            //bucle para cada imagen
            while(rs.next()){
                String title = rs.getString("title");
                String author = rs.getString("author");
                String creator = rs.getString("creator");
                int id = rs.getInt("id");
                String description = rs.getString("description");
                if(usu.equals(creator)){
                    out.println("Imagen: "+ id + "<br>"+ "Titulo: "+ title + "<br>"+ "Autor:"+ author+ "<br>"
                    + "<a href='visualizar.jsp?nombreIM=" + rs.getString("filename") + "'> Ver imagen</a>" + "<br>"
                    + "<a href='modificarimagen.jsp?id=" + id + "'>Modificar Imagen</a>" + "<br>"
                    + "<a href='eliminarImagen.jsp?id=" + id + "'>Eliminar Imagen</a>" + "<br>" + "<br>");
                } else {
                    out.println("Imagen: "+ id + "<br>"+ "Titulo: "+ title + "<br>"+ "Autor:"+ author+ "<br>"
                    + "<a href='visualizar.jsp?nombreIM=" + rs.getString("filename") + "'> Ver imagen</a>" + "<br>"
                    + "NO tienes permisos para modificar la imagen" + "<br>"
                    + "NO tienes permisos para eliminar la imagen" + "<br>" + "<br>");
                }
            }
        connection.cerrarconexion();
        out.println("<br>");
        out.println("<a href ='menu.jsp'> Volver a menu </a>");
        %>
    </body>
</html>
