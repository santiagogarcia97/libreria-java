<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
		
		<c:set var = "tipo" scope = "session" value = "<%= request.getParameter(\"tipo\") %>"/>
    	<h2>Gestión de Prestamos</h2>
    	<c:if test="${tipo == \"preparacion\" }">
    		<h4 style="color:gray">Prestamos en Preparación</h4>
    	</c:if>
    	<c:if test="${tipo == \"retiro\" }">
    		<h4 style="color:gray">Prestamos para Retirar</h4>
    	</c:if>
    	<c:if test="${tipo == \"devolucion\" }">
    		<h4 style="color:gray">Prestamos a Devolver</h4>
    	</c:if>
    	
  
	</div>
	
	<br>
	
	
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">ID Usuario</th>
		  <th scope="col">Nombre y Apellido</th>
		  <th scope="col">Cantidad de Líneas</th>
		  <c:if test="${tipo == \"devolucion\" }">
    		<th scope="col">Fecha Max Devolucion</th>
		  	<th scope="col">Cantidad Dias Restantes</th>
    	</c:if>
		  <th scope="col"> </th>
	    </tr>
	  </thead>
	  
	  <tbody>
	  	<c:if test="${requestScope.prestamos != null }">
		  	<c:forEach var="prestamo" begin="0" items="${requestScope.prestamos}">
		  		<c:forEach var="u" begin="0" items="${requestScope.usuarios}">
		  			<c:if test="${u.getId() == prestamo.getSocioId()}">
		  				<c:set var = "usuario" scope = "session" value = "${u}"/>
		  			</c:if>
		  		</c:forEach>
		  		<c:if test="${prestamo.getEstado() == tipo}">
			  	<tr id="row-prestamo-${prestamo.getId()}">
			      	
			      	<th scope="row"><label id="id-lbl-${prestamo.getId()}">${prestamo.getId()}</label></th>
			      	
			      	<td><label id="id-user-lbl-${prestamo.getId()}">${usuario.getId()}</label></td>			      	
			      	
			      	<td><label id="nombre-apellido-lbl-${prestamo.getId()}">${usuario.getNombre()} ${usuario.getApellido()}</label></td>
			      	
			      	<td><label id="lineas-lbl-${prestamo.getId()}">${prestamo.getLineas().size()}</label></td>
			      	
			      	<c:if test="${tipo == \"devolucion\" }">
    					<td><label id="fecha-lbl-${prestamo.getId()}">${prestamo.calc_fecha_devolver()}</label></td>
    					<td><label id="dias-lbl-${prestamo.getId()}">${prestamo.calc_dias_restantes()}</label></td>
    				</c:if>
			      	
			      	<td> <button id="mod-btn-${prestamo.getId()}" 
			      			   class="btn btn-outline-secondary float-right"
			      			   type="button"
			      			   onClick="location.href = '/libreria-java/admin/edit-prestamo?id=${prestamo.getId()}'">Gestionar</button></td>
			      			   
		      	</tr>
		      	</c:if>
	    	</c:forEach>	
		</c:if>	
	
	  </tbody>
	</table>
	
	<form id="form-del" action="/libreria-java/admin/listado-prestamo/eliminar" method="post" hidden="true">
		<input type="text" class="form-control" id="inputID-del" 
			   name="inputID" required="required" hidden="true">
	</form>

	<hr>
</div>
<script src="/libreria-java/resources/js/prestamos.js"></script>

