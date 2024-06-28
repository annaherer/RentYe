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
                <a href="./details/${contract.lastContractPeriod.id}">Details</a>
                <a href="./delete/${contract.lastContractPeriod.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a href="./addContract?apartment=${apartment.id}">Add contract</a>
</div>
<%@ include file="../footer.jsp" %>