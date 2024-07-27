<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Contractors <a class = "error-message">${param.message}</a></h1>
<br>
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
                <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/contractor/contractor/display/${contractor.id}">Display</a>
                <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/contractor/contractor/edit/${contractor.id}">Edit</a>
                <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/contractor/contractor/delete/${contractor.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/contractor/contractor/add">Add</a>
</div>
<%@ include file="../footer.jsp" %>