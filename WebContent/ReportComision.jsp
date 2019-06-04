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
<title>Reporte de Comisiones por ventas</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Reporte de Comisiones por ventas</h1>
      </div>
    </div>

		<div class="container">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Vendedor</th>
							<th>Cantidad de Ventas</th>
							<th>Comision</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${comisiones}" var="comision">
							<tr>
								<td>${comision.vendedor}</td>
								<td>${comision.cantidadVentas}</td>
								<td>${comision.comision}</td>
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