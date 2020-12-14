<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
    	<h2>Ejemplares</h2>
  
	</div>
	
	<br>
	<c:set var = "libroID" scope = "session" value = "<%= request.getParameter(\"id\") %>"/>
	<select  class="form-control" id="inputLibro" name="inputLibro" onchange="selectLibro()">
		<c:if test="${requestScope.libros != null && requestScope.libros.size() != 0 }">
		  <option disabled selected hidden="true"> -- Por favor escoga un libro -- </option>
		  <c:forEach var="libro" begin="0" items="${requestScope.libros}">
		  		<c:if test="${libroID != null}">
		  			<c:if test="${libro.getId() == libroID }">
		  				<option value="${libro.getId()}" selected> ${libro.getTitulo()} </option>
		  			</c:if>
		  			<c:if test="${libro.getId() != libroID }">
		  				<option value="${libro.getId()}"> ${libro.getTitulo()} </option>
		  			</c:if>
		  		</c:if>
		  		<c:if test="${libroID == null}">
		  			<option value="${libro.getId()}"> ${libro.getTitulo()} </option>
		  		</c:if>
	       </c:forEach>	
		</c:if>	
		
		</select>
	
	<c:if test="${libroID != null}">
		<c:set var = "ejCount" scope = "session" value = "0"/>	
		<c:forEach var="ejemplar" begin="0" items="${requestScope.ejemplares}">
			<c:if test ="${ejemplar.libro.getId() == libroID}">
				<c:set var = "ejCount" scope = "session" value = "${ejCount + 1 }"/>
			</c:if>
						  			
		</c:forEach>
		
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col"></th>
		      <th scope="col"></th>
			  <th scope="col"></th>
			  <th scope="col"> </th>
		    </tr>
		  </thead>
		  <tbody>
		  	<tr>
		  		<td width="270px" style="vertical-align: middle">Cantidad de ejemplares en stock:</td>
		  		<td  width="270px" style="vertical-align: middle"><b>${ejCount}</b></td>
		  		<td><button id="btn-alta" class="btn btn-outline-primary" onClick="toggle_alta_form()">Agregar</button></td>
		  		<td><button id="btn-baja" class="btn btn-outline-danger" onClick="toggle_baja_form()">Eliminar</button></td>
		  	</tr>
		  </tbody>
		</table>
		
			
		<form id="form-alta" action="/libreria-java/admin/ejemplares/alta" method="post" hidden="true">
			<hr>
			<br>
			<div class="form-group row">
				<label for="inputDesc" class="col-sm-5 col-form-label">Elegir cantidad de ejemplares a agregar</label>
		  		<div class="col-sm-5" align="right">
		     			<input type="number" class="form-control" id="inputCantEj" name="inputCantEj" min="1" max="10" placeholder="Cantidad ejemplares" required="required">
		     			<input type="text" class="form-control" id="inputLibroID" name="inputLibroID" required="required" value="${libroID }" hidden="true">
		   		</div>
		   		<div class="col">
		        	<button type="submit" class="btn btn-primary">Agregar</button>
		      	</div>
		 	</div>				
		</form>	
			
			
		<form id="form-baja" action="/libreria-java/admin/ejemplares/baja" method="post" hidden="true">
			<hr>
			<br>
			<div class="form-group row">
		   		<label for="inputDesc" class="col-sm-3 col-form-label">Elegir ID de ejemplar a eliminar</label>
		  		<div class="col-sm-7" align="right">
		     			<select  class="form-control" id="inputEjemplar" name="inputEjemplar" required="required">
							<c:if test="${requestScope.libros != null && requestScope.ejemplares.size() != 0 }">
							  <c:forEach var="ejemplar" begin="0" items="${requestScope.ejemplares}">
							  		<c:if test ="${ejemplar.libro.getId() == libroID}">
							  			<option value="${ejemplar.getId()}"> ${ejemplar.getId()} </option>
							  		</c:if>
							  			
						       </c:forEach>	
							</c:if>	
		 				</select>
		 				<input type="text" class="form-control" id="inputLibroID" name="inputLibroID" required="required" value="${libroID}" hidden="true">
		   		</div>
		   		<div class="col">
		   			<button type="submit" class="btn btn-danger">Eliminar</button>
		   		</div>
		 	</div>		

		</form>
</c:if>
<c:if test="${sessionScope.errorMsg != null && sessionScope.errorMsg !='' }">
		<br>
	    	<div class="text-center text-danger">
	        	<c:out value="${sessionScope.errorMsg}"></c:out>
	            <c:set var="errorMsg" value="" scope="session"  />
	        </div>            
     </c:if>
</div>
<script src="/libreria-java/resources/js/ejemplares.js"></script>


