<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
    	<h2>Reportes: Prestamos</h2>
  		
  		
  		<c:set var = "totalPrestamos" scope = "session" value = "${0}"/>
  		<c:set var = "prep" scope = "session" value = "${0}"/>
  		<c:set var = "ret" scope = "session" value = "${0}"/>
  		<c:set var = "dev" scope = "session" value = "${0}"/>
  		<c:set var = "sum_lineas" scope = "session" value = "${0}"/>
  		<c:set var = "morosos" scope = "session" value = "${0}"/>
  		
  		
  		<c:forEach var="prestamo" begin="0" items="${requestScope.prestamos}">
  			<c:set var = "totalPrestamos" scope = "session" value = "${totalPrestamos + 1}"/>
  			<c:if test="${prestamo.getEstado() == \"preparacion\" }">
    			<c:set var = "prep" scope = "session" value = "${prep + 1}"/>
	    	</c:if>
	    	<c:if test="${prestamo.getEstado() == \"retiro\" }">
	    		<c:set var = "ret" scope = "session" value = "${ret + 1}"/>
	    	</c:if>
	    	<c:if test="${prestamo.getEstado() == \"devolucion\" }">
	    		<c:set var = "dev" scope = "session" value = "${dev + 1}"/>
	    		<c:if test="${prestamo.calc_dias_restantes() < 0 }">
	    			<c:set var = "morosos" scope = "session" value = "${morosos + 1}"/>
  				</c:if>
	    	</c:if>
  			
  			<c:set var = "sum_lineas" scope = "session" value = "${sum_lineas + prestamo.getLineas().size()}"/>
  			 
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
					Prestamos Activos:
				</th>
			    <td>
			    	 ${totalPrestamos}
			    </td>
     	    </tr>
     	    <c:if test="${totalPrestamos > 0 }">
     	    	<tr>	      	
				<th scope="row">
					Cantidad promedio de ejemplares:
				</th>
			    <td>
			    	${Math.round(sum_lineas/totalPrestamos)}
			    </td>
     	    </tr>
     	    <tr>	      	
				<th scope="row">
					Prestamos a preparar:
				</th>
			    <td>
			    	 ${prep}  &nbsp (${Math.round((prep/totalPrestamos)*100)}%)
			    </td>
     	    </tr>
     	    <tr>	      	
				<th scope="row">
					Prestamos a retirar:
				</th>
			    <td>
			    	 ${ret} &nbsp (${Math.round((ret/totalPrestamos)*100)}%)
			    </td>
     	    </tr>
     	    <tr>	      	
				<th scope="row">
					Prestamos a devolver:
				</th>
			    <td>
			    	 ${dev} &nbsp (${Math.round((dev/totalPrestamos)*100)}%)
			    </td>
     	    </tr>
     	    <tr>	      	
				<th scope="row">
					Prestamos Morosos:
				</th>
			    <td>
			    	 ${morosos} &nbsp (${Math.round((morosos/dev)*100)}% de los prestamos a devolver)
			    </td>
     	    </tr>
     	    </c:if>
     	    
		  </tbody>
		</table>
	</div>
	
	<br>
	
	

</div>


