<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	<h3>Product Details Page</h3>
</div>
<br/>
<br/>
<br/>

<!-- Product Description -->
<div>

	

	<h2>${pList.description}</h2>

</div>



<table class="table table-striped" style="cellspacing: 2; cellpadding: 2; border: 1; width:50%" >
<tbody>
<tr><td>PartId:<td><td>${pList.partID}</td>

<tr><td>UnitPrice:<td><td>${pList.unitPrice}</td>
<tr><td>Color:<td><td>${pList.color}</td>
<tr><td>Dimension:<td><td>${pList.dimension}</td>
<tr><td>Manufacturer:<td><td>${pList.manufacturer}</td>
<tr><td>ReorderLevel:<td><td>${pList.reorderLevel}</td>
<tr><td>MinimumReorderQuantity:<td><td>$${pList.minReorderQty}</td>
<tr><td>ShelfLocation:<td><td>${pList.shelfLocation}</td>
<tr><td>SupplierID:<td><td>${pList.supplierID}</td>
<tr><td>UnitsInStock:<td><td>${pList.unitsInStock}</td>
<tr><td>UnitsOnOrder:<td><td>${pList.unitsOnOrder}</td>
<tr><td>Discontinued:<td><td>${pList.discontinued}</td>



</table>
<br/>
<br/>
<br/>





<!-- Product BY Date Range -->
<div>
<h5 align="left" Style="color:red">${msgAlert}</h5>
<form action="${pageContext.request.contextPath}/mechanic/product/details/filter">
<h4>Filtered transaction history by date range</h4>
<table>
<tr>
<td>
  Start Date:
  <br />
  <input class="form-control" type="date" name="startdate" style="width:180px"></td>
  <td>&nbsp;&nbsp;
  </td>
  <td>
  End Date:
  <br />
  <input class="form-control" type="date" name="enddate" style="width:180px"></td>
</tr>


</table>
 
  <input type="hidden" name="pid" value="${pList.partID}"><br/>
  <input class="btn btn-primary" type="submit">
</form>
</div>
<br/>


<!-- Transaction History -->
<div>
	<h4 align="center">Transaction History</h4>
</div>
	<hr class="divider" />
<c:if test="${fn:length(tList) gt 0}">
	<table class="table table-striped"
		style="cellspacing: 2; cellpadding: 2; border: 1; width: 100%">
		<thead>
			<tr class="listHeading">
				<th>TransactionId</th>
				<th>UsedQuantity</th>
				<th>CustomerName</th>
				<th>DateUsed</th>

			</tr>
		</thead>
		<tbody>
			<!-- Iterating through tList.Printing transId and Usedquantity of product -->
			<c:forEach var="p" items="${tList}">
				<tr class="listRecord">

					<!-- Iterating through the rList to print out the rList details specific to the tList -->
					<c:forEach var="p1" items="${rList}">
						<!-- If condition to print rList record only if transId is same -->
						<c:if test="${p.transId==p1.transID}">		
							<td align="left">${p.transId}</td>
							<td align="left">${p.usedQty}</td>
							<td align="left">${p1.customerName}</td>
							<td align="left">${p1.dateUsed}</td>
						</c:if>



					</c:forEach>
				</tr>
			</c:forEach>

	</tbody>
	</table>
</c:if>


<div>
	<form:form action="${pageContext.request.contextPath}/mechanic/product/addtocart" method="POST">
	<table>
	<tr>
	<td>
		<input type="submit" class="btn btn-danger" value="Add">
	</td>
		  <td>&nbsp;&nbsp;</td>
	<td>
		<input class="form-control" type="text" name="qty" style="width:100px">
	</td>
	</tr>
	
	</table>
		
		<input id="cartPId" name="cartPId" type="hidden" value="${pList.partID}"/>
	</form:form>
</div>


	