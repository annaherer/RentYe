<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Transaction subtype list <a class = "error-message">${param.message}</a></h1>
<table>
    <tr>
        <th>Transaction type</th>
        <th>Code</th>
        <th>Description</th>
        <th>Last index</th>
        <th>Transaction subtype actions</th>
    </tr>
    <c:forEach var="transactionSubtype" items="${transactionSubtypes}">
        <tr>
            <td>${transactionSubtype.transactionType.description}</td>
            <td>${transactionSubtype.code}</td>
            <td>${transactionSubtype.description}</td>
            <td>${transactionSubtype.lastIndex}</td>
            <td>
                <a href="./edit/${transactionSubtype.id}">Edit</a>
                <a href="./delete/${transactionSubtype.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn" href="./add">Add transaction subtype</a>
</div>
<%@ include file="../footer.jsp" %>