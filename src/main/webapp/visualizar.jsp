<%-- 
    Document   : test
    Created on : Oct 16, 2021, 6:15:27 PM
    Author     : dani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualize Image Page</title>
    </head>
    <body>
         <% if(session.getAttribute("user") == null ) response.sendRedirect("login.jsp"); %>
         <% String nombre = request.getParameter("nombreIM");%>
        <img src ="./imagenes/<%= nombre%>" width ="500" height ="500">
        <br>
        <a href ='menu.jsp'> Volver a menu </a>
    </body>
</html>
