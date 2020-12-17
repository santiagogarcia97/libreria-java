<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
    	<h2>Gestión de Usuarios</h2>
  
	</div>
	
	<br>
	
	
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">Nombre y Apellido</th>
		  <th scope="col">Tipo</th>
		  <th scope="col">Moroso</th>
		  <th scope="col">Sancionado</th>
		  <th scope="col"> </th>
		  <th scope="col"> </th>
	    </tr>
	  </thead>
	  
	  <tbody>
	  	<c:if test="${requestScope.usuarios != null }">
		  	<c:forEach var="usuario" begin="0" items="${requestScope.usuarios}">
			  	<tr id="row-usuario-${usuario.getId()}">
			      	
			      	<th scope="row"><label id="id-lbl-${usuario.getId()}">${usuario.getId()}</label></th>
			      	
			      	<td><label id="nombre-lbl-${usuario.getId()}">${usuario.getNombre()} ${usuario.getApellido()}</label></td>
			      
			      	<td><label id="tipo-lbl-${usuario.getId()}" style="text-transform: capitalize">${usuario.getTipoUsuario()}</label></td>
			      	
			      	<td><label id="sancionado-lbl-${usuario.getId()}">${usuario.getMoroso()}</label></td>
			      	
			      	<td><label id="sancionado-lbl-${usuario.getId()}">${usuario.getSancionado()}</label></td>
			      	
			      	<td> <button id="mod-btn-${usuario.getId()}" 
			      			   class="btn btn-outline-secondary float-right"
			      			   type="button"
			      			   onClick="location.href = '/libreria-java/admin/edit-usuario?id=${usuario.getId()}'">Modificar</button></td>
			      			   
			      	<td> <button id="del-btn-${usuario.getId()}" 
			      			   class="btn btn-outline-danger float-right"
			      			   type="button"
			      			   onClick="eliminar(${usuario.getId()})">Eliminar</button></td>
		      	</tr>
	    	</c:forEach>	
		</c:if>	
	
	  </tbody>
	</table>
	
	<form id="form-del" action="/libreria-java/admin/listado-usuario/eliminar" method="post" hidden="true">
		<input type="text" class="form-control" id="inputID-del" 
			   name="inputID" required="required" hidden="true">
	</form>
	
	<hr>
	<br>
	    <button id="btn-alta" class="btn btn-primary" onClick="location.href = '/libreria-java/admin/alta-usuario'">Agregar</button>
</div>
<script src="/libreria-java/resources/js/usuarios.js"></script>

