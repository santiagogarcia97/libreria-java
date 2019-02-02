<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
	<head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Libreria UTN</title>
        
        <link href="resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/template.css" rel="stylesheet">

	</head>
	
	<body>
		<jsp:include page="topbar.jsp" />
		
		<div class="container">
			<div class="row">
				<jsp:include page="sidebar.jsp" />
				
				<jsp:include page="content.jsp" />
			</div>
		</div>
		
		<jsp:include page="footer.jsp" />

        <script src="resources/js/jquery/jquery-3.3.1.min.js"></script>
        <script src="resources/js/bootstrap/bootstrap.min.js"></script>
	</body>	
</html>