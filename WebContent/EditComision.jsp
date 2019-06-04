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
<title>Editar Comision</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Editar Comision</h1>
      </div>
    </div>
    <div class="container">
     <div class="col-lg-4"></div>
    <div class="col-lg-4">
	
		<form action="EditComision" method="post">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Editar Comision</legend>
	
		<input type="hidden" name="id" value="${comision.id }" />
		
		<!-- Text input-->
		<div class="form-group">
		  <label for="minimo">Minimo</label>
		    <input value="${comision.minimo}" readonly="readonly" id="minimo" name="minimo" type="text" class="form-control numeros" placeholder="Cant. minima de ventas" required="">
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label for="maximo">Maximo</label>
		    <input value="${comision.maximo }" readonly="readonly" id="maximo" name="maximo" type="text" placeholder="Cant. maxima de ventas" class="form-control numeros" required="">
		</div>
		
		<div class="form-group">
		  <label for="adicional">Adicional por Cantidad de Ventas</label>
		    <input value="${comision.monto }" id="adicional" name="adicional" type="text" placeholder="Adicional por Venta" class="form-control numeros" required="">
		</div>
		
		<!-- Button -->
		<div class="form-group">
		  <label for="singlebutton"></label>
		    <button type="submit" class="btn btn-lg btn-primary btn-block">Editar Comision</button>
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