<%@page import="edu.utn.util.Sesion"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%if(!new Sesion(request.getSession()).isLogged())
    	response.sendRedirect("index.jsp");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/head.jsp" />
<title>Cambiar Contraseña</title>
 
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Cambiar Contraseña</h1>
      </div>
    </div>
    <div class="container">
     <div class="col-lg-4"></div>
    <div class="col-lg-4">
	
		<form action="ChangePassword" method="post">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Cambiar Contraseña</legend>
		
		<!-- Password input-->
		<div class="form-group">
		  <label for="password">Contraseña actual</label>
		    <input id="password" name="passwordActual" type="password" placeholder="Contraseña Actual" class="form-control" required="">
		</div>
		
		<!-- Password input-->
		<div class="form-group">
		  <label for="password">Contraseña nueva</label>
		    <input id="password" name="passwordNueva" type="password" placeholder="Contraseña Nueva" class="form-control" required="">
		</div>
		
		<!-- Button -->
		<div class="form-group">
		  <label for="singlebutton"></label>
		    <button type="submit" class="btn btn-lg btn-primary btn-block">Cambiar Contraseña</button>
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