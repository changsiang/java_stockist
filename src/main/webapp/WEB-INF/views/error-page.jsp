<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML>

<HEAD>
<TITLE>Oooops!</TITLE>
</HEAD>

<body>
	<center>
		<div class="container">
			<div class="jumbotron">
				<h1 align="left">Stuff happens.. but not to worry!</h1>
			</div>
		</div>
	</center>

	<table style="width: 100%">
		<tr>
			<td align="right"><IMG SRC="${pageContext.request.contextPath}/image/majorcrash.png" width="100%"
				height="100%"></td>
			<td><h3>Thanks for the patience while we put the pieces
					back together.</h3>
				<h4>Meanwhile....</h4>
				<p>
				<ul>
					<c:forEach var="troubleS" items="${troubleshoot}">
						<li>${troubleshoot}</li>
					</c:forEach>
				</ul>
				<h4>Otherwise...</h4>
				<p>
				<ul>
					<li>You are just a click away from <a
						href="localhost:8080/team12_stockist">home</a>.
					</li>
					<li>or contact IT Support by <a
						href="localhost:8080/team12_stockist">emailing</a> us.
					</li>
				</ul></td>


		</tr>
	</table>
	<!--


Failed URL	: ${url}
Date		: ${time}
User		: ${user.user.username}
Exception	: ${message}
<c:forEach items="${exception.stackTrace}" var="stackTrace">
${stackTrace}
</c:forEach>

 -->

</BODY>

</HTML>
