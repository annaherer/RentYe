<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Please change your password</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" integrity="sha384-oOE/3m0LUMPub4kaC09mrdEhIc+e3exm4xOGxAmuFXhBNF4hcg/6MiAXAf5p0P56" crossorigin="anonymous"/>
</head>
<body>
<div class="container">
    <form:form class="form-signin" method="post" modelAttribute="changePasswordDTO">
        <h2 class="form-signin-heading">Please enter your new password</h2>
        <p>
            <form:label path="newPassword" class="sr-only">New Password</form:label>
            <form:input type="password" path="newPassword" class="form-control" placeholder="Password"/>
        </p>
        <p>
            <form:label path="newPasswordRetyped" class="sr-only">New Password</form:label>
            <form:input type="password" path="newPasswordRetyped" class="form-control" placeholder="Retype password"/>
        </p>
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Change password">
    </form:form>
</div>
</body></html>