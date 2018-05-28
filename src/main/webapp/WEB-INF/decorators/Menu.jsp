<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/logout" var="logout" />
<spring:url value="/admin/student/list" var="slist"
							htmlEscape="true" /> <a href="${slist}"> 
							Listing  Page
					</a>


