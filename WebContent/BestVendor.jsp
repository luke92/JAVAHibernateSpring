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
<title>Mejor Vendedor</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Mejor Vendedor</h1>
      </div>
    </div>

		<div class="container">
		<c:if test="${not empty comision}">
			<div class="table-responsive">
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
								<td>${comision.vendedor}</td>
								<td>${comision.cantidadVentas}</td>
								<td>${comision.comision}</td>
							</tr>
					</tbody>
				</table>
			</div>
		</c:if>
		${mensaje}
			
		</div>
    <jsp:include page="includes/footer.jsp" />​
</body>
</html>