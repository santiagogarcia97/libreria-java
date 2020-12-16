<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
	<head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Libreria UTN</title>
        
        <link href="/libreria-java/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link href="/libreria-java/resources/css/template.css" rel="stylesheet">
        <link href="/libreria-java/resources/css/error-page.css" rel="stylesheet">

	</head>
	
	<body>
		<jsp:include page="topbar.jsp" />
		
		<div class="container">
			<div class="row">
				
				<jsp:include page="sidebar.jsp" />

				<jsp:include page="content.jsp" />
			</div>
		</div>

        <script src="/libreria-java/resources/js/jquery/jquery-3.3.1.min.js"></script>
        <script src="/libreria-java/resources/js/bootstrap/bootstrap.min.js"></script>
	</body>	
</html>