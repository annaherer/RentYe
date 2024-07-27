<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>
<c:if test="${operation == 'display'}"><h1>Display ${contractor.transactionParty.description}</h1></c:if>
<c:if test="${operation == 'add'}"><h1>Add contractor</h1></c:if>
<c:if test="${operation == 'edit'}"><h1>Edit contractor</h1></c:if>
<form:form method="post" modelAttribute="contractor">
    <fieldset <c:if test="${operation == 'display'}"> disabled = "disabled"</c:if>>
        Description: <form:input type="text" path="transactionParty.description"/> <form:errors
            path="transactionParty.description"/><br>
        Contact person: <form:input type="text" path="contactPerson"/> <form:errors path="contactPerson"/><br>
        Contractor type: <form:select itemValue="id" itemLabel="description" path="contractorType.id"
                                      items="${contractorTypes}"/> <form:errors path="contractorType.id"/><br>
        First name: <form:input type="text" path="transactionParty.firstName"/> <form:errors
            path="transactionParty.firstName"/><br>
        SecondName: <form:input type="text" path="transactionParty.secondName"/> <form:errors
            path="transactionParty.secondName"/><br>
        Surname: <form:input type="text" path="transactionParty.surname"/> <form:errors
            path="transactionParty.surname"/><br>
        Country: <form:input type="text" path="transactionParty.country"/> <form:errors
            path="transactionParty.country"/><br>
        City: <form:input type="text" path="transactionParty.city"/> <form:errors path="transactionParty.city"/><br>
        Zip: <form:input type="text" path="transactionParty.zip"/> <form:errors path="transactionParty.zip"/><br>
        Street: <form:input type="text" path="transactionParty.street"/> <form:errors
            path="transactionParty.street"/><br>
        House number: <form:input type="text" path="transactionParty.houseNumber"/> <form:errors
            path="transactionParty.houseNumber"/><br>
        Flat number: <form:input type="text" path="transactionParty.flatNumber"/> <form:errors
            path="transactionParty.flatNumber"/><br>
        Phone number: <form:input type="text" path="transactionParty.phoneNumber"/> <form:errors
            path="transactionParty.phoneNumber"/><br>
        Email: <form:input type="text" path="transactionParty.email"/> <form:errors path="transactionParty.email"/><br>
    </fieldset>
    <br>
    <c:if test="${!(operation == 'display')}">
        <input class="btn btn-secondary btn-lg" type="submit" value="Save">
    </c:if>
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/contractor/contractor/list">Back</a>
</form:form>
<%@ include file="../footer.jsp" %>