<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	

<h3 align="left">New Product Entry</h3>
<h5 align="left" Style="color:red">${msgAlert}</h5>
<form:form method="POST" modelAttribute="product" action="${pageContext.request.contextPath}/admin/product/create">
	<table style="border: 10; width: 50%; display: flex; align-items: center">
		<tbody>
			<tr>
				<td>PartID&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><form:input path="partID" class="form-control" pattern="^\d+$" />
				<form:errors path="partID" cssStyle="color:red" /></td>
			</tr>
			<tr><td><br /></td></tr>
			<tr>
				<td>Description&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><form:input path="description" class="form-control" />
				<form:errors path="description" cssStyle="color:red" /></td>
			</tr>
			<tr><td><br /></td></tr>
			<tr>
				<td>Unit Price&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><form:input path="unitPrice" class="form-control" pattern="^[+]?([0-9]*\.?[0-9]+|[0-9]+\.?[0-9]*)([eE][+-]?[0-9]+)?$" />
				<form:errors path="unitPrice" cssStyle="color:red" /></td>
			</tr>
			<tr><td><br /></td></tr>
			<tr>
				<td>Color&nbsp;&nbsp;&nbsp;&nbsp;<br /></td>
				<td><form:input path="color" class="form-control" />
				<form:errors path="color" cssStyle="color:red" /></td>
			</tr>
			<tr><td><br /></td></tr>
			<tr>
				<td>Dimension&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><form:input path="dimension" class="form-control" />
				<form:errors path="dimension" cssStyle="color:red" /></td>
			</tr>
			<tr><td><br /></td></tr>
			<tr>
				<td>Manufacturer&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><form:input path="manufacturer" class="form-control" />
				<form:errors path="manufacturer" cssStyle="color:red" /></td>
			</tr>
			<tr><td><br /></td></tr>
			<tr>
				<td>Reorder Level&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><form:input path="reorderLevel" class="form-control" pattern="^\d+$" />
				<form:errors path="reorderLevel" cssStyle="color:red" /></td>
			</tr>
			<tr><td><br /></td></tr>
			<tr>
				<td>Minimum Reorder Quantity&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><form:input path="minReorderQty" class="form-control" pattern="^\d+$" />
				<form:errors path="minReorderQty" cssStyle="color:red" /></td>
			</tr>
			<tr><td><br /></td></tr>
			<tr>
				<td>Shelf Location&nbsp;&nbsp;&nbsp;&nbsp;</td>	
				<td><form:input path="shelfLocation" class="form-control" />
				<form:errors path="shelfLocation" cssStyle="color:red" /></td>
			</tr>
			<tr><td><br /></td></tr>
			<tr>
				<td>SupplierID&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>
					<form:select path="supplierID" class="form-control" >
						<c:forEach var="s" items="${sList}">
							<form:option value = "${s.supplierID}" >${s.companyName}</form:option>
              			</c:forEach>
					</form:select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<form:errors path="supplierID" cssStyle="color:red" />
				</td>
			</tr>
			<tr><td><br /></td></tr>
			<tr>
				<td>Quantity&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><form:input path="UnitsInStock" class="form-control" pattern="^\d+$" />
				<form:errors path="UnitsInStock" cssStyle="color:red" /></td>
			</tr>
		</tbody>
	</table>
	<br />
	<br />
	
	<input type="submit" value="Enter" class="btn btn-primary" style="position: relative; left: 34.25%;"/>
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/product/create" style="position: relative; left: -6%;">Reset</a>
</form:form>



