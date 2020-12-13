<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="col-lg-3">

  <h1 class="my-4">Bienvenido!</h1>
  <c:if test="${sessionScope.loggedUser != null}">
  	<h5>Prestamos</h5>
  <div class="list-group list-group-flush">
    <a href="/libreria-java/prestamos/carrito" class="list-group-item">Carrito</a>
    <a href="/libreria-java/prestamos/prestamos-activos" class="list-group-item">Prestamos Activos</a>
  </div>
  <br>
  </c:if>
  <h5 class="">Categorias</h5>
  
  <div class="list-group list-group-flush">
	
	<c:if test="${requestScope.categorias != null && requestScope.categorias.size() != 0}">
	  <c:forEach var="cat" begin="0" items="${requestScope.categorias}">
	    <a href="/libreria-java/home?cat=${cat.getId()}" class="list-group-item">${cat.getDesc()}</a>
    	</c:forEach>	
	</c:if>	
  </div>

</div>