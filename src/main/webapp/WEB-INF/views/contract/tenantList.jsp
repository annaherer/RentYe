<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Tenants list <a class = "error-message">${param.message}</a></h1>
<table>
    <tr>
        <th>Description</th>
        <th>Active</th>
        <th>Tenant actions</th>
    </tr>
    <c:forEach var="tenant" items="${tenants}">
        <tr>
            <td>${tenant.transactionParty.description}</td>
            <td>${tenant.active}</td>
            <td>
                <a href="./display/${tenant.id}">Display</a>
                <a href="./edit/${tenant.id}">Edit</a>
                <a href="./delete/${tenant.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a href="./add">Add tenant</a>
</div>
<%@ include file="../footer.jsp" %>