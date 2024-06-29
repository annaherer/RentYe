<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>
<c:set var="dateFieldFormat" value="date"/>
<c:if test="${operation == 'display'}">
    <h1>Display tenant</h1>
    <c:set var="dateFieldFormat" value="text"/>
</c:if>
<c:if test="${operation == 'add'}"> <h1>Add tenant</h1></c:if>
<c:if test="${operation == 'edit'}"> <h1>Edit tenant</h1></c:if>
<form:form method="post" modelAttribute="tenant">
    <fieldset <c:if test="${operation == 'display'}"> disabled = "disabled"</c:if>>
        Description: <form:input type="text" path="transactionParty.description"/> <form:errors path="transactionParty.description"/><br>
        First name: <form:input type="text" path="transactionParty.firstName"/> <form:errors path="transactionParty.firstName"/><br>
        Second name: <form:input type="text" path="transactionParty.secondName"/> <form:errors path="transactionParty.secondName"/><br>
        Surname: <form:input type="text" path="transactionParty.surname"/> <form:errors path="transactionParty.surname"/><br>
        Country: <form:input type="text" path="transactionParty.country"/> <form:errors path="transactionParty.country"/><br>
        City: <form:input type="text" path="transactionParty.city"/> <form:errors path="transactionParty.city"/><br>
        Zip code: <form:input type="text" path="transactionParty.zip"/> <form:errors path="transactionParty.zip"/><br>
        Street: <form:input type="text" path="transactionParty.street"/> <form:errors path="transactionParty.street"/><br>
        House number: <form:input type="text" path="transactionParty.houseNumber"/> <form:errors path="transactionParty.houseNumber"/><br>
        Flat number: <form:input type="text" path="transactionParty.flatNumber"/> <form:errors path="transactionParty.flatNumber"/><br>
        Phone number: <form:input type="text" path="transactionParty.phoneNumber"/> <form:errors path="transactionParty.phoneNumber"/><br>
        Email: <form:input type="text" path="transactionParty.email"/> <form:errors path="transactionParty.email"/><br>
        Citizenship: <form:input type="text" path="citizenship"/> <form:errors path="citizenship"/><br>
        Passport number: <form:input type="text" path="passportNumber"/> <form:errors path="passportNumber"/><br>
        Passport validity: <form:input type="${dateFieldFormat}" path="passportValidity"/> <form:errors path="passportValidity"/><br>
        Personal id number: <form:input type="text" path="personalIdNumber"/> <form:errors path="personalIdNumber"/><br>
        Personal id validity: <form:input type="${dateFieldFormat}" path="personalIdValidity"/> <form:errors path="personalIdValidity"/><br>
        PESEL: <form:input type="text" path="pesel"/> <form:errors path="pesel"/><br>
        Active: <form:checkbox path="active"/> <form:errors path="active"/><br>
    </fieldset>
    <c:if test="${!(operation == 'display')}"> <br><input class="btn btn-secondary btn-lg" type="submit" value="Submit"></c:if>
</form:form>
<c:if test="${operation == 'display'}">
    <a href="../list">Go back</a>
</c:if>
<%@ include file="../footer.jsp" %>