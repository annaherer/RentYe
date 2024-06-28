<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Contractor type list <a class = "error-message">${param.message}</a></h1>
<table>
    <tr>
        <th>Description</th>
        <th>Contractor type actions</th>
    </tr>
    <c:forEach var="contractorType" items="${contractorTypes}">
        <tr>
            <td>${contractorType.description}</td>
            <td>
                <a href="./edit/${contractorType.id}">Edit</a>
                <a href="./delete/${contractorType.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn" href="./add">Add contractor type</a>
</div>
<%@ include file="../footer.jsp" %>