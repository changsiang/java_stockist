<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>

	<h3>Product Reorder Report</h3>
	<c:if test="${empty message}">
		<form:form method="POST"
			action="${pageContext.request.contextPath}/admin/print/report">
			<input type="submit" value="Generate Report" class="btn btn-success" />
		</form:form>
	</c:if>

	<h3>${message}</h3>
	<div class="scrollbar-thumb"
		style="overflow-x: auto; overflow-y: auto;">
		<c:forEach var="map" items="${orderListMap}">
			<div class="col-md-offset-4">
				<h4><c:out
					value="Inventory Reorder Report for Supplier ${map.key.supplierID}"></c:out></h4>
			</div>
			<table class="table table-striped"
				style="cellspacing: 2; cellpadding: 2; border: 1; width: 100%">
				<thead>
					<tr class="listHeading">
						<th>Part No.</th>
						<th>Unit Price</th>
						<th>Quantity</th>
						<th>Reorder Qty</th>
						<th>Min. Order Qty</th>
						<th>Ordered Qty</th>
						<th>Price</th>
					</tr>
				</thead>


				<c:forEach var="p" items="${map.value}">
					<tr class="listRecord">

						<td><fmt:formatNumber type="number" minIntegerDigits="4"
								groupingUsed="false" value="${p.partID}" /></td>
						<td><fmt:formatNumber type="currency" currencySymbol="$"
								value="${p.unitPrice}" /></td>
						<td align="left">${p.unitsInStock}</td>
						<td align="left">${p.reorderLevel}</td>
						<td align="left">${p.minReorderQty}</td>
						<td align="left">${p.unitsOnOrder}</td>
						<td align="left"><fmt:formatNumber type="currency"
								currencySymbol="$" groupingUsed="true"
								value="${p.unitPrice * p.unitsOnOrder}" /></td>
					</tr>


				</c:forEach>




			</table>
		</c:forEach>
		<div align="right">
			<h3>
				Total Price:
				<fmt:formatNumber type="currency" currencySymbol="$"
					groupingUsed="true" value=" ${totalPrice}" />
			</h3>
		</div>
		<div class="col-md-offset-5">End of Report</div>




	</div>
</body>
</html>