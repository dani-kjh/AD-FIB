<%-- 
    Document   : registrarimagen
    Created on : 05-oct-2021, 10:49:51
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if(session.getAttribute("user") == null ) response.sendRedirect("login.jsp"); %>
        <h1>Registrar Imagen</h1>
        <form action="registrarimagen" method="post" enctype="multipart/form-data">
            Titulo:
            <input type="text" name="title" required><br>
            Descripcion:
            <input type="text" name="description" required><br>
            Keywords:
            <input type="text" name="keywords" required><br>
            Autor:
            <input type="text" name="author" required><br>
            Fecha de creacion:
            <input type="date" name="capture_date" required><br>
            Imagen:
            <input type="file" name="image" required><br>
            
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
