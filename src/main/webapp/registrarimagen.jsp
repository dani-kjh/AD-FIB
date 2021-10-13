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
        <h1>Registrar Imagen</h1>
        <form action="registrarimagen" method="post" enctype="multipart/form-data">
            Titulo:
            <input type="text" name="title"><br>
            Descripcion:
            <input type="text" name="description"><br>
            Keywords:
            <input type="text" name="keywords"><br>
            Autor:
            <input type="text" name="author"><br>
            Fecha de creacion:
            <input type="date" name="capture_date"><br>
            Imagen:
            <input type="file" name="image"><br>
            
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
