<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>

<h1>Contracts for apartment ${apartment.description} <a class = "error-message">${param.message}</a></h1>
<table>
    <tr>
        <th>Active</th>
        <th>Start date</th>
        <th>End date</th>
        <th>Main tenant</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="contract" items="${contracts}">
        <tr>
            <td>${contract.active}</td>
            <td>${contract.startDate}</td>
            <td>${contract.endDate}</td>
            <td>${contract.mainTenant}</td>
            <td>
                <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/contract/details/${contract.lastContractPeriod.id}">Details</a>
                <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/contract/deleteContract/${contract.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/contract/addContract?apartment=${apartment.id}">Add</a>
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/apartment/list">Back</a>
</div>
<%@ include file="../footer.jsp" %>