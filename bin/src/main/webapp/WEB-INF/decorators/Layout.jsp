<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/darkly/bootstrap.min.css" />
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
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div  class="navbar-header">
				<a class="navbar-brand text-center" href="#">STOCKIST - Inventory Management System</a>
			</div>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						Naing:Role Mechanic</a></li>
				<li><a href="/team12_stockist/product/login1"><span class="glyphicon glyphicon-log-in"></span>
						Login</a></li>
			</ul>
		</div>
	</nav>


	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-md-2 sidebar-offcanvas" id="sidebar"
				role="navigation">
				<div class="sidebar-nav">
					<ul class="nav">

						<li class="nav-divider"></li>
						<li><a class="btn btn-primary" href="/team12_stockist/product/list">View Products</a></li>
						<li class="nav-divider"></li>
						<li><a class="btn btn-primary" href="#">Usage Records</a></li>
						<li class="nav-divider"></li>

					</ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->

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

			- ISS NUS SA45 - V0.1
		</p>
	</footer>
</body>
</html>
