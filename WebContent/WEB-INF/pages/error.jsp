<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
	<head>
		<title>Librería - Error</title>
        <link href="/libreria-java/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="/libreria-java/resources/css/error-page.css" rel="stylesheet">
	</head>
	
	<body>
		<div class="container">
			<span class="h3 text-bold">Se ha producido un error</span>
			<hr>
			<c:out value="${sessionScope.errorMsg}"></c:out>
			<c:set var="errorMsg" value="" scope="session"/>
		</div>
	</body>
</html>