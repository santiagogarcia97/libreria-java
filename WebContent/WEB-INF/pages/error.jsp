<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>

<div class="col-lg-9">
	<br>
	<span class="h3 text-bold">Se ha producido un error</span>
			<hr>
			<c:out value="${sessionScope.errorMsg}"></c:out>
			<c:set var="errorMsg" value="${null}" scope="session"/>
		</div>
	