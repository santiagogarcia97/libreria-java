<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
    	<h2>Gestión de Libros</h2>
  
	</div>
	
	<br>
	
	
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">Titulo</th>
		  <th scope="col">Autor</th>
		  <th scope="col"> </th>
		  <th scope="col"> </th>
	    </tr>
	  </thead>
	  
	  <tbody>
	  	<c:if test="${requestScope.libros != null }">
		  	<c:forEach var="libro" begin="0" items="${requestScope.libros}">
			  	<tr id="row-libro-${libro.getId()}">
			      	
			      	<th scope="row"><label id="id-lbl-${libro.getId()}">${libro.getId()}</label></th>
			      	
			      	<td><label id="titulo-lbl-${libro.getId()}">${libro.getTitulo()}</label></td>
			      
			      	<td><label id="autor-lbl-${libro.getId()}">${libro.getAutor()}</label></td>
			      	
			      	<td> <button id="mod-btn-${libro.getId()}" 
			      			   class="btn btn-outline-secondary float-right"
			      			   type="button"
			      			   onClick="location.href = '/libreria-java/admin/edit-libro?id=${libro.getId()}'">Modificar</button></td>
			      			   
			      	<td> <button id="del-btn-${libro.getId()}" 
			      			   class="btn btn-outline-danger float-right"
			      			   type="button"
			      			   onClick="eliminar(${libro.getId()})">Eliminar</button></td>
		      	</tr>
	    	</c:forEach>	
		</c:if>	
	
	  </tbody>
	</table>
	
	<form id="form-del" action="/libreria-java/admin/listado-libro/eliminar" method="post" hidden="true">
		<input type="text" class="form-control" id="inputID-del" 
			   name="inputID" required="required" hidden="true">
	</form>
	
	<hr>
	<br>
	    <button id="btn-alta" class="btn btn-primary" onClick="location.href = '/libreria-java/admin/alta-libro'">Agregar</button>
</div>
<script src="/libreria-java/resources/js/libros.js"></script>

