<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="col-lg-3 h-100">

        <h1 class="my-4">AdminPanel</h1>
  
        <h5 class="">Prestamos</h5>
        <div class="list-group list-group-flush">
          <div class="list-group-item d-flex justify-content-between align-items-center">
            <a href="#" class="text-dark">Pendientes Preparación</a>
            <span class="badge badge-primary badge-pill">14</span>
          </div>
          <div class="list-group-item d-flex justify-content-between align-items-center">
            <a href="#" class="text-dark">Pendientes Retiro</a>
            <span class="badge badge-primary badge-pill">14</span>
          </div>
          <div class="list-group-item d-flex justify-content-between align-items-center">
            <a href="#" class="text-dark">Pendientes Devolución</a>
            <span class="badge badge-primary badge-pill">14</span>
          </div>
        
        </div>
        
        <br>
        <h5 class="">Libros</h5>
        <div class="list-group list-group-flush">
          <div class="list-group-item d-flex justify-content-between align-items-center">
            <a href="#" class="text-dark">Buscar</a>
          </div>        
          <div class="list-group-item d-flex justify-content-between align-items-center">
            <a href="/libreria-java/admin/alta-libro" class="text-dark">Agregar</a>
          </div>    
        </div>
        
        <br>
        <h5 class="">Categorias Libros</h5>
        <div class="list-group list-group-flush">
          <div class="list-group-item d-flex justify-content-between align-items-center">
            <a href="/libreria-java/admin/alta-cat-libro" class="text-dark">Agregar</a>
          </div>         
        </div>
                
        <br>
        <h5 class="">Socios</h5>
        <div class="list-group list-group-flush">
          <div class="list-group-item d-flex justify-content-between align-items-center">
            <a href="#" class="text-dark">Buscar</a>
          </div>            
        </div>
        
        <br>
        <h5 class="">Reportes</h5>
        <div class="list-group list-group-flush">
          <div class="list-group-item d-flex justify-content-between align-items-center">
            <a href="#" class="text-dark">Prestamos</a>
          </div>    
          <div class="list-group-item d-flex justify-content-between align-items-center">
            <a href="#" class="text-dark">Libros</a>
          </div>    
          <div class="list-group-item d-flex justify-content-between align-items-center">
            <a href="#" class="text-dark">Socios</a>
          </div>                                
        </div>

</div>