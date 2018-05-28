<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<h3>Product List page</h3>

<c:if test="${fn:length(productList) gt 0}">
<div class="scrollbar-thumb" style="overflow-x:auto;overflow-y:auto;">
<table class="table table-striped" style="cellspacing: 2; cellpadding: 2; border: 1;width:100%">
	<thead>
	<tr class="listHeading">
		<th>Part Id</th>
		<th>Description</th>
		<th>UnitPrice</th>
		<th>Color</th>
		<th>Dimension</th>
		<th>Manufacturer</th>
		<th>ReorderLevel</th>
		<th>MinReorder Qty</th>
		<th>ShelfLocation</th>
		<th>SupplierID</th>
		<th>UnitsInStock</th>
		<th>UnitsOnOrder</th>
		<th>Discontinued</th>
		<th><spring:message code="caption.edit" /></th>
		<th><spring:message code="caption.delete" /></th>
	</tr>
	</thead>
	<tbody>

	<c:forEach var="p" items="${productList}">
		<tr class="listRecord">
			<td align="left">${p.partID}</td>
			<td align="left">${p.description}</td>
			<td align="left">${p.unitPrice}</td>
			<td align="left">${p.color}</td>
			<td align="left">${p.dimension}</td>
			<td align="left">${p.manufacturer}</td>
			<td align="left">${p.reorderLevel}</td>
			<td align="left">${p.minReorderQty}</td>
			<td align="left">${p.shelfLocation}</td>
			<td align="left">${p.supplierID}</td>
			<td align="left">${p.unitsInStock}</td>
			<td align="left">${p.unitsOnOrder}</td>
			<td align="left">${p.discontinued}</td>

			
			<td align="center"><a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/student/edit/${student.id}.html">Edit</a></td>
			<td><a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/student/delete/${student.id}.html">Delete</a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
</c:if>