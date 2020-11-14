<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
		
    	<h2>Prestamos Activos</h2>
  
	</div>
	
	<br>
	
	
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">ID</th>
		  <th scope="col">Cantidad de Libros</th>
		  <th scope="col">Fecha de Realización</th>
		  <th scope="col">Estado</th>
	    </tr>
	  </thead>
	  
	  <tbody>
	  	<c:if test="${requestScope.prestamos != null }">
		  	<c:forEach var="prestamo" begin="0" items="${requestScope.prestamos}">
		  		
		  		<c:if test="${sessionScope.loggedUser.getId() == prestamo.getSocioId()}">
			  	<tr id="row-prestamo-${prestamo.getId()}">
			      	
			      	<th scope="row"><label id="id-lbl-${prestamo.getId()}">${prestamo.getId()}</label></th>
			      	
			      	<td><label id="lineas-lbl-${prestamo.getId()}">${prestamo.getLineas().size()}</label></td>
			      	
			      	<td><label id="fecha-lbl-${prestamo.getId()}">${prestamo.getFechaHoraSolicitud()}</label></td>			      	
			      	
			      	<td><label id="estado-lbl-${prestamo.getId()}">${prestamo.getEstado()}</label></td>
			      	
			      	
		      	</tr>
		      	</c:if>
	    	</c:forEach>	
		</c:if>	
	
	  </tbody>
	</table>
	
	<hr>
</div>
<script src="/libreria-java/resources/js/prestamos.js"></script>

