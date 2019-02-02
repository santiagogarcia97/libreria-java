<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="col-lg-9 p-4 bg-light h-100">

 	<div class="form-group">
    	<h2>Agregar Libro</h2>
    	<hr>
	</div>
	
	<form action="/libreria-java/admin/alta-libro" method="post">
		
		<div class="form-group row">
    		<label for="inputTitulo" class="col-sm-3 col-form-label">Titulo</label>
   			 <div class="col-sm-9">
      			<input type="text" class="form-control" id="inputTitulo" name="inputTitulo" placeholder="Titulo" required="required">
    		</div>
  		</div>			
		<div class="form-group row">
    		<label for="inputAutor" class="col-sm-3 col-form-label">Autor</label>
   			 <div class="col-sm-9">
      			<input type="text" class="form-control" id="inputAutor" name="inputAutor" placeholder="Autor" required="required">
    		</div>
  		</div>		
		<div class="form-group row">
    		<label for="inputEdicion" class="col-sm-3 col-form-label">Edicion</label>
   			 <div class="col-sm-9">
      			<input type="text" class="form-control" id="inputEdicion" name="inputEdicion" placeholder="Edicion" required="required">
    		</div>
  		</div>		  		  		
		<div class="form-group row">
    		<label for="inputFechaEdicion" class="col-sm-3 col-form-label">Fecha de edicion</label>
   			 <div class="col-sm-9">
      			<input type="date" class="form-control" id="inputFechaEdicion" name="inputFechaEdicion" placeholder="Fecha de edicion" required="required">
    		</div>
  		</div>	  			
		<div class="form-group row">
    		<label for="inputCategoria" class="col-sm-3 col-form-label">Categoria</label>
   			 <div class="col-sm-9">
      			<input type="text" class="form-control" id="inputCategoria" name="inputCategoria" placeholder="Categoria" required="required">
    		</div>
  		</div>			
		<div class="form-group row">
    		<label for="inputISBN" class="col-sm-3 col-form-label">ISBN</label>
   			 <div class="col-sm-9">
      			<input type="text" class="form-control" id="inputISBN" name="inputISBN" placeholder="ISBN" required="required">
    		</div>
  		</div>
 		<div class="form-group row">
    		<label for="inputMaxDias" class="col-sm-3 col-form-label">Maximo dias de prestamo</label>
   			 <div class="col-sm-9">
      			<input type="number" class="form-control" id="inputMaxDias" name="inputMaxDias" placeholder="Maximo dias de prestamo" required="required">
    		</div>
  		</div> 		
  	    <div class="form-group row">
	      <div class="col">
	        <button type="submit" class="btn btn-primary float-right">Agregar</button>
	      </div>
	    </div> 			
	</form>



</div>