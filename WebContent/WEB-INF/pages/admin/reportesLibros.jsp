<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="https://cdn.anychart.com/js/8.0.1/anychart-core.min.js"></script>
<script src="https://cdn.anychart.com/js/8.0.1/anychart-pie.min.js"></script>

<div class="col-lg-9 p-4 bg-light 100">
		
		<c:set var = "totalLibros" scope = "session" value = "${0}"/>
		<c:set var = "prestados" scope = "session" value = "${0}"/>
		
    	<h2>Reportes: Libros</h2>
    	<c:set var = "str" scope = "session" value = ""/>
    	<c:forEach var="libro" begin="0" items="${requestScope.libros}">
    		<c:set var = "totalLibros" scope = "session" value = "${totalLibros + 1}"/>
    		<c:set var = "str" scope = "session" value = "${str.concat(libro.getCat().getDesc()) }"/>
    		<c:set var = "str" scope = "session" value = "${str.concat(\", \") }"/>
    		<c:set var = "en_prestamo" scope = "session" value ="${false }"/>
    		<c:forEach var="prestamo" begin="0" items="${requestScope.prestamos}">
    			<c:forEach var="linea" begin="0" items="${prestamo.getLineas()}">
    				<c:if test="${linea.getEjemplar().getLibro().getId() == libro.getId() }">
    					<c:set var = "en_prestamo" scope = "session" value ="${true }"/>
    				</c:if>
    			</c:forEach>
    		</c:forEach>
    		<c:if test="${en_prestamo }">
    			<c:set var = "prestados" scope = "session" value = "${prestados + 1}"/>
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
		  <tr id="row-cat-${cat.getId()}">	      	
				<th scope="row">
					Libros:
				</th>
			    <td>
			    	 ${totalLibros}
			    </td>
     	    </tr>
     	    <c:if test="${totalLibros > 0 }">
     	    	<tr>	      	
				<th scope="row">
					Libros presentes en uno o más prestamos activos:
				</th>
			    <td>
			    	 ${prestados} &nbsp  (${Math.round((prestados/totalLibros)*100)}%)
			    </td>
     	    </tr>
     	    </c:if>
     	    
		  </tbody>
		</table>
		
		<hr>
    	<h4>Categorias</h4>
		<input id='cats' value="${str}" hidden=true>
  		<div id="container" style="width: 100%; height: 100%"></div>
  		
	<br>

</div>
<script src="/libreria-java/resources/js/reportes.js"></script>


