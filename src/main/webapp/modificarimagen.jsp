<%-- 
    Document   : modificarimagen
    Created on : 11-oct-2021, 12:08:19
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
        <% if(session.getAttribute("user").equals(null)) response.sendRedirect("login.jsp"); %>
        <h1>Modificar Imagen</h1>
        <form action="modificarimagen" method="post">
            Titulo:
            <input type="text" name="title" placeholder="Titulo ejemplo" required><br>
            Descripcion:
            <input type="text" name="description" placeholder="Descripcion ejemplo"><br>
            Keywords:
            <input type="text" name="keywords" placeholder="Keywords ejemplo"><br>
            Autor:
            <input type="text" name="author" placeholder="Autor ejemplo"><br>
            Fecha de creacion:
            <input type="date" name="capture_date" placeholder="Fecha creacion ejemplo"><br>
            <% String idimage = request.getParameter("id");%>
            <input type="hidden" name ="id" value="<%= idimage %>">
            
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
