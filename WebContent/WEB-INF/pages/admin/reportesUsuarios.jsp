<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
    	<h2>Reportes: Usuarios</h2>
  		
  		
  		<c:set var = "totalUsuarios" scope = "session" value = "${0}"/>
  		<c:set var = "socios" scope = "session" value = "${0}"/>
  		<c:set var = "admins" scope = "session" value = "${0}"/>
  		<c:set var = "activos" scope = "session" value = "${0}"/>
  		<c:set var = "morosos" scope = "session" value = "${0}"/>
  		<c:set var = "sancionados" scope = "session" value = "${0}"/>
  		
  		
  		<c:forEach var="usuario" begin="0" items="${requestScope.usuarios}">
  			<c:set var = "totalUsuarios" scope = "session" value = "${totalUsuarios + 1}"/>
  			<c:if test="${usuario.getTipoUsuario() == \"socio\" }">
  				<c:set var = "socios" scope = "session" value = "${socios + 1}"/>
  			</c:if>
  			<c:if test="${usuario.getTipoUsuario() == \"admin\" }">
  				<c:set var = "admins" scope = "session" value = "${admins + 1}"/>
  			</c:if>
  			<c:set var = "tieneActivos" scope = "session" value = "${false}"/>
  			<c:set var = "tieneMorosos" scope = "session" value = "${false}"/>
  			<c:forEach var="prestamo" begin="0" items="${requestScope.prestamos}">
  				<c:if test="${prestamo.getSocioId() == usuario.getId() }">
  					<c:set var = "tieneActivos" scope = "session" value = "${true}"/>
  					<c:if test="${prestamo.getEstado() == \"devolucion\" }">
	  					<c:if test="${prestamo.calc_dias_restantes() < 0 }">
	  						<c:set var = "tieneMorosos" scope = "session" value = "${true}"/>
	  					</c:if>
  					</c:if>
  				</c:if>
  			</c:forEach>
  			<c:if test="${tieneActivos }">
  				<c:set var = "activos" scope = "session" value = "${activos + 1}"/>
  			</c:if>
  			<c:if test="${tieneMorosos }">
  				<c:set var = "morosos" scope = "session" value = "${morosos + 1}"/>
  			</c:if>  			
  			<c:set var = "tieneSanciones" scope = "session" value = "${false}"/>
  			<c:forEach var="sancion" begin="0" items="${requestScope.sanciones}">
  				<c:if test="${sancion.getIdUsuario() == usuario.getId() }">
  					<c:set var = "tieneSanciones" scope = "session" value = "${true}"/>
  				</c:if>
  			</c:forEach>
  			<c:if test="${tieneSanciones }">
  				<c:set var = "sancionados" scope = "session" value = "${sancionados + 1}"/>
  			</c:if> 
 		</c:forEach>
  		
  		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col"> </th>
		      <th scope="col"> </th>
		    </tr>
		  </thead>
		  <tbody>
		  <tr>	      	
				<th scope="row">
					Usuarios Activos:
				</th>
			    <td>
			    	 ${totalUsuarios}
			    </td>
     	    </tr>
     	    <c:if test="${totalUsuarios > 0 }">
     	    	<tr">	      	
				<th scope="row">
					Socios:
				</th>
			    <td>
			    	 ${socios} &nbsp  (${Math.round((socios/totalUsuarios)*100)}%)
			    </td>
     	    </tr>
     	    <tr">	      	
				<th scope="row">
					Administradores:
				</th>
			    <td>
			    	 ${admins}  &nbsp (${Math.round((admins/totalUsuarios)*100)}%)
			    </td>
     	    </tr>
     	    <tr">	      	
				<th scope="row">
					Usuarios con prestamos activos:
				</th>
			    <td>
			    	 ${activos} &nbsp (${Math.round((activos/totalUsuarios)*100)}%)
			    </td>
     	    </tr>
     	    <tr">	      	
				<th scope="row">
					Usuarios morosos:
				</th>
			    <td>
			    	 ${morosos} &nbsp (${Math.round((morosos/totalUsuarios)*100)}%)
			    </td>
     	    </tr>
     	    <tr>	      	
				<th scope="row">
					Usuarios sancionados:
				</th>
			    <td>
			    	 ${sancionados} &nbsp (${Math.round((sancionados/totalUsuarios)*100)}%)
			    </td>
     	    </tr>
     	    </c:if>
     	    
		  </tbody>
		</table>
	</div>
	
	<br>
	
	

</div>


