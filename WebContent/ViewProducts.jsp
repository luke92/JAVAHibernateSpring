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
<title>Lista de Productos</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Listado de Productos</h1>
      </div>
    </div>
	
		<div class="container">
		<form action="ViewProducts" method="post">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th>Descripcion</th>
							<th>Precio Adicional</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${productos}" var="producto">
							<tr>
								<td><input type="radio" name="rbId" value="${producto.id}" required=""/></td> 
								<td>${producto.descripcion}</td>
								<td><span>$</span>${producto.adicional}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="form-group">
		  <label for="singlebutton"></label>
		    <button type="submit" name="opcion" value="edit" class="btn btn-primary">Editar Producto</button>
		    <button type="submit" name="opcion" value="delete" class="btn btn-danger">Borrar Producto</button>
		</div>
		</form>
		${mensaje}
		</div>
    <jsp:include page="includes/footer.jsp" />​
</body>
</html>