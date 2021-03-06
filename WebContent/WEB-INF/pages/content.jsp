<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="col-lg-9">
<!-- 
	<div id="carouselExampleIndicators" class="carousel slide my-4"
		data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<div class="carousel-item active">
				<img class="d-block img-fluid" src="http://placehold.it/900x200"
					alt="First slide">
			</div>
			<div class="carousel-item">
				<img class="d-block img-fluid" src="http://placehold.it/900x200"
					alt="Second slide">
			</div>
			<div class="carousel-item">
				<img class="d-block img-fluid" src="http://placehold.it/900x200"
					alt="Third slide">
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>


 -->
	<c:choose>
		<c:when
			test="${requestScope.libros != null && requestScope.libros.size() !=0}">
			<div class="px-4 pt-4">
				<h2 class="m-0">Libros</h2>
			</div>
			<hr>
			<div class="row">
				<c:forEach var="libro" begin="0" items="${requestScope.libros}">

					<div class="col-lg-3 col-md-4 col-6 mb-4">
						<div class="card h-100">
							<a href="/libreria-java/home?libro=${libro.getId()}"> <img
								class="card-img-top img-fluid" src="${libro.getTapa()}"
								alt="Imagen no disponible">
							</a>
							<div class="card-body">
								<h6 class="card-title">
									<a href="/libreria-java/home?libro=${libro.getId()}"> <c:out
											value="${libro.getTitulo()}"></c:out>
									</a>
								</h6>
								<h6>
									<c:out value="${libro.getAutor()}"></c:out>
								</h6>
								<p class="card-text">${libro.getCat().getDesc()}</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- /.row -->
		</c:when>

		<c:when test="${requestScope.libro != null }">
			
			<c:set var = "en_carrito" scope = "request" value = "${false}"/>
			<c:forEach var="l" begin="0" items="${sessionScope.carrito}">
				<c:if test="${l.getTitulo() == libro.getTitulo() }">
					<c:set var = "en_carrito" scope = "request" value = "${true}"/>
				</c:if>
			</c:forEach>
		
			<div class="container-fluid">
				<div class="px-4 pt-4">
					<h2 class="m-0">${libro.getTitulo()}</h2>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-auto">
						<img alt="Imagen no disponible" width="250px" src="${libro.getTapa()}">
					</div>
					<div class="col">
						<h5>Autor: ${libro.getAutor()}</h5>
						<h5>ISBN: ${libro.getIsbn()}</h5>
						<h5>Edición: ${libro.getEdicion()}</h5>
						<h5>Fecha edición: ${libro.getFechaEdicion()}</h5>
						<br>
						<h5>Maximo dias de prestamo: ${libro.getDiasMaxPrestamo()}</h5>
						<form id="form-add" action="/libreria-java/prestamos/agregar" method="post">
							<input type="text" class="form-control" id="inputIDLibro" 
			   				name="inputIDLibro" required="required" hidden="true" value=${ libro.getId() }>
			   				<c:choose>
			   					<c:when test="${sessionScope.loggedUser == null}">
			   						<button class="btn btn-primary" type="submit" disabled>Agregar al prestamo</button>
			   						<br>
			   						<label style="color: red">Debe iniciar sesión para pedir un libro.</label>
			   					</c:when>
			   					<c:when test="${en_carrito}">
			   						<button class="btn btn-primary" type="submit" disabled>Agregar al prestamo</button>
			   						<br>
			   						<label style="color: red">Ya tiene este libro en el carrito.</label>
			   					</c:when>
			   					<c:when test="${sessionScope.loggedUser != null}">
			   						<button class="btn btn-primary" type="submit">Agregar al prestamo</button>
			   					</c:when>
			   				</c:choose>
						</form>
					</div>
				</div>
			</div>
		</c:when>

		<c:otherwise>
			<div class="col">
				<h4>No hay resultados.</h4>
			</div>
		</c:otherwise>
	</c:choose>


</div>
<!-- /.col-lg-9 -->