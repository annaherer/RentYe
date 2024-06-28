<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Contractor list <a class = "error-message">${param.message}</a></h1>
<table>
    <tr>
        <th>Contractor type</th>
        <th>Description</th>
        <th>Contact person</th>
        <th>Contractor actions</th>
    </tr>
    <c:forEach var="contractor" items="${contractors}">
        <tr>
            <td>${contractor.contractorType.description}</td>
            <td>${contractor.transactionParty.description}</td>
            <td>${contractor.contactPerson}</td>
            <td>
                <a href="./display/${contractor.id}">Display</a>
                <a href="./edit/${contractor.id}">Edit</a>
                <a href="./delete/${contractor.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn" href="./add">Add contractor</a>
</div>
<%@ include file="../footer.jsp" %>