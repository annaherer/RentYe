<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Transaction types <a class = "error-message">${param.message}</a></h1>
<br>
<table>
    <tr>
        <th>Description</th>
        <th>Default transaction sort</th>
        <th>Transaction type actions</th>
    </tr>
    <c:forEach var="transactionType" items="${transactionTypes}">
        <tr>
            <td>${transactionType.description}</td>
            <td>${transactionType.defaultTransactionSort.description}</td>
            <td>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/transaction/transactionType/edit/${transactionType.id}">Edit</a>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/transaction/transactionType/delete/${transactionType.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/transaction/transactionType/add">Add</a>
</div>
<%@ include file="../footer.jsp" %>