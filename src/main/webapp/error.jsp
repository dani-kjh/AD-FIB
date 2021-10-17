<%-- 
    Document   : error
    Created on : 11-oct-2021, 17:34:27
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
        <h1>Error :(</h1> 
        <%
            if(request.getParameter("tipo").equals("login")){
        %>        
                Los credenciales son incorrectos, inténtelo de nuevo.<br>
                <a href="login.jsp"> Aceptar </a> <br>
        <%
            } else if(request.getParameter("tipo").equals("modificarimagen")) {
        %>
                La modificación no se ha podido completar, revise los parámetros e inténtelo de nuevo.<br>
                <a href="menu.jsp"> Aceptar </a> <br>
        <%
            } else if(request.getParameter("tipo").equals("registrarimagen")) {
        %>
                El registro no se ha podido completar, revise los parámetros e inténtelo de nuevo.<br>
                <a href="menu.jsp"> Aceptar </a> <br>
        <%
            } else if(request.getParameter("tipo").equals("eliminarImagen")) {
        %>
                No se ha podido eliminar la imagen correctamente. Inténtelo de nuevo. <br>
                <a href="menu.jsp"> Aceptar </a> <br>

        <%

            } else {
        %>
                Error desconocido, será enviado de vuelta al login.<br>
                <a href="login.jsp"> Aceptar </a> <br>
        <%
            }
        %>
    </body>
</html>
