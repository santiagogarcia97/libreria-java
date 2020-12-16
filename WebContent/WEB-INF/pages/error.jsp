<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Librería - Error</title>
        <link href="/libreria-java/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link href="/libreria-java/resources/css/error-page.css" rel="stylesheet">
	</head>
	
	<body>
		
		<div class="container">
		    <div class="row">
		        <div class="col-md-12">
		            <div class="error-template">
		                <h1>Oops!</h1>
		                <h2>Se ha producido un error</h2>
		                <div class="error-details">
		                	<c:out value="${sessionScope.errorMsg}"></c:out>
							<c:set var="errorMsg" value="" scope="session"/>
		                </div>
		                <div class="error-actions">
		                    <a href="/libreria-java" class="btn btn-primary btn-lg">
		                    	<span class="glyphicon glyphicon-home"></span>
		                        Volver al inicio
		                    </a>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>	
		
		
        <script src="/libreria-java/resources/js/jquery/jquery-3.3.1.min.js"></script>
        <script src="/libreria-java/resources/js/bootstrap/bootstrap.min.js"></script>
	</body>
</html>
