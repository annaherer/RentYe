<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>
<c:set var="operation" value="Add"/>
<c:if test="${userAccountAdminDTO.id > 0}"> <c:set var="operation" value="Edit"/> </c:if>
<h1>${operation} user account<a class="error-message"> <form:errors path="userAccountAdminDTO"/></a></h1>
<form:form method="post" modelAttribute="userAccountAdminDTO">
    Username:
    <c:if test="${operation eq 'Edit'}"> <form:input type="text" path="username" readonly="true"/> </c:if>
    <c:if test="${operation eq 'Add'}"> <form:input type="text" path="username"/> <form:errors path="username"/></c:if>
    <br>
    Email: <form:input type="email" path="email"/> <form:errors path="email"/><br>
    First name: <form:input type="text" path="firstName"/> <form:errors path="firstName"/><br>
    Last name: <form:input type="text" path="lastName"/> <form:errors path="lastName"/><br>
    Password: <form:input type="password" path="newPassword"/> <form:errors path="newPassword"/>
    <form:input type="hidden" path="password"/><br>
    Administrator: <form:checkbox path="admin"/> <form:errors path="admin"/><br>
    Enabled: <form:checkbox path="enabled"/> <form:errors path="enabled"/><br>
    <br><input class="btn btn-secondary btn" type="submit" value="Save">
    <a class="btn btn-secondary btn" href="/admin/adminPanel" role="button">Back</a>
</form:form>
<%@ include file="footer.jsp" %>