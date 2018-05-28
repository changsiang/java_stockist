<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Item Cart</title>
</head>
<body>
<h3>Record Part Usage</h3>
	<form:form method="POST" modelAttribute="cart"
		action="${pageContext.request.contextPath}/mechanic/usagerecord/viewcart">



		<div>
			<table style="cellspacing: 2; cellpadding: 1; border: 1; width: 30%">
				<tr>
					<td><b>Staff Name:</b></td>
					<td>${cart.user.username}</td>
				</tr>
				<tr>
					<td><b>Cart ID:</b></td>
					<td>${cart.cartId}</td>
				</tr>

			</table>
		</div>

		<br />
		<div style="color: red;">${customerNameError}</div>
		<form:input path="customerName" class="form-control"
			placeholder="Enter Customer Name.." />



		<br />


		<table class="table table-striped"
			style="cellspacing: 2; cellpadding: 2; border: 1; width: 100%">
			<thead>
				<tr>
					<td align="center"><b>Part ID</b></td>
					<td align="center"><b>Description</b></td>
					<td align="center"><b>Quantity</b></td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="i" items="${cart.cartItemList}" varStatus="loop">
					<tr>
						<td align="center">${i.product.partID}</td>
						<td align="center">${i.product.description}</td>
						<td align="center">${i.quantity}</td>
						<td align="center"><a class="btn btn-primary"
							href="${pageContext.request.contextPath}/mechanic/usagerecord/viewcart/edit/${loop.index}">Amend</a></td>
						<td><a class="btn btn-danger"
							href="${pageContext.request.contextPath}/mechanic/usagerecord/viewcart/delete/${loop.index}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div style="color: red;">
			<c:forEach var="s" items="${noStockCartItem}">
				<c:out value="${s}" />
				<br />
			</c:forEach>
			<c:if test="${not empty noStockCartItem}">
				<b>Please amend the quantity accordingly.</b>
			</c:if>
		</div>
		<input type="submit" value="Record Usage" class="btn btn-success"
			style="position: relative; left: 70%;" />
	</form:form>
</body>
</html>