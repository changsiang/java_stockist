<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div style="width: 400px; margin-left: auto; margin-right: auto;">

	<!-- Login Form -->
	<form:form
		action="${pageContext.request.contextPath}/authenticateTheUser"
		method="POST" class="form-horizontal">

		<!-- Place for messages: error, alert etc ... -->
		<div class="form-group">
			<div class="col-xs-15">
				<div>

					<!-- Check for login error -->

					<c:if test="${param.error != null}">

						<div class="alert alert-danger col-xs-offset-1 col-xs-10">
							Invalid username and password.</div>

					</c:if>

					<!-- Check for logout action -->
					<c:if test="${param.logout != null}">
						<div class="alert alert-success col-xs-offset-1 col-xs-10">
							You have been logged out.</div>
					</c:if>

				</div>
			</div>
		</div>

		<!-- User name -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span> <input type="text"
				name="username" placeholder="username" class="form-control">
		</div>

		<!-- Password -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-lock"></i></span> <input type="password"
				name="password" placeholder="password" class="form-control">
		</div>

		<!-- Login/Submit Button -->
		<div style="margin-top: 10px" class="form-group">
			<div class="col-sm-6 controls">
				<button type="submit" class="btn btn-success btn-block">Login</button>
			</div>
		</div>

	</form:form>
</div>
