<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Temporary Test Page</title>
</head>
<body>
<h2>This is the temporary test page to help everyone with tesing your app</h2>
<h4>Written by Chang Siang</h4>
<br/>
<b>This is achieved by added the folloing code to the WebAppConfig.java</b>
<pre><code>
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("default");
	}
</code>
</pre>

<p>List of test page</p>
<br/>
<table>
<tr><td><a href="${pageContext.request.contextPath}/product/list">Product-List.jsp GET</a></td></tr>
<tr><td><a href="${pageContext.request.contextPath}/usagerecord/viewcart">view-cart.jsp GET</a></td></tr>
</table>

</body>
</html>