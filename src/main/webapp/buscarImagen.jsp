<%-- 
    Document   : buscarImagen
    Created on : Oct 13, 2021, 12:19:55 PM
    Author     : dani
--%>

<%@page import="java.nio.file.Paths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar imagen</title>
    </head>
    <body>
        <h1>Busqueda de imagenes</h1>
        <form action = "buscarImagen" method = "GET">
            
            <label for ="titulo"> Titulo: </label>
            <input type ="text" name = "titulo"> 
            <br>
            <label for ="descripcion"> Descripcion: </label>
            <input type ="text" name = "descripcion"> 
            <br>
            <label for ="autor"> Autor: </label>
            <input type ="text" name = "autor">
            <br>
            <label for ="keyword"> Keywords: </label>
            <input type ="text" name = "keyword">
            <br>
            <label for ="capture_date"> Fecha de creacion: </label>
            <!<!-- Necesitamos dejar un valor por defecto para que funcione -->
            <input type ="date" name = "capture_date" value = "1000-01-01">
            <br>
            
            
            <input type ="submit" value ="Enviar">
            
            
        </form>
    </body>
</html>
