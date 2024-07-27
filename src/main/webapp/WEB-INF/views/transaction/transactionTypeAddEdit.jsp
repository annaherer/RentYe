<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="valuemakers.app.rentye.model.TransactionSort" %>
<%@ include file="../header.jsp" %>
<c:set var="operation" value="Add"/>
<c:if test="${transactionType.id>0}"> <c:set var="operation" value="Edit"/> </c:if>
<h1>${operation} transaction type</h1>
<br>
<form:form method="post" modelAttribute="transactionType">
    Description: <form:input type="text" path="description"/> <form:errors path="description"/><br>
    Default transaction sort:
    <form:select path="defaultTransactionSort">
        <form:option value="${TransactionSort.REVENUE}" label="${TransactionSort.REVENUE.description}"/>
        <form:option value="${TransactionSort.COST}" label="${TransactionSort.COST.description}"/>
        <form:option value="${TransactionSort.PURCHASE}" label="${TransactionSort.PURCHASE.description}"/>
        <form:option value="${TransactionSort.EXCLUDED}" label="${TransactionSort.EXCLUDED.description}"/>
    </form:select> <form:errors path="defaultTransactionSort"/>
    <br>
    <br>
    <input class="btn btn-secondary btn-lg" type="submit" value="Save">
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/transaction/transactionType/list" role="button">Back</a>
</form:form>
<%@ include file="../footer.jsp" %>