<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/head.jsp" />
<title>Home</title>
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
   	<div class="jumbotron">
      <div class="container">
        <h1>Home</h1>
      </div>
      ${mensaje}
    </div>
    <div class="container">
      <hr>

      <jsp:include page="includes/footer.jsp" />
    </div>​
</body>
</html>