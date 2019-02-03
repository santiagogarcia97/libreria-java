<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="col-lg-9">

  <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
      <div class="carousel-item active">
        <img class="d-block img-fluid" src="http://placehold.it/900x200" alt="First slide">
      </div>
      <div class="carousel-item">
        <img class="d-block img-fluid" src="http://placehold.it/900x200" alt="Second slide">
      </div>
      <div class="carousel-item">
        <img class="d-block img-fluid" src="http://placehold.it/900x200" alt="Third slide">
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>

  <div class="row">

	<c:if test="${requestScope.libros != null }">
	  <c:forEach var="libro" begin="0" items="${requestScope.libros}">

	      <div class="col-lg-3 col-md-4 col-6 mb-4">
		    <div class="card h-100">
  		      <a href="#">
  		      	<img class="card-img-top img-fluid" height="200" src="/libreria-java/resources/images/bookcovers/${libro.getTapa()}.jpg" alt="img">
  		      </a>
        	  <div class="card-body">
          	    <h6 class="card-title">
                  <a href="#">
                	<c:out value="${libro.getTitulo()}"></c:out>
                  </a>
                </h6>
                <h6><c:out value="${libro.getAutor()}"></c:out></h6>
                <p class="card-text">${libro.getCat().getDesc()}</p>
              </div>
            </div>
          </div>
			
    	</c:forEach>	
	</c:if>	

  </div>
  <!-- /.row -->

</div>
<!-- /.col-lg-9 -->