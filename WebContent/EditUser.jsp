<%@page import="edu.utn.util.Sesion"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%if(!new Sesion(request.getSession()).isAdmin())
    	response.sendRedirect("index.jsp");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/head.jsp" />
<title>Modificar Usuario</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Modificar Usuario</h1>
      </div>
    </div>
    <div class="container">
     <div class="col-lg-4"></div>
    <div class="col-lg-4">
	
		<form action="EditUser" method="post">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Modificar Usuario</legend>
		
		<!-- Text input-->
		<div class="form-group">
		  <label for="dni">D.N.I.</label>
		    <input value="${usuario.dni }" readonly maxlength = "8" max="99999999" id="dni" name="dni" type="text" placeholder="DNI" class="form-control numeros" required="">
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label for="nombre">Nombre</label>
		    <input value="${usuario.nombre }" id="nombre" name="nombre" type="text" placeholder="Nombre" class="form-control letras" required="">
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label for="apellido">Apellido</label>
		    <input value="${usuario.apellido }" id="apellido" name="apellido" type="text" placeholder="Apellido" class="form-control letras" required="">
		</div>
		
		<!-- Password input-->
		<div class="form-group">
		  <label for="password">Contraseña</label>
		    <input value="${usuario.contrasenia }" id="password" name="password" type="password" placeholder="Contraseña" class="form-control" required="">
		</div>
		
		<!-- Select Basic -->
		<div class="form-group">
		  <label for="tipoUsuario">Tipo Usuario</label>
		    <select id="tipoUsuario" name="tipoUsuario" class="form-control">
		      <option value="1" <c:if test="${usuario.tipoUsuario == 'Vendedor' }">selected="selected"</c:if>>Vendedor</option>
		      <option value="2" <c:if test="${usuario.tipoUsuario == 'RRHH' }">selected="selected"</c:if>>RRHH</option>
		      <option value="3" <c:if test="${usuario.tipoUsuario == 'Administrador' }">selected="selected"</c:if>>Adminstrador</option>
		    </select>
		</div>
		
		<!-- Button -->
		<div class="form-group">
		  <label for="singlebutton"></label>
		    <button type="submit" class="btn btn-lg btn-primary btn-block">Modificar Usuario</button>
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