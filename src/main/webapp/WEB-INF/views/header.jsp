<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, max-width=960px">
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
            integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
            crossorigin="anonymous"></script>
    <title>RentYe</title>
</head>
<body>
<nav class="navbar justify-content-center navbar-expand-lg navbar-dark" style="background-color: #000104"
     style="color: white" style="align-items: center">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">RentYe</a>
        <a class="navbar-brand" href="/apartment/list">Apartments</a>
        <a class="navbar-brand" href="/contract/tenant/list">Tenants</a>
        <a class="navbar-brand" href="/inProgress">Transactions</a> <%--/transaction/list--%>
        <a class="navbar-brand" href="/contractor/contractor/list">Contractors</a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="/" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">Dictionaries</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/transaction/transactionType/list">Transaction types</a></li>
                        <li><a class="dropdown-item" href="/transaction/transactionSubtype/list">Transaction
                            subtypes</a></li>
                        <li><a class="dropdown-item" href="/contractor/contractorType/list">Contractor types</a></li>
                        <li><a class="dropdown-item" href="/fixedAsset/fixedAssetType/list">Fixed asset types</a></li>
                        <li><a class="dropdown-item" href="/fixedAsset/fixedAssetSubtype/list">Fixed asset subtypes</a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="/" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Reporting
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/inProgress">Annual P&L per apartment</a></li>
                        <li><a class="dropdown-item" href="/inProgress">Multi year P&L</a></li>
                        <li><a class="dropdown-item" href="/inProgress">Fixed rate advancements</a></li>
                        <li><a class="dropdown-item" href="/inProgress">Fixed rate tax annual return</a></li>
                        <li><a class="dropdown-item" href="/inProgress">Progressive tax report</a></li>
                        <li><a class="dropdown-item" href="/inProgress">Progressive tax annual return</a></li>
                    </ul>
                </li>
                <sec:authorize access="!isAuthenticated()">
                    <a class="navbar-brand" href="/login">Login</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="/" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            <sec:authentication var = "username" property="name"/>
                            <sec:authentication var = "userFirstName" property="principal.userAccountDTO.firstName"/>
                            Welcome,
                            <c:if test="${empty userFirstName}">${username}</c:if>
                            <c:if test="${not empty userFirstName}">${userFirstName}</c:if>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/accountProfile">Account profile</a></li>
                            <sec:authorize access="hasRole('ADMIN')">
                                <li><a class="dropdown-item" href="/admin/adminPanel">Admin panel</a></li>
                            </sec:authorize>
                            <li><a class="dropdown-item" href="/logout">Logout</a></li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
<br>
<div id="screen-content">