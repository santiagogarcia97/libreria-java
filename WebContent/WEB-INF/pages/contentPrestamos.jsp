<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="col-lg-9 p-4 bg-light h-100">
	<div class="form-group">
		<h2>Prestamo actual</h2>
	</div>

	<c:choose>
		<c:when
			test="${sessionScope.prestamoActual != null && sessionScope.prestamoActual.size() > 0 }">
			<div class="row">
				<table class="table text-center">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Tapa</th>
							<th scope="col">Titulo</th>
							<th scope="col">Autor</th>
							<th scope="col">Max dias</th>
							<th scope="col">Eliminar</th>
						</tr>
					</thead>

					<tbody>
						<c:set var="count" value="0" scope="page" />
						<c:forEach var="libro" begin="0"
							items="${sessionScope.prestamoActual}">
							<tr>
								<th scope="row" class="align-middle"><c:out
										value="${count + 1}"></c:out></th>
								<td class="align-middle"><img class="img-fluid"
									height="200" width="150" src="${libro.getTapa()}"
									alt="Imagen no disponible"></td>
								<td class="align-middle"><c:out
										value="${libro.getTitulo()}"></c:out></td>
								<td class="align-middle"><c:out value="${libro.getAutor()}"></c:out></td>
								<td class="align-middle"><c:out
										value="${libro.getDiasMaxPrestamo()}"></c:out></td>
								<td class="align-middle"><a
									href="/libreria-java/prestamos?action=remove&index=${count}"
									class="btn btn-sm btn-danger">X</a></td>
							</tr>
							<c:set var="count" value="${count + 1}" scope="page" />
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="text-center">
				<hr>
				<a href="#" class="btn btn-lg btn-primary">Solicitar Prestamo</a>
			</div>
		</c:when>
		<c:otherwise>
		No hay libros seleccionados
		</c:otherwise>
	</c:choose>


</div>