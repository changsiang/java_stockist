<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Page</title>
</head>

<div class="jumbotron text-center">
	<img src="${pageContext.request.contextPath}/image/logo.png"
		width="35%" height="35%" class="img-rounded" />


	<h2>WELCOME TO STOCKIST</h2>
	<h5>Stockist is a simple inventory management application for car
		dealership workshop</h5>
	<h4 style="color: green">${checkoutSuccess}</h4>
</div>

</body>
</html>