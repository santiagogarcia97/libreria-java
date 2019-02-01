<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Librería - Ingresar</title>

        <link href="../resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link href="../resources/css/signup.css" rel="stylesheet">
	</head>

    <body>
        <div class="signup-form">
            <form id="mainForm" action="login" method="post">
                <h2>Ingresar</h2>
                <p class="hint-text">Ingrese su usuario y contraseña.</p>
                <div class="form-group">
                    <input type="text" class="form-control" name="inputUsername" placeholder="Nombre de Usuario" required="required">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="inputPassword" placeholder="Contraseña" required="required">
                </div>
                <div>
                    <button type="submit" id="btnSubmit" class="btn btn-success btn-lg btn-block">Ingresar</button>
                </div>
              	
              	<c:if test="${sessionScope.errorMsg != null && sessionScope.errorMsg !='' }">
              	<br>
            	<div class="text-center text-danger">
            		<c:out value="${sessionScope.errorMsg}"></c:out>
            		<c:set var="errorMsg" value="" scope="session"  />
            	</div>            
            	</c:if>
            	
            </form>
            <div class="text-center">No tiene una cuenta? <a href="signup">Registrarse</a></div>
        </div>
        
        <script src="../resources/js/jquery/jquery-3.3.1.min.js"></script>
  		<script src="../resources/js/auth.js"></script>        
    </body>
</html>