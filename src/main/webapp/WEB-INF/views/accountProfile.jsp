<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>
<h1>Account profile for ${userDetails.username}</h1>
<form:form method="post" modelAttribute="userAccountReducedDTO">
    Email: <form:input type="email" path="email"/> <form:errors path="email"/><br>
    First name: <form:input type="text" path="firstName"/> <form:errors path="firstName"/><br>
    Last name: <form:input type="text" path="lastName"/> <form:errors path="lastName"/><br>
    Administrator: <form:input type="text" path="admin" readonly = "true"/><br>
    <br><input class="btn btn-secondary btn" type="submit" value="Save">
    <a class="btn btn-secondary btn" href="./changePassword" role="button">Change password</a>
    <a class="btn btn-secondary btn" href="/" role="button">Home</a>
</form:form>
<%@ include file="footer.jsp" %>