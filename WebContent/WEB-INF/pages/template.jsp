<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
	<head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Libreria UTN</title>
        
        <link href="resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">

        <script src="resources/js/jquery/jquery-3.3.1.min.js"></script>
        <script src="resources/js/bootstrap/bootstrap.min.js"></script>
	</head>
	
	<body>
		<jsp:include page="topbar.jsp" />
		
		<div class="container">
			<jsp:include page="content.jsp" />
		</div>
		


	</body>	
</html>