<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Librería - Registrarse</title>

        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/signup.css" rel="stylesheet">
	</head>

    <body>
        <div class="signup-form">
            <form action="auth/signup" method="post">
                <h2>Registrarse</h2>
                <p class="hint-text">Complete los datos para poder crear su cuenta.</p>
                <div class="form-group">
                    <div class="row">
                        <div class="col"><input type="text" class="form-control" name="inputNombre" placeholder="Nombre" required="required"></div>
                        <div class="col"><input type="text" class="form-control" name="inputApellido" placeholder="Apellido" required="required"></div>
                    </div>        	
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="inputDomicilio" placeholder="Domicilio" required="required">
                </div>
                <div class="form-group">
                    <input type="tel" class="form-control" name="inputTelefono" placeholder="Telefono" required="required">
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" name="inputEmail" placeholder="Email" required="required">
                </div>
                <div class="form-group">
                    <input type="number" class="form-control" name="inputDni" placeholder="DNI" required="required">
                </div>
                <hr>
                <div class="form-group">
                    <input type="text" class="form-control" name="inputUsername" placeholder="Nombre de Usuario" required="required">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="inputPassword" placeholder="Contraseña" required="required">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="confirm_password" placeholder="Confirmar Contraseña" required="required">
                </div>        
                <div>
                    <button type="submit" class="btn btn-success btn-lg btn-block">Crear Cuenta</button>
                </div>
            </form>
            <div class="text-center">Ya tiene una cuenta? <a href="signin.jsp">Ingresar</a></div>
        </div>
    </body>
</html>