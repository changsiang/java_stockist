
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Edit User Page</h3>
<p style="color:red">${userupdateerror}</p>
<form:form method="POST" modelAttribute="user"
	action="${pageContext.request.contextPath}/admin/user/edit/${users.id}.html">
	<form:errors />
	<table>
		<tbody>
			<tr>
				<td>User ID</td>
				<td><form:input class="form-control" path="id" readonly="true" />
					<form:errors path="id" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td>User Name</td>
				<td><form:input class="form-control" path="username" readonly="true" /> <form:errors
						path="username" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:input class="form-control" path="password" /> <form:errors
						path="password" cssStyle="color: red;" /></td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td>User Role</td>


				<td><form:select class="form-control" path="userRole" items="${roleList}" width="200" style="width: 200px"/> <form:errors
						path="userRole" cssStyle="color: red;" /></td>

			</tr>

			<tr>
				<td><br /></td>
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
