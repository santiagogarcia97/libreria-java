<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
    	<h2>Gestión de Prestamo</h2>
  
	</div>
	
	
	<c:set var = "id" scope = "session" value = "<%= request.getParameter(\"id\") %>"/>
	
	<c:forEach var="p" begin="0" items="${requestScope.prestamos}">
		<c:if test="${p.getId() == id}">
			<c:set var = "prestamo" scope = "session" value = "${p}"/>
		</c:if>
	</c:forEach>
	<hr>
	<form id="form-confirmar" action="/libreria-java/admin/edit-prestamo/confirmar" method="post">
		<input type="text" class="form-control" id="inputIDPrestamo-add" 
				   name="inputIDPrestamo" required="required" hidden="true" value=${ prestamo.getId() }>
		<c:choose>
			<c:when test="${prestamo.getEstado() == \"preparacion\" }">
				<button id="btn-alta" class="btn btn-warning">Marcar como preparado</button> 
			</c:when>
			<c:when test="${prestamo.getEstado() == \"retiro\" }">
				<button id="btn-alta" class="btn btn-warning">Marcar como retirado</button> 
			</c:when>
			<c:when test="${prestamo.getEstado() == \"devolucion\" }">
				<button id="btn-alta" class="btn btn-warning">Marcar como devuelto</button> 
			</c:when>
			<c:otherwise>
				<h5 style="color:red">Error</h5>
				Estado:  ${prestamo.getEstado()}
			</c:otherwise>
		</c:choose>
	</form>
	<br>
	<br>
	<h3>Lineas de Prestamo</h3>
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">ID</th>
		      <th scope="col">Titulo</th>
			  <th scope="col">Autor</th>
			  <th scope="col"> </th>
		    </tr>
		  </thead>
		  
		  <tbody>
		  	<c:if test="${prestamo.getLineas() != null }">
			  	<c:forEach var="linea" begin="0" items="${prestamo.getLineas()}">
				  	<tr id="row-linea-${linea.getId()}">
				      	
				      	<th scope="row">
				      		<label id="id-lbl-${linea.getId()}">${linea.getId()}</label>
				      	</th>
				      	
				      	<td>
				      		<label id="titulo-lbl-${linea.getId()}">${linea.ejemplar.libro.getTitulo()}</label>
				      	</td>
				      
				      	<td>
				      		<label id="autor-lbl-${linea.getId()}">${linea.ejemplar.libro.getAutor()}</label>
				      	</td>
				      	
				      			   
				      	<td> <button id="del-btn-${linea.getId()}" 
				      			   class="btn btn-outline-danger float-right"
				      			   type="button"
				      			   onClick="eliminar(${linea.getId()})">Eliminar</button></td>
			      	</tr>
		    	</c:forEach>	
			</c:if>	
		
		  </tbody>
		</table>
	
	<form id="form-del" action="/libreria-java/admin/edit-prestamo/eliminar-linea" method="post" hidden="true">
		<input type="text" class="form-control" id="inputID-del" 
			   name="inputID" required="required" hidden="true">
		<input type="text" class="form-control" id="inputIDPrestamo-del" 
			   name="inputIDPrestamo" required="required" hidden="true" value=${ prestamo.getId() }>  
	</form>
	
	<hr>
	    <button id="btn-alta" class="btn btn-primary" onClick="toggle_alta_form()">Agregar Linea</button>
	    <form id="form-baja-p" action="/libreria-java/admin/edit-prestamo/eliminar-prestamo" method="post" style="display: inline">
	    	<input type="text" class="form-control" id="inputIDPrestamo-del" 
			   name="inputIDPrestamo" required="required" hidden="true" value=${ prestamo.getId() }>
	    	<button id="btn-baja" class="btn btn-danger">Eliminar Préstamo</button>
	    </form>
	    <br>
	    <br>
	<form id="form-alta" action="/libreria-java/admin/edit-prestamo/agregar-linea" method="post" hidden="true">
		<input type="text" class="form-control" id="inputIDPrestamo-add" 
			   name="inputIDPrestamo" required="required" hidden="true" value=${ prestamo.getId() }>
		<div class="form-group row">
    		<label for="selectEjemplar" class="col-sm-3 col-form-label">Ejemplar</label>
   			 <div class="col-sm-8">
				<select class="form-control" id="inputEjId" name="inputEjId">
					<c:if test="${requestScope.ejemplares != null}">
					  <option disabled selected hidden="true"> -- Por favor escoga un ejemplar -- </option>
					  <c:forEach var="ej" begin="0" items="${requestScope.ejemplares}">
   			 		  	<option value="${ej.getId()}"> ${ej.getId()} - ${ej.getLibro().getTitulo()} - ${ej.getLibro().getAutor() } </option>					  	
				      </c:forEach>	
					</c:if>	
				</select>
    		</div>
  		</div>				
  	    <div class="form-group row">
	      <div class="col">
	        <button type="submit" class="btn btn-primary">Agregar</button>
	      </div>
	    </div> 	
	</form>

</div>
<script src="/libreria-java/resources/js/prestamos.js"></script>


