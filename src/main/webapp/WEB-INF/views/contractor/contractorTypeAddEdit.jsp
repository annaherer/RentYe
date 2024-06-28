<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="valuemakers.app.rentye.model.TransactionSort" %>
<%@ include file="../header.jsp" %>
<c:set var="operation" value="Add"/>
<c:if test="${contractorType.id>0}"> <c:set var="operation" value="Edit"/> </c:if>
<h1>${operation} contractor type</h1>
<form:form method="post" modelAttribute="contractorType">
    Description: <form:input type="text" path="description"/> <form:errors path="description"/><br>
    <br><input class="btn btn-secondary btn" type="submit" value="Submit">
</form:form>
<%@ include file="../footer.jsp" %>