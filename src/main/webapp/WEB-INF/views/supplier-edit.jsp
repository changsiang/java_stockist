
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Edit Supplier Record</h3>

<form:form method="POST" modelAttribute="supplier"
	action="${pageContext.request.contextPath}/admin/supplier/edit/${suppliers.supplierID}.html">
	<form:errors />
	<table>
		<tbody>
			<tr>
				<td>Supplier ID</td>
				    <td><form:input class="form-control" path="supplierID" readonly="true" />
					<form:errors path="supplierID" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td>Company Name</td>
				<td><form:input class="form-control" path="companyName" />
					<form:errors path="companyName" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td>Contact Number</td>
				<td><form:input class="form-control" path="contactNumber" />
					<form:errors path="contactNumber" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><form:input class="form-control" path="address" />
					<form:errors path="address" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>

			<tr>
				<td><input class="btn btn-primary" type="submit" value="Update" /></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
</form:form>
