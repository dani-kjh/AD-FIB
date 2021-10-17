<%-- 
    Document   : eliminarImagen
    Created on : Oct 12, 2021, 11:11:11 AM
    Author     : dani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Imagen</title>
    </head>
    <body>
        <% if(session.getAttribute("user") == null) response.sendRedirect("login.jsp"); %>
        <h1>Eliminar Imagen</h1>
        <form action="eliminarimagen" method="post">
            <p> Esta seguro que desea eliminar la imagen: </p>
            <br>
            
            <input type="radio" name="seleccion" id ="si" value="si" > 
            <% String idimage = request.getParameter("id");%>
            <input type="hidden" name ="id" value="<%= idimage %>">
            <label for = "si"> Si, deseo eliminar la imagen </label>
            <br>
            
            <input type="radio" name="seleccion" id ="no" value="no" >
            <label for ="no"> No, no deseo eliminar la imagen </label>
            <br>
            
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
