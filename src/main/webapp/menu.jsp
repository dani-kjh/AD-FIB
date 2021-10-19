<%-- 
    Document   : menu
    Created on : 28-sep-2021, 10:54:25
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Page</title>
    </head>
    
    <body>
        <% if(session.getAttribute("user") == null) response.sendRedirect("login.jsp"); %>
        <h1>Menú</h1> 
        <h2>Selecciona la opción que quieras</h2>
        <a href="registrarimagen.jsp"> Ir a registar imagen </a> <br>
        <a href="listar.jsp"> Ir a listar </a> <br>
        <a href="buscarImagen.jsp"> Ir a buscar imagen </a> <br>
    </body>
</html>
