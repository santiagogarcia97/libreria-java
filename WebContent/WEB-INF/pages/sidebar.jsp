<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="col-lg-3">

  <h1 class="my-4">Bienvenido!</h1>
  
  <h5 class="">Categorias</h5>
  
  <div class="list-group list-group-flush">
	
	<c:if test="${requestScope.categorias != null && requestScope.categorias.size() != 0}">
	  <c:forEach var="cat" begin="0" items="${requestScope.categorias}">
	    <a href="/libreria-java/home?cat=${cat.getId()}" class="list-group-item">${cat.getDesc()}</a>
    	</c:forEach>	
	</c:if>	
  </div>

</div>