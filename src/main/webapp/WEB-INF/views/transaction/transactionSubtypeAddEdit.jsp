<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="valuemakers.app.rentye.util.TransactionSort" %>
<%@ include file="../header.jsp" %>
<c:set var="operation" value="Add"/>
<c:if test="${transactionSubtype.id>0}"> <c:set var="operation" value="Edit"/> </c:if>
<h1>${operation} transaction subtype</h1>
<br>
<form:form method="post" modelAttribute="transactionSubtype">
    Code: <form:input type="text" path="code"/> <form:errors path="code"/>
    Description: <form:input type="text" path="description"/> <form:errors path="description"/>
    Transaction type: <form:select itemValue="id" itemLabel="description" path="transactionType.id" items="${transactionTypes}"/> <form:errors path="transactionType.id"/>
    <br>
    <br>
    <input class="btn btn-secondary btn-lg" type="submit" value="Save">
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/transaction/transactionSubtype/list" role="button">Back</a>
</form:form>
<%@ include file="../footer.jsp" %>