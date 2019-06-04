<%@page import="edu.utn.util.Sesion"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%if(!new Sesion(request.getSession()).isAdmin())
    	response.sendRedirect("index.jsp");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/head.jsp" />
<title>Lista de Usuarios</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Listado de Usuario</h1>
      </div>
    </div>
	
		<div class="container">
		<form action="EditUser" method="get">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th>DNI</th>
							<th>Nombre</th>
							<th>Rol</th>
							<th>Password</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${usuarios}" var="usuario">
							<tr>
								<td><input type="radio" name="rbDni" value="${usuario.dni}" required=""/></td> 
								<td>${usuario.dni}</td>
								<td>${usuario.nombre} ${usuario.apellido}</td>
								<td>${usuario.tipoUsuario}</td>
								<td>${usuario.contrasenia}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="form-group">
		  <label for="singlebutton"></label>
		    <button type="submit" name="opcion" value="edit" class="btn btn-primary">Editar Usuario</button>
		</div>
		</form>
		${mensaje}
		</div>
    <jsp:include page="includes/footer.jsp" />​
</body>
</html>