<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Contractor types <a class = "error-message">${param.message}</a></h1>
<br>
<table>
    <tr>
        <th>Description</th>
        <th>Contractor type actions</th>
    </tr>
    <c:forEach var="contractorType" items="${contractorTypes}">
        <tr>
            <td>${contractorType.description}</td>
            <td>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/contractor/contractorType/edit/${contractorType.id}">Edit</a>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/contractor/contractorType/delete/${contractorType.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/contractor/contractorType/add">Add</a>
</div>
<%@ include file="../footer.jsp" %>