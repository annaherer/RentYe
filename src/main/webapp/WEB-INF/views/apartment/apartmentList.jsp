<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Apartments<a class="error-message">${param.message}</a></h1>
<br>
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
                <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/apartment/details/${apartment.id}">Details</a>
                <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/apartment/delete/${apartment.id}">Delete</a>
                <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/contract/list?apartment=${apartment.id}">Contracts</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/apartment/add" role="button">Add</a>
</div>
<%@ include file="../footer.jsp" %>