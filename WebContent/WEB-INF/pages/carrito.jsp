<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
    	<h2>Carrito de Pedido</h2>
  
	</div>
	
	<br>
	
	<c:set var = "carrito" scope = "session" value = "${sessionScope.carrito}"/>
	<c:set var = "disponible" scope = "request" value = "${true}"/>
	
	<c:choose>
		<c:when test="${carrito != null }">
		
			<h3>Libros a agregar al pedido</h3>
				<table class="table">
				  <thead>
				    <tr>
				      <th scope="col">Tapa</th>
				      <th scope="col">Titulo</th>
					  <th scope="col">Autor</th>
					  <th scope="col">Ejemplares</th>
					  <th scope="col"> </th>
				    </tr>
				  </thead>
				  
				  <tbody>
				  		<!-- Uso un indice porque no se enumerar arrays en forEachs -->
				  		<c:set var = "i" scope = "request" value = "${0 }"/>
					  	<c:forEach var="libro" begin="0" items="${carrito}">
						  	<tr id="row-libro-${i}">
						      	
						      	<th scope="row">
						      		<img alt="Imagen no disponible" width="64px" src="${libro.getTapa()}">
						      	</th>
						      	
						      	<td>
						      		<label id="titulo-lbl-${i}">${libro.getTitulo()}</label>
						      	</td>
						      
						      	<td>
						      		<label id="autor-lbl-${i}">${libro.getAutor()}</label>
						      	</td>
						      	
						      	<td>
						      		<c:choose>
						      			<c:when test="${requestScope.nroEjemplares[i] > 0}">
						      				<label id="ejemplares-lbl-${i}">${requestScope.nroEjemplares[i]}</label>
						      			</c:when>
						      			<c:otherwise>
						      				<c:set var = "disponible" scope = "request" value = "${false}"/>
						      				<label style="color:red" id="ejemplares-lbl-${i}">No hay ejemplares disponibles</label>
						      			</c:otherwise>
						      		</c:choose>
						      	</td>
						      			   
						      	<td> <button id="del-btn-${i}" 
						      			   class="btn btn-outline-danger float-right"
						      			   type="button"
						      			   onClick="eliminar(${i})">Eliminar</button></td>
					      	</tr>
					      	
					    <c:set var = "i" scope = "request" value = "${ i + 1 }"/>
				    	</c:forEach>	
				
				  </tbody>
				</table>
	
	<form id="form-del" action="/libreria-java/prestamos/carrito/eliminar" method="post" hidden="true">
		<input type="text" class="form-control" id="index-del" 
			   name="index" required="required" hidden="true">
	</form>
	
	<hr>
	<br>
		<c:choose>
			<c:when test="${disponible && requestScope.sancionado.equals(\"No\") && requestScope.moroso.equals(\"No\")}">
			<form id="form-confirmar" action="/libreria-java/prestamos/carrito/confirmar" method="post" style="display: inline">
				<button id="btn-alta" class="btn btn-primary">Confirmar Préstamo</button>
			</form>
			
			</c:when>
			<c:otherwise>
				<button id="btn-alta" class="btn btn-secondary" disabled>Confirmar Préstamo</button>
			</c:otherwise>
		</c:choose>
	    <a href="/libreria-java/home" id="btn-alta" class="btn btn-outline-secondary">Agregar más libros</a>
	    <br>
	    <br>
	    
		</c:when>
		<c:otherwise>
			<hr>
			<h4>Tu carrito está vacío. <a href="/libreria-java/home">Explora nuestros libros</a> y agrégalos al carrito para crear un nuevo pedido.</h4>
		</c:otherwise>
	</c:choose>
	<c:if test="${requestScope.sancionado.equals(\"Si\")}">
		<label style="color: red">Usted está sancionado, por lo que no puede hacer un pedido hasta que finalice su sanción.</label>
	</c:if>	
	<c:if test="${requestScope.moroso.equals(\"Si\")}">
		<label style="color: red">Usted ha excedido el tiempo de devolución de uno de sus prestamos, por lo que no puede hacer un pedido hasta devolver su préstamo pendiente.</label>
	</c:if>	
</div>
<script src="/libreria-java/resources/js/carrito.js"></script>


