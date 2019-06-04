<%@page import="edu.utn.util.Sesion"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%if(!new Sesion(request.getSession()).isRRHH())
    	response.sendRedirect("index.jsp");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/head.jsp" />
<title>Reporte de Comisiones y Premios</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Reporte de Comisiones y Premios</h1>
      </div>
    </div>

		<div class="container">
		
		<form action="ReportComisionPremio" method="post">
			<div class="row">
		
				<div class="col-lg-4">
					<div class="form-group">
						<label for="Vendedores">Elegir Vendedores</label>
						<span class="input-group-addon">
						<c:forEach items="${vendedores}" var="vendedor">
							<input type="checkbox" name="vendedores" value="${vendedor.dni }">
							<span>${vendedor.nombre} ${vendedor.apellido}</span>
						</c:forEach>
       					 
      					</span>
     				</div>
				</div>
				
				<div class="col-lg-4">
				
					<!-- Text input-->
					<div class="form-group">
		  				<label for="fecha">Fecha Inicio</label>
		    			<input id="fechaMin" name="fechaInicio" type="date" placeholder="Fecha" class="form-control" required="">
					</div>
					
					<!-- Text input-->
					<div class="form-group">
		  				<label for="fecha">Fecha Fin</label>
		    			<input id="fechaMin" name="fechaFin" type="date" placeholder="Fecha" class="form-control" required="">
					</div>
					
				</div>
				
			</div>
			<div class="row">
				
					<!-- Button -->
					<div class="form-group">
				  		<label for="singlebutton"></label>
				    	<button type="submit" class="btn btn-lg btn-primary btn-block">Obtener Reporte General</button>
					</div>
				
			</div>
		
		</form>
		
		
		<c:if test="${not empty mejorVendedorMes}">
			<div class="table-responsive">
				<h3>Mejor Vendedor del Mes</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Vendedor</th>
							<th>Cantidad de Ventas</th>
							<th>Premio</th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<td>${mejorVendedorMes.vendedor}</td>
								<td>${mejorVendedorMes.cantidadVentas}</td>
								<td>${mejorVendedorMes.comision}</td>
							</tr>
					</tbody>
				</table>
			</div>
		</c:if>
		
		
		
		<c:if test="${not empty mejorVendedorCampanias}">
		<div class="table-responsive">
			<h3>Mejor Vendedor de cada Producto</h3>
			<c:forEach items="${mejorVendedorCampanias}" var="mejorVendedor">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Vendedor</th>
							<th>Producto</th>
							<th>Comision</th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<td>${mejorVendedor.vendedor}</td>
								<td>${mejorVendedor.producto}</td>
								<td>${mejorVendedor.comision}</td>
							</tr>
					</tbody>
				</table>
			</c:forEach>
		</c:if>
		
		<c:if test="${not empty comisionesPorVentas}">
		<div class="table-responsive">
			<h3>Comisiones Por Ventas</h3>
			<c:forEach items="${comisionesPorVentas}" var="comision">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Vendedor</th>
							<th>Cantidad de Ventas</th>
							<th>Comision</th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<td>${comision.vendedor}</td>
								<td>${comision.cantidadVentas}</td>
								<td>${comision.comision}</td>
							</tr>
					</tbody>
				</table>
			</c:forEach>
		</c:if>
		
		<c:if test="${not empty comisionesPorProductos}">
		<div class="table-responsive">
			<h3>Comisiones Por Ventas de un Producto en Particular</h3>
			<c:forEach items="${comisionesPorProductos}" var="comisionP">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Vendedor</th>
							<th>Producto</th>
							<th>Adicional</th>
							<th>Cantidad de Ventas</th>
							<th>Comision</th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<td>${comisionP.vendedor}</td>
								<td>${comisionP.producto}</td>
								<td>${comisionP.adicional}</td>
								<td>${comisionP.cantidadVentas}</td>
								<td>${comisionP.comision}</td>
							</tr>
					</tbody>
				</table>
			</c:forEach>
		</c:if>
		
		${mensaje}
		</div>
    <jsp:include page="includes/footer.jsp" />​
</body>
</html>