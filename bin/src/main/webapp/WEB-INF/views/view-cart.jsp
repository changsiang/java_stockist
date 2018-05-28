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
	<form:form method="POST" modelAttribute="cartList"
		action="${pageContext.request.contextPath}/usagerecord/viewcart">
	Cart ID: ${cartList.cartId} Name: ${cartList.user.username} Count: ${fn:length(cartList.cartItemList) }
	<br />
		<table>
			<thead>
				<tr>
					<th>Product</th>
					<th>Quantity</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="i" items="${cartList.cartItemList}">
					<tr>
						<td>${i.product.description}</td>
						<td>${i.quantity}</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<input type="submit" value="Checkout" />
	</form:form>
</body>
</html>