<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
    	<h2>Modificar Usuario</h2>
    	<hr>
	</div>
	<c:if test="${sessionScope.errorMsg != null && sessionScope.errorMsg !='' }">
        <br>
          	<div class="text-center text-danger">
          		<c:out value="${sessionScope.errorMsg}"></c:out>
          		<c:set var="errorMsg" value="" scope="session"  />
          	</div>
         <br>            
    </c:if>
    
	<c:set var = "id" scope = "session" value = "<%= request.getParameter(\"id\") %>"/>
	
	<c:forEach var="u" begin="0" items="${requestScope.usuarios}">
		<c:if test="${u.getId() == id}">
			<c:set var = "usuario" scope = "session" value = "${u}"/>
		</c:if>
	</c:forEach>		  

	<form action="/libreria-java/admin/edit-usuario" method="post">
		<div class="form-group row">
    		<label for="inputID" class="col-sm-4 col-form-label">ID</label>
   			 <div class="col-sm-8">
      			<input type="text" class="form-control" id="inputID" name="inputID" placeholder="ID" required="required" readonly value="${usuario.getId() }">
    		</div>
  		</div>	
		
		<div class="form-group row">
    		<label for="inputNombre" class="col-sm-4 col-form-label">Nombre</label>
   			 <div class="col-sm-8">
      			<input type="text" class="form-control" id="inputNombre" name="inputNombre" placeholder="Nombre" required="required" value="${usuario.getNombre() }">
    		</div>
  		</div>			
		<div class="form-group row">
    		<label for="inputApellido" class="col-sm-4 col-form-label">Apellido</label>
   			 <div class="col-sm-8">
      			<input type="text" class="form-control" id="inputApellido" name="inputApellido" placeholder="Apellido" required="required" value="${usuario.getApellido() }">
    		</div>
  		</div>		
		<div class="form-group row">
    		<label for="inputDNI" class="col-sm-4 col-form-label">DNI</label>
   			 <div class="col-sm-8">
      			<input type="text" class="form-control" id="inputDNI" name="inputDNI" placeholder="DNI" required="required" value="${usuario.getDni() }">
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="inputDomicilio" class="col-sm-4 col-form-label">Domicilio</label>
   			 <div class="col-sm-8">
      			<input type="text" class="form-control" id="inputDomicilio" name="inputDomicilio" placeholder="Domicilio" required="required" value="${usuario.getDomicilio() }">
    		</div>
  		</div>			
		<div class="form-group row">
    		<label for="inputEmail" class="col-sm-4 col-form-label">Email</label>
   			 <div class="col-sm-8">
      			<input type="text" class="form-control" id="inputEmail" name="inputEmail" placeholder="Email" required="required" readonly value="${usuario.getEmail() }">
    		</div>
  		</div>		
		<div class="form-group row">
    		<label for="inputTelefono" class="col-sm-4 col-form-label">Teléfono</label>
   			 <div class="col-sm-8">
      			<input type="text" class="form-control" id="inputTelefono" name="inputTelefono" placeholder="Telefono" required="required" value="${usuario.getTelefono() }">
    		</div>
  		</div>
  		
		<div class="form-group row">
    		<label for="inputTipo" class="col-sm-4 col-form-label">Tipo</label>
   			 <div class="col-sm-8">
   			 	<select  class="form-control" id="inputTipo" name="inputTipo" required="required">
   			 		<c:if test="${usuario.getTipoUsuario() == \"admin\"}">
						<option value="admin" selected> Administrador </option>
						<option value="socio"> Socio </option>
					</c:if>
					<c:if test="${usuario.getTipoUsuario() == \"socio\"}">
						<option value="admin"> Administrador </option>
						<option value="socio" selected> Socio </option>
					</c:if>
   			 	</select>
    		</div>
  		</div>	
  				
		<div class="form-group row">
    		<label for="inputPSWD" class="col-sm-4 col-form-label">Password</label>
   			 <div class="col-sm-8">
      			<input type="password" class="form-control" id="inputPSWD" name="inputPSWD" placeholder="Password" required="required" readonly value="${usuario.getPassword() }">
    		</div>
  		</div>
				
  	    <div class="form-group row">
	      <div class="col">
	        <button type="submit" class="btn btn-primary float-right">Aceptar</button>
	      </div>
	    </div> 			
	</form>



</div>