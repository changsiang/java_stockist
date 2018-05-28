<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/flatly/bootstrap.min.css" />
	
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/lumen/bootstrap.min.css" />--%>

<!-- jQuery library -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 2;
	border-radius: 0;
}
</style>
<decorator:head />
</head>

<body background="${pageContext.request.contextPath}/image/whitepoly.jpg"/>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand text-center" href="#">STOCKIST -
					Inventory Management System</a>
					
			</div>

			<sec:authorize access="isAuthenticated()">

				<div class="nav navbar-nav navbar-right">
					<span class="btn btn-primary">Hi 
						${pageContext.request.userPrincipal.name}! You are currently
						logged in as <sec:authentication
							property="principal.userRole" />
					</span>

				</div>


				<div class="nav navbar-nav navbar-right">
					<form:form action="${pageContext.request.contextPath}/logout"
						method="POST">
						<input class="btn btn-primary" type="submit" value="Logout" />
					</form:form>
				</div>
			</sec:authorize>



		</div>
	</nav>


	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-md-2 sidebar-offcanvas" id="sidebar"
				role="navigation">
				<div class="sidebar-nav">
					<ul class="nav">
						<li class="nav-divider"></li>
						<li><a class="btn btn-primary"
							href="${pageContext.request.contextPath}">Home</a></li>

						<li class="nav-divider"></li>
						<li><a class="btn btn-primary"
							href="/mechanic/product/browse">Browse Catalog</a></li>
						<li class="nav-divider"></li>
						<li><a class="btn btn-primary" href="${pageContext.request.contextPath}/mechanic/usagerecord/viewcart">Record Usage</a></li>
						<li class="nav-divider"></li>
						
						<sec:authorize access="hasAuthority('admin')">
							<li><a class="btn btn-primary"
								href="${pageContext.request.contextPath}/admin/print/report">Reorder
									Report</a></li>
							<li class="nav-divider"></li>

							<li><a class="btn btn-primary"
								href="${pageContext.request.contextPath}/admin/supplier/list">Suppliers</a></li>
							<li class="nav-divider"></li>

							<li><a class="btn btn-primary"
								href="${pageContext.request.contextPath}/admin/user/list">Users</a></li>
							<li class="nav-divider"></li>

							<li><a class="btn btn-primary"
								href="${pageContext.request.contextPath}/admin/product/create">New
									Product Entry</a></li>
							<li class="nav-divider"></li>



						</sec:authorize>

					</ul>
				</div>

			</div>



			<div class="col-md-10">
				<br>
				<div class="jumbotron">

					<decorator:body />
				</div>

			</div>
		</div>
	</div>



	<footer>
		<br />
		<hr />
		<p>
			&copy;
			<c:set var="testDate" value="<%=new java.util.Date()%>" />
			<fmt:formatDate pattern="y" value="${testDate}" />

			- ISS NUS SA45 - TEAM12 - Version 1.0
		</p>
	</footer>
</body>
</html>
