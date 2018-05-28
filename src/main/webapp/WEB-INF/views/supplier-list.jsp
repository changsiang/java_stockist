
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h3>Supplier List Page</h3>
<a class="btn btn-success" href="${pageContext.request.contextPath}/admin/supplier/create">Add
	Supplier</a>
<c:if test="${fn:length(supplierList) gt 0}">
<p>${param.supplierdeleteerror}</p>
	<table  class="table table-striped" style="cellspacing: 2; cellpadding: 2; border: 1;width:100%">
		<thead>
			<tr class="listHeading">
				<th><spring:message code="Supplier ID" /></th>
				<th><spring:message code="Company Name" /></th>
				<th><spring:message code="Contact Number" /></th>
				<th><spring:message code="Address" /></th>
				<th><spring:message code="caption.edit" /></th>
				<th><spring:message code="caption.delete" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="suppliers" items="${supplierList}">
				<tr class="listRecord">
					<td>${suppliers.supplierID}</td>
					<td>${suppliers.companyName}</td>
					<td>${suppliers.contactNumber}</td>
					<td>${suppliers.address}</td>
					<td align="center"><a class="btn btn-primary"
						href="${pageContext.request.contextPath}/admin/supplier/edit/${suppliers.supplierID}.html"><spring:message
								code="caption.edit" /></a></td>
					<td><a class="btn btn-danger"
						href="${pageContext.request.contextPath}/admin/supplier/delete/${suppliers.supplierID}.html"><spring:message
								code="caption.delete" /></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>