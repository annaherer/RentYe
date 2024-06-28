<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Apartments list <a class = "error-message">${param.message}</a></h1>
<table>
    <tr>
        <th>Description</th>
        <th>Active</th>
        <th>Apartment actions</th>
    </tr>
    <c:forEach var="apartment" items="${apartments}">
        <tr>
            <td>${apartment.description}</td>
            <td>${apartment.active}</td>
            <td>
                <a href="../dashboard/display/${apartment.id}">Dashboard</a>
                <a href="./edit/${apartment.id}">Edit</a>
                <a href="./delete/${apartment.id}">Delete</a>
                <a href="/contract/list?apartment=${apartment.id}">Contracts</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn-lg" href="./add" role="button">Add apartment</a>
</div>
<%@ include file="../footer.jsp" %>