<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Tenants<a class = "error-message">${param.message}</a></h1>
<br>
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
                <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/contract/tenant/display/${tenant.id}">Display</a>
                <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/contract/tenant/edit/${tenant.id}">Edit</a>
                <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/contract/tenant/delete/${tenant.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/contract/tenant/add">Add</a>
</div>
<%@ include file="../footer.jsp" %>