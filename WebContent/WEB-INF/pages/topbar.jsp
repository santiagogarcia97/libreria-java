<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand mb-0 h1" href="#">Libreria UTN</a>
        <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" 
            data-target="#navbarCollapse" >
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="dropdown-menu-style collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Inicio</a>
                </li>                        
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" 
                        data-toggle="dropdown">Administrar</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Socios</a>
                        <a class="dropdown-item" href="#">Libros</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink2" 
                        data-toggle="dropdown">Prestamos</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">asdasd</a>
                        <a class="dropdown-item" href="#">asdasdads</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">sadasd</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Reportes</a>
                </li>
            </ul>
            
			<c:choose>		
				<c:when test="${sessionScope.loggedUser != null}">
		            <ul class="navbar-nav ">
		                <li class="nav-item dropdown">
		                    <a href="#" class="nav-link dropdown-toggle" id="navDropDownLink" 
		                        data-toggle="dropdown">
		                       Username</a>
		                    <div class="dropdown-menu dropdown-menu-right">
		                        <a class="dropdown-item" href="#">Perfil</a>
		                        <div class="dropdown-divider"></div>
		                        <form action="auth/logout" method="post">
		                        	<button type="submit" class="dropdown-item">Log Out</button>
		                        </form>
		                    </div>
		                </li>
		            </ul>				   				   		   
				</c:when>				
				
				<c:otherwise>		
                    <a class="btn btn-outline-primary mr-1" href="signin.jsp">Ingresar</a>
                    <a class="btn btn-primary" href="signup.jsp">Registrarse</a>
				</c:otherwise>
			</c:choose>            

        </div>
    </div>
</nav>