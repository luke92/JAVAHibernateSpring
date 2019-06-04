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
<title>Editar Producto</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Editar Producto</h1>
      </div>
    </div>
    <div class="container">
     <div class="col-lg-4"></div>
    <div class="col-lg-4">
	
		<form action="EditProduct" method="post">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Modificar Producto</legend>
		
		<input type="hidden" name="id" value="${producto.id }" />
		
		<!-- Text input-->
		<div class="form-group">
		  <label for="descripcion">Descripcion</label>
		    <input value='<c:out value="${producto.descripcion}"></c:out>' readonly="readonly" id="descripcion" name="descripcion" type="text" placeholder="Descripcion" class="form-control letras" required="">
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label for="adicional">Adicional por Venta</label>
		    <input value="${producto.adicional }" id="adicional" name="adicional" type="text" placeholder="Adicional por Venta" class="form-control numeros" required="">
		</div>
		
		<!-- Button -->
		<div class="form-group">
		  <label for="singlebutton"></label>
		    <button type="submit" class="btn btn-lg btn-primary btn-block">Actualizar Producto</button>
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