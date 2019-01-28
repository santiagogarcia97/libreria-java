<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@page import="libreria.utils.Configuracion"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>
	<head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Libreria UTN</title>
        
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="resources/jquery/js/jquery-3.3.1.min.js"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js"></script>
	</head>
	
	<body>
		<jsp:include page="topbar.jsp" />
		
		<div class="container">
			<jsp:include page="content.jsp" />
		</div>
		


	</body>	
</html>