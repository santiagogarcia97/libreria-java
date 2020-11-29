<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
    	<h2>Gestión de Sanciones</h2>
  
	</div>
	
	<br>
	
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">ID</th>
		      <th scope="col">Fecha</th>
			  <th scope="col">Cant. Dias</th>
			  <th scope="col">ID Usuario</th>
			  <th scope="col"> </th>
		    </tr>
		  </thead>
		  
		  <tbody>
		  	<c:if test="${requestScope.sanciones != null }">
			  	<c:forEach var="s" begin="0" items="${requestScope.sanciones}">
				  	<tr id="row-s-${s.getId()}">
				      	
				      	<th scope="row">
				      		<label id="id-lbl-${s.getId()}">${s.getId()}</label>
				      	</th>
				      	
				      	<td>
				      		<label id="fecha-lbl-${s.getId()}">${s.getFechaSancion()}</label>
				      	</td>
				      
				      	<td>
				      		<label id="dias-lbl-${s.getId()}">${s.getDiasSancion()}</label>
				      	</td>
				      	
				      	<td>
				      		<label id="user-lbl-${s.getId()}">${s.getIdUsuario()}</label>
				      	</td>
				      			   
				      	<td> <button id="del-btn-${s.getId()}" 
				      			   class="btn btn-outline-danger float-right"
				      			   type="button"
				      			   onClick="eliminar(${s.getId()})">Eliminar</button></td>
			      	</tr>
		    	</c:forEach>	
			</c:if>	
		
		  </tbody>
		</table>
	
	<form id="form-del" action="/libreria-java/admin/listado-sancion/eliminar" method="post" hidden="true">
		<input type="text" class="form-control" id="inputID-del" 
			   name="inputID" required="required" hidden="true">
	</form>
	

</div>
<script src="/libreria-java/resources/js/sanciones.js"></script>


