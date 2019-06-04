<%@page import="edu.utn.util.Sesion"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%if(new Sesion(request.getSession()).getNivelRol() < 2)
    	response.sendRedirect("index.jsp");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/head.jsp" />
<title>Nuevo Producto</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Nuevo Producto</h1>
      </div>
    </div>
    <div class="container">
     <div class="col-lg-4"></div>
    <div class="col-lg-4">
	
		<form action="NewProduct" method="post">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Agregar Producto</legend>

		<!-- Text input-->
		<div class="form-group">
		  <label for="descripcion">Descripcion</label>
		    <input id="descripcion" name="descripcion" type="text" placeholder="Descripcion" class="form-control letras" required="">
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label for="adicional">Adicional por Venta</label>
		    <input id="adicional" name="adicional" type="text" placeholder="Adicional por Venta" class="form-control numeros" required="">
		</div>
		
		<!-- Button -->
		<div class="form-group">
		  <label for="singlebutton"></label>
		    <button type="submit" class="btn btn-lg btn-primary btn-block">Agregar Producto</button>
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