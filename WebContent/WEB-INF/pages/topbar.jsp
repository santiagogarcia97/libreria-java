<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand mb-0 h1" href="home">Libreria UTN</a>
        <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" 
            data-target="#navbarCollapse" >
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="dropdown-menu-style collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto"> 
                
				<c:choose>		
					<c:when test="${sessionScope.loggedType eq 'admin'}">
		                <li class="nav-item dropdown">
		                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" 
		                        data-toggle="dropdown">Prestamos</a>
		                    <div class="dropdown-menu">
		                        <a class="dropdown-item" href="#">En preparación</a>
		                        <a class="dropdown-item" href="#">Pendientes de retiro</a>
		                        <a class="dropdown-item" href="#">Pendientes de devolución</a>
		                        <a class="dropdown-item" href="#">Buscar</a>
		                    </div>
		                </li>
		                <li class="nav-item">
		                    <a class="nav-link" href="#">Socios</a>
		                </li>
		                <li class="nav-item">
		                    <a class="nav-link" href="#">Libros</a>
		                </li>                
		                <li class="nav-item">
		                    <a class="nav-link" href="#">Reportes</a>
		                </li>			   				   		   
					</c:when>				

					<c:when test="${sessionScope.loggedType eq 'socio'}">
		                <li class="nav-item dropdown">
		                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" 
		                        data-toggle="dropdown">Mis Prestamos</a>
		                    <div class="dropdown-menu">
		                        <a class="dropdown-item" href="#">Pendientes de retiro</a>
		                        <a class="dropdown-item" href="#">Pendientes de devolución</a>
		                        <a class="dropdown-item" href="#">Historial</a>
		                    </div>
		                </li>	   				   		   
					</c:when>	
				</c:choose>       
                
                <li class="nav-item">
                    <a class="nav-link" href="#">Acerca de</a>
                </li>
            </ul>
            
			<c:choose>		
				<c:when test="${sessionScope.loggedType != null}">
		            <ul class="navbar-nav ">
		                <li class="nav-item dropdown">
		                    <a href="#" class="nav-link dropdown-toggle" id="navDropDownLink" 
		                        data-toggle="dropdown">
		                    	<c:out value="${sessionScope.loggedUsername}"></c:out>   
							</a>
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
                    <a class="btn btn-outline-primary mr-1" href="auth/login">Ingresar</a>
                    <a class="btn btn-primary" href="auth/signup">Registrarse</a>
				</c:otherwise>
			</c:choose>            

        </div>
    </div>
</nav>