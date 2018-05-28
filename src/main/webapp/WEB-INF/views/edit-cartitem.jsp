<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Edit Cart Item</title>
</head>
<body>
	<h3>Edit Cart Item</h3>
	<form:form method="POST" modelAttribute="cartItem"
		action="${pageContext.request.contextPath}/mechanic/usagerecord/viewcart/edit/${index}">
		

		<div>
			<table style="cellspacing: 2; cellpadding: 2; border: 1; width: 50%">
				<tr>
					<td><b>Part ID:</b></td>
					<form:hidden path="product.partID" />
					<td><b>Name:</b></td>
					<td><b>Quantity:</b></td>
				</tr>
				<tr>
					<td>${cartItem.product.partID}</td>
					<td>${cartItem.product.description}</td>
					<td><form:input path="quantity" class="form-control"
							placeholder="Enter Quantity" pattern="^\d+$"/><form:errors path="quantity" class="form-control" /></td>

				</tr>
				<tr>
					<td colspan="3"><input type="submit" value="Update"
						class="btn btn-success" /></td>
				</tr>
			</table>
		</div>



		<div>
			<form:hidden path="product.partID" />
			<form:hidden path="product.description" />
			<form:hidden path="product.unitPrice" />
			<form:hidden path="product.color" />
			<form:hidden path="product.manufacturer" />
			<form:hidden path="product.reorderLevel" />
			<form:hidden path="product.minReorderQty" />
			<form:hidden path="product.shelfLocation" />
			<form:hidden path="product.supplierID" />
			<form:hidden path="product.unitsInStock" />
			<form:hidden path="product.unitsOnOrder" />
			<form:hidden path="product.discontinued" />

		</div>

	</form:form>

</body>
</html>