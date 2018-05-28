<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
	
	
<link rel='stylesheet prefetch' href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<form:form action="${pageContext.request.contextPath}/mechanic/product/search" method="POST">
	<div class="wrap">
		<div class="search">
				<input type="text" class="searchTerm" placeholder="Search..." name="inputField" value="${searchValue}"/>
				<button class="searchPicture" type="submit"><i class="fa fa-search"></i></button>
		</div>
	</div>
	<br />
	
	<h5 align="center" Style="color:red">${msgAlert}</h5>
	<h4>Refine by</h4>
	<div>
		<table style="width:60%">
			<tbody>
				<tr>
					<td>Manufacturer: </td>
					<td><form:checkbox path="manufacturerFilters" value="Toyota"/>&nbsp;Toyota&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><form:checkbox path="manufacturerFilters" value="Mercedez Benz"/>&nbsp;Mercedez Benz</td>
					<td><form:checkbox path="manufacturerFilters" value="General Motors"/>&nbsp;General Motors</td>
					<td><form:checkbox path="manufacturerFilters" value="Honda"/>&nbsp;Honda</td>
				</tr>
			</tbody>
		</table>
	</div>
</form:form>



<h2 align="center">Product List</h2>

		
<c:if test="${fn:length(productList) gt 0}">
<div class="scrollbar-thumb" style="overflow-x:auto;overflow-y:auto;">
<table class="table table-striped" style="cellspacing: 2; cellpadding: 2; border: 1; width:100%">
	<thead>
	<tr class="listHeading">
		<th style="text-align: center">Part Id</th>
		<th style="text-align: center">Description</th>
		<th style="text-align: center">Unit Price</th>
		<th style="text-align: center">Color</th>
		<th style="text-align: center">Manufacturer</th>
		<th style="text-align: center">Shelf Location</th>
		<th style="text-align: center">Supplier</th>
		<th style="text-align: center">Units In Stock</th>
		<sec:authorize access="hasAuthority('admin')">
			<th style="text-align: center"><spring:message code="caption.edit" /></th>
		</sec:authorize>
		<th style="text-align: center">Add to cart</th>
	</tr>
	</thead>
	<tbody>

	<c:forEach var="p" items="${productList}">
		<tr class="listRecord">
			<td align="center">${p.partID}</td>
			
			<!-- For some columns we use a combination of EL and scriptlets to modify the retrieve data such that all first letters are capitalized -->
			<td align="center"><c:set var="temp" value="${p.description}"/>
				<a href="${pageContext.request.contextPath}/mechanic/product/details/${p.partID}">
					<% 
						String tem = (String) pageContext.getAttribute("temp");
						tem = Character.toUpperCase(tem.charAt(0)) + tem.substring(1);
						out.println(tem);
					%>
				</a>
			</td>
			<td align="center"><fmt:formatNumber value="${p.unitPrice}" type="currency" currencySymbol="$"/></td>
			<td align="center"><c:set var="temp" value="${p.color}"/>
			<% 
				tem = (String) pageContext.getAttribute("temp");
				tem = Character.toUpperCase(tem.charAt(0)) + tem.substring(1);
				out.println(tem);
			%></td>
			<td align="center"><c:set var="temp" value="${p.manufacturer}"/>
			<% 
				tem = (String) pageContext.getAttribute("temp");
				tem = Character.toUpperCase(tem.charAt(0)) + tem.substring(1);
				out.println(tem);
			%></td>
			<td align="center">${p.shelfLocation}</td>
			 
			
			
			
			<!-- Done to produce supplier name in the 'Supplier' column of the table rather than SupplierID -->
			<c:set var="supID1" value="${p.supplierID}"/>
			<c:forEach var="s" items="${supplierList}">
				<c:set var="supID2" value="${s.supplierID}"/>
				<c:set var="supName" value="${s.companyName}"/>
				<% 
					String temp1 = (String) pageContext.getAttribute("supID1");
					String temp2 = (String) pageContext.getAttribute("supID2");
					String temp3 = (String) pageContext.getAttribute("supName");
					if (temp1.equals(temp2)) 
					{
						pageContext.setAttribute("supID1", temp3);
						break;
					}
				%>
			</c:forEach>
			<td align="center"><c:set var="temp" value="${supID1}"/>
			<% 
				tem = (String) pageContext.getAttribute("temp");
				tem = Character.toUpperCase(tem.charAt(0)) + tem.substring(1);
				out.println(tem);
			%></td>
			
			
			
			
			
			<td align="center">${p.unitsInStock}</td>

			<sec:authorize access="hasAuthority('admin')">
			<td align="center"><a class="btn btn-primary" href="${pageContext.request.contextPath}/mechanic/product/edit/${p.partID}">Edit</a></td>
			</sec:authorize>
			<td>
				<form:form action="${pageContext.request.contextPath}/mechanic/product/addtocart" method="POST">
					<input type="submit" class="btn btn-danger" value="Add">&nbsp;&nbsp;
					<input type="text" name="qty">
					<input id="cartPId" name="cartPId" type="hidden" value="${p.partID}"/>
				</form:form>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
</c:if>




<style>
/* For the search bar */
.search {
	width: 100%;
	position: relative
}

.searchTerm {
	float: left;
	width: 100%;
	border: 3px solid #3A3A87;
	padding: 5px;
	height: 45px;
	border-radius: 5px;
	outline: none;
	color: #9DBFAF;
	font-size: 20px;
}

.searchTerm:focus {
	color: #3A3A87;
}

.searchPicture {
	position: absolute;
	right: -51px;
	width: 55px;
	height: 45px;
	border: 1px solid #3A3A87;
	background: #3A3A87;
	text-align: center;
	color: #fff;
	border-radius: 5px;
	cursor: pointer;
	font-size: 20px;
}

.wrap {
	width: 40%;
	position: absolute;
	top: 52px;
	left: 50%;
	transform: translate(-50%, -50%);
}
</style>