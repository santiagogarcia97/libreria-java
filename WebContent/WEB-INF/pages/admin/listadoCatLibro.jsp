<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
    	<h2>Gestión de Categorías</h2>
  
	</div>
	
	<br>
	
	
	<form id="form-mod" action="/libreria-java/admin/listado-cat-libro/modificar" method="post">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">ID</th>
		      <th scope="col">Descripcion</th>
			  <th scope="col">Estado</th>
			  <th scope="col"> </th>
			  <th scope="col"> </th>
		    </tr>
		  </thead>
		  
		  <tbody>
		  	<c:if test="${requestScope.categorias != null }">
			  	<c:forEach var="cat" begin="0" items="${requestScope.categorias}">
				  	<tr id="row-cat-${cat.getId()}">
				      	
				      	<th scope="row">
				      		<label id="id-lbl-${cat.getId()}">${cat.getId()}</label>
      						<input type="text" 
      							   class="form-control" 
      							   id="inputID-${cat.getId()}" 
      							   name="inputID" 
      							   required="required"
      							   hidden="true"
      							   disabled>
				      	</th>
				      	
				      	<td>
				      		<label id="des-lbl-${cat.getId()}">${cat.getDesc()}</label>
				      		<input type="text" 
      							   class="form-control" 
      							   id="inputDesc-${cat.getId()}" 
      							   name="inputDesc" 
      							   required="required"
      							   hidden="true"
      							   disabled
      							   maxlength=120>
				      	</td>
				      
				      	<td>
				      		<label id="est-lbl-${cat.getId()}">${cat.getEstado()}</label>
				      		<input type="text" 
      							   class="form-control" 
      							   id="inputEstado-${cat.getId()}" 
      							   name="inputEstado" 
      							   required="required"
      							   hidden="true"
      							   disabled
      							   maxlength=120>
				      	</td>
				      	
				      	<td> <button id="mod-btn-${cat.getId()}" 
				      			   class="btn btn-outline-secondary float-right"
				      			   type="button"
				      			   onClick="modificar(${cat.getId()})">Modificar</button></td>
				      			   
				      	<td> <button id="del-btn-${cat.getId()}" 
				      			   class="btn btn-outline-danger float-right"
				      			   type="button"
				      			   onClick="eliminar(${cat.getId()})">Eliminar</button></td>
			      	</tr>
		    	</c:forEach>	
			</c:if>	
		
		  </tbody>
		</table>
	</form>
	
	<form id="form-del" action="/libreria-java/admin/listado-cat-libro/eliminar" method="post" hidden="true">
		<input type="text" class="form-control" id="inputID-del" 
			   name="inputID" required="required" hidden="true">
	</form>
	
	<hr>
	<br>
	    <button id="btn-alta" class="btn btn-primary" onClick="toggle_alta_form()">Agregar</button>
	    <br>
	    <br>
		<form id="form-alta" action="/libreria-java/admin/listado-cat-libro/alta" method="post" hidden="true">
		
		<div class="form-group row">
    		<label for="inputDesc" class="col-sm-3 col-form-label">Descripción</label>
   			 <div class="col-sm-9">
      			<input type="text" class="form-control" id="inputDesc" name="inputDesc" placeholder="Descripción" required="required" maxlength=120>
    		</div>
  		</div>				
  	    <div class="form-group row">
	      <div class="col">
	        <button type="submit" class="btn btn-primary">Agregar</button>
	      </div>
	    </div> 	
	</form>
</div>
<script src="/libreria-java/resources/js/categorias.js"></script>


