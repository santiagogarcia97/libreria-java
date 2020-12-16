<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand mb-0 h1" href="/libreria-java/home">Libreria
			UTN</a>
		<button class="navbar-toggler collapsed" type="button"
			data-toggle="collapse" data-target="#navbarCollapse">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="dropdown-menu-style collapse navbar-collapse"
			id="navbarCollapse">
			<ul class="navbar-nav mr-auto">

				<c:choose>
					<c:when
						test="${sessionScope.loggedUser.getTipoUsuario() eq 'admin'}">
						<li class="nav-item"><a class="nav-link"
							href="/libreria-java/admin/panel">Panel Administrador</a></li>
					</c:when>

					<c:when
						test="${sessionScope.loggedUser.getTipoUsuario() eq 'socio'}">
						<li class="nav-item"><a class="nav-link"
							href="/libreria-java/prestamos/prestamos-activos">Mis Prestamos</a>
						</li>
					</c:when>
				</c:choose>

				<li class="nav-item"><a class="nav-link" href="https://github.com/santiagogarcia97/libreria-java/blob/master/README.md">Acerca de</a>
				</li>
			</ul>

			<c:choose>
				<c:when test="${sessionScope.loggedUser != null}">
				<div style="width: 42px">
					<c:choose>
						<c:when test="${sessionScope.carrito.size() == null}">
							<a href="/libreria-java/prestamos/carrito">
								<img alt="Carrito" width="20px" src="${pageContext.request.contextPath}/resources/images/shopping-cart-empty.png">
							</a>
						</c:when>
						<c:otherwise>
							<a href="/libreria-java/prestamos/carrito">
								<img alt="Carrito" width="24px" src="${pageContext.request.contextPath}/resources/images/shopping-cart-full.png">
							</a>
						</c:otherwise>
					</c:choose>

				</div>
					<ul class="navbar-nav ">
						
						<li class="nav-item dropdown"><a href="#"
							class="nav-link dropdown-toggle" id="navDropDownLink"
							data-toggle="dropdown"> 
								<c:out value="${sessionScope.loggedUser.getNombre()}"></c:out>
								<c:out value=" "></c:out> 
								<c:out value="${sessionScope.loggedUser.getApellido()}"></c:out>
						</a>
							<div class="dropdown-menu dropdown-menu-right">
								<a class="dropdown-item" href="#">Perfil</a>
								<div class="dropdown-divider"></div>
								<form action="/libreria-java/auth/logout" method="post">
									<button type="submit" class="dropdown-item">Log Out</button>
								</form>
							</div></li>
					</ul>
				</c:when>

				<c:otherwise>
					<a class="btn btn-outline-primary mr-1"
						href="/libreria-java/auth/login">Ingresar</a>
					<a class="btn btn-primary" href="/libreria-java/auth/signup">Registrarse</a>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
</nav>