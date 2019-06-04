<%@page import="edu.utn.util.Sesion"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%if(!new Sesion(request.getSession()).isVendor())
    	response.sendRedirect("index.jsp");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/head.jsp" />
<title>Ventas realizadas</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Ventas realizadas</h1>
      </div>
    </div>

		<div class="container">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Nro Factura</th>
							<th>Fecha</th>
							<th>Producto</th>
							<th>Cantidad</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ventas}" var="venta">
							<tr>
								<td>${venta.id}</td>
								<td><fmt:formatDate pattern="dd-MM-yyyy" 
            					value="${venta.fecha}" /></td>
								<td>${venta.producto.descripcion}</td>
								<td>${venta.cantidad}
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			${mensaje}
		</div>
    <jsp:include page="includes/footer.jsp" />​
</body>
</html>