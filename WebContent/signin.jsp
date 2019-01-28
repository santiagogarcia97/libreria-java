<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Librería - Ingresar</title>

        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/signup.css" rel="stylesheet">
	</head>

    <body>
        <div class="signup-form">
            <form action="auth/signin" method="post">
                <h2>Ingresar</h2>
                <p class="hint-text">Ingrese su usuario y contraseña.</p>
                <div class="form-group">
                    <input type="text" class="form-control" name="inputUsername" placeholder="Nombre de Usuario" required="required">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="inputPassword" placeholder="Contraseña" required="required">
                </div>
                <div>
                    <button type="submit" class="btn btn-success btn-lg btn-block">Ingresar</button>
                </div>
            </form>
            <div class="text-center">No tiene una cuenta? <a href="signup.jsp">Registrarse</a></div>
        </div>
    </body>
</html>