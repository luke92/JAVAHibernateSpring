<%@page import="edu.utn.util.Sesion"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.jsp">Home</a>
        </div>
        <div class="navbar-collapse collapse">
        	<ul class="nav navbar-nav">
        	
        		<c:if test="${userRol == 'Administrador'}">
	        		<li class="dropdown">
		        		<a href="#" class='dropdown-toggle' data-toggle="dropdown">Usuarios<b class='caret'></b></a>
		        		<ul class="dropdown-menu">
	                    	<li><a href="NewUser.jsp">Crear Usuario</a></li>
	                    	<li><a href="ViewUsers">Listar Usuarios</a></li>
	                    </ul>
	             	</li>
	             	
	             	<li class="dropdown">
		        		<a href="#" class='dropdown-toggle' data-toggle="dropdown">Productos<b class='caret'></b></a>
		        		<ul class="dropdown-menu">
	                    	<li><a href="NewProduct.jsp">Agregar Producto</a>
	                    	<li><a href="ViewProducts">Listar Productos</a></li>
	                    </ul>
	             	</li>
          	    </c:if>
          	    
        		<c:if test="${userRol == 'Vendedor'}">
	        		<li><a href="NewSale">Registrar Venta</a></li>
	        		<li><a href="ViewSalesUser">Listar Ventas</a></li>
        		</c:if>
        		
        		<c:if test="${userRol == 'RRHH'}">
        		
        		<li class="dropdown">
		        		<a href="#" class='dropdown-toggle' data-toggle="dropdown">Productos<b class='caret'></b></a>
		        		<ul class="dropdown-menu">
	                    	<li><a href="NewProduct.jsp">Agregar Producto</a>
	                    	<li><a href="ViewProducts">Listar Productos</a></li>
	                    </ul>
	             	</li>
        		
        		<li class="dropdown">
		        		<a href="#" class='dropdown-toggle' data-toggle="dropdown">Comisiones<b class='caret'></b></a>
		        		<ul class="dropdown-menu">
	                    	<li><a href="NewComision.jsp">Agregar Comision</a>
	                    	<li><a href="ViewRulesComisiones">Listar Reglas de Comisiones</a></li>
	                    </ul>
	             </li>
        		
        		<li class="dropdown">
		        		<a href="#" class='dropdown-toggle' data-toggle="dropdown">Premios<b class='caret'></b></a>
		        		<ul class="dropdown-menu">
	                    	<li><a href="NewPremio.jsp">Agregar Premio</a>
	                    	<li><a href="ViewRulesPremios">Listar Reglas de Premios</a></li>
	                    </ul>
	             </li>
  
        		<li class="dropdown">
		        		<a href="#" class='dropdown-toggle' data-toggle="dropdown">Reportes<b class='caret'></b></a>
		        		<ul class="dropdown-menu">
	                    	<li><a href="ReportComision">Comisiones de Vendedores por cantidad de ventas</a>
	                    	<li><a href="ReportComisionProduct">Comisiones de Vendedores por producto</a>
	                    	<li><a href="BestVendor">Mejor Vendedor del Mes</a></li>
	                    	<li><a href="BestVendorProduct">Mejor Vendedor de una Campaña</a></li>
	                    </ul>
	             </li>
	             
	             <li>
		        		<a href="ReportComisionPremio">Reporte Comisiones y Premios</a>
	             </li>

        		</c:if>
        	</ul>
        	
	        <c:if test="${empty userDni}" >
	          <form class="navbar-form navbar-right" action="Login" method="post">
	            <div class="form-group">
	              <input required name="dni" type="text" placeholder="DNI" class="form-control numeros">
	            </div>
	            <div class="form-group">
	              <input required name="password" type="password" placeholder="Password" class="form-control">
	            </div>
	            <button type="submit" class="btn btn-success">Iniciar Sesion</button>
	          </form>
	        </c:if>
	        
	        <c:if test="${not empty userDni}">
	        <ul class="nav navbar-nav pull-right">
	        	<li class="dropdown">
	        		<a href="#" class='dropdown-toggle' data-toggle="dropdown">${userName} ${userLastName}<b class='caret'></b></a>
	        		<ul class="dropdown-menu">
                    	<li><a href="Logout">Cerrar Sesion</a></li>
                    	<li><a href="ChangePassword.jsp">Modificar Contraseña</a></li>
                    </ul>
             	</li>
            </ul>
	        </c:if>
	        
        </div><!--/.navbar-collapse -->
      </div>
    </div>