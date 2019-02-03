<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
    	<h2>Agregar Categoria</h2>
    	<hr>
	</div>
	
	<form action="/libreria-java/admin/alta-cat-libro" method="post">
		
		<div class="form-group row">
    		<label for="inputDescripcion" class="col-sm-3 col-form-label">Descripción</label>
   			 <div class="col-sm-9">
      			<input type="text" class="form-control" id="inputDescripcion" name="inputDescripcion" placeholder="Descripción" required="required">
    		</div>
  		</div>				
  	    <div class="form-group row">
	      <div class="col">
	        <button type="submit" class="btn btn-primary float-right">Agregar</button>
	      </div>
	    </div> 	
	    		
	</form>
	
	<br>
<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Descripcion</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Otto</td>
    </tr>
    <tr>
      <th scope="row">2</th>

      <td>Thornton</td>
    </tr>
    <tr>

      <th scope="row">3</th>
      <td>the Bird</td>
    </tr>
  </tbody>
</table>


</div>