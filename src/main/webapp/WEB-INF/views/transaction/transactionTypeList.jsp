<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Transaction type list</h1>
<table>
    <tr>
        <th>Description</th>
        <th>Default transaction sort</th>
    </tr>
    <c:forEach var="transactionType" items="${transactionTypes}">
        <tr>
            <td>${transactionType.description}</td>
            <td>${transactionType.defaultTransactionSort.description}</td>
            <td>
                <a href="./edit/${transactionType.id}">Edit</a>
                <a href="./delete/${transactionType.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a href="./add">Add transaction type</a>
</div>
<%@ include file="../footer.jsp" %>