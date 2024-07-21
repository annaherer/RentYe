<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./header.jsp" %>
<sec:authentication var="username" property="name"/>
<h1>Fixed asset type list <a class="error-message">${param.message}</a></h1>
<table>
    <tr>
        <th>Username</th>
        <th>Email</th>
        <th>Name</th>
        <th>Admin</th>
        <th>Enabled</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="userAccount" items="${allUsers}">
        <tr>
            <td>${userAccount.username}</td>
            <td>${userAccount.email}</td>
            <td>${userAccount.firstName} ${userAccount.lastName}</td>
            <td>${userAccount.admin}</td>
            <td>${userAccount.enabled}</td>
            <td>
                <c:if test="${not(userAccount.username eq username)}">
                    <a href="./editUser/${userAccount.id}">Edit</a>
                    <a href="./deleteUser/${userAccount.id}">Delete</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn" href="./addUser">Add user</a>
</div>
<%@ include file="./footer.jsp" %>