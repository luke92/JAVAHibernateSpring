<%@page import="edu.utn.util.Sesion"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%if(!new Sesion(request.getSession()).isVendor())
    	response.sendRedirect("index.jsp");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/head.jsp" />
<title>Agregar Venta</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Nueva Venta</h1>
      </div>
    </div>
    <div class="container">
     <div class="col-lg-4"></div>
    <div class="col-lg-4">
	
		<form action="NewSale" method="post">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Agregar Venta</legend>
		
		<!-- Text input-->
		<div class="form-group">
		  <label for="nroFactura">Nº Factura</label>
		    <input maxlength = "8" max="99999999" id="nroFactura" name="nroFactura" type="text" placeholder="Nº Factura" class="form-control numeros" required="">
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label for="fecha">Fecha</label>
		    <input id="fecha" name="fecha" type="date" placeholder="Fecha" class="form-control" required="">
		</div>
		
		<!-- Select Basic -->
		<div class="form-group">
		  <label for="tipoUsuario">Producto</label>
		    <select id="idProducto" name="idProducto" class="form-control">
		      <option selected="selected" disabled>Seleccione el Producto</option>
		      <c:forEach  var="producto" items="${productos}">
		      <option value="${producto.id}">${producto.descripcion}</option>
		      </c:forEach>
		    </select>
		</div>
		
		<!-- Number input-->
		<div class="form-group">
		  <label for="cantidad">Cantidad</label>
		    <input id="cantidad" name="cantidad" type="number" value="1" placeholder="Cantidad" min="1" class="form-control" required="">
		</div>
		
		<!-- Button -->
		<div class="form-group">
		  <label for="singlebutton"></label>
		    <button type="submit" class="btn btn-lg btn-primary btn-block">Registrar Venta</button>
		</div>
		
		</fieldset>
		</form>
		${mensaje}
	</div>
      <hr>
    </div>
    <jsp:include page="includes/footer.jsp" />​
</body>
</html>