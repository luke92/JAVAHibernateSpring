<%@page import="edu.utn.util.Sesion"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%if(new Sesion(request.getSession()).getNivelRol() < 2)
    	response.sendRedirect("index.jsp");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/head.jsp" />
<title>Lista de Reglas de Premios</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Listado de Reglas de Premios</h1>
      </div>
    </div>
	
		<div class="container">
		<form action="EditPremio" method="get">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th>Nombre</th>
							<th>Monto Adicional</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${premios}" var="premio">
							<tr>
								<td><input type="radio" name="rbId" value="${premio.id}" required=""/></td> 
								<td>${premio.nombre}</td>
								<td><span>$</span>${premio.monto}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="form-group">
		  <label for="singlebutton"></label>
		    <button type="submit" name="opcion" value="edit" class="btn btn-primary">Editar Premio</button>
		</div>
		</form>
		${mensaje}
		</div>
    <jsp:include page="includes/footer.jsp" />​
</body>
</html>