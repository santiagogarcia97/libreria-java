<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Librería - Registrarse</title>

        <link href="resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/signup.css" rel="stylesheet">
       
	</head>

    <body>
        <div class="signup-form">
            <form name="registration" action="auth/signup" method="post">
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
                    <input type="password" class="form-control" id="1" name="inputPassword" placeholder="Contraseña" required="required">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="2" name="confirm_password" placeholder="Confirmar Contraseña" required="required">
                </div>        
                <div>
                    <button type="submit" class="btn btn-success btn-lg btn-block" disabled="disabled">Crear Cuenta</button>
                </div>
                
				<c:if test="${sessionScope.errorMsg != null && sessionScope.errorMsg !='' }">
	              	<br>
	            	<div class="text-center text-danger">
	            		<c:out value="${sessionScope.errorMsg}"></c:out>
	            		<c:set var="errorMsg" value="" scope="session"  />
	            	</div>            
            	</c:if>

            </form>
            <div class="text-center">Ya tiene una cuenta? <a href="signin.jsp">Ingresar</a></div>      
            
            <script src="resources/js/jquery/jquery-3.3.1.min.js"></script>
       		<script src="resources/js/signup-validation.js"></script>
       </div>
    </body>
</html>