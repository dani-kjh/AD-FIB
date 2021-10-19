<%-- 
    Document   : login.jsp
    Created on : 28-sep-2021, 10:24:50
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="login" method="post">
            Usuario:
            <input type="text" name="usuario"><br>
            Contraseña:
            <input type="password" name="clave"><br>
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
