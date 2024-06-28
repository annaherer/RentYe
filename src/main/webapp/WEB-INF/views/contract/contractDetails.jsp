<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>

<c:set var="dateFieldFormat" value="date"/>
<c:if test="${operation == 'display'}">
    <h1>Contract for apartment ${contractPeriod.contract.apartment.description}</h1>
    <c:set var="dateFieldFormat" value="text"/>
</c:if>
<c:if test="${operation == 'add'}"><h1>New contract for
    apartment ${contractPeriod.contract.apartment.description}</h1></c:if>
<c:if test="${operation == 'edit'}"><h1>Edit contract for
    apartment ${contractPeriod.contract.apartment.description}</h1></c:if>

<form:form method="post" modelAttribute="contractPeriod">
    <fieldset <c:if test="${operation == 'display'}"> disabled = "disabled"</c:if>>
        <h2>Contract information</h2>
        Active: <form:input type = "text" path="contract.active" readonly="true"/><br>
        Tenants:
        <form:select path="contract.tenants" multiple="true">
            <c:forEach items="${allTenants}" var="tenant">
                <c:set var="contains" value="false"/>
                <c:forEach items="${contractPeriod.contract.tenants}" var="contractTenant">
                    <c:if test="${contractTenant.id eq tenant.id}">
                        <c:set var="contains" value="true"/>
                    </c:if>
                </c:forEach>
                <c:if test="${contains}">
                    <form:option value="${tenant.id}" label="${tenant.transactionParty.description}" Selected="True"/>
                </c:if>
                <c:if test="${!contains}">
                    <form:option value="${tenant.id}" label="${tenant.transactionParty.description}"/>
                </c:if>
            </c:forEach>
        </form:select><br><br>
        Deposit amount: <form:input type="number" path="contract.deposit"/> <form:errors path="contract.deposit"/><br>
        Deposit held: <form:input type="number" path="contract.depositHeld"/> <form:errors path="contract.depositHeld"/><br>
        <h2>Contract period (annex) information</h2>
        Active: <form:input type = "text" path="active" readonly="true"/><br>
        Period (annex) number: <form:input type="number" path="sequenceNumber" readonly="true"/><br>
        Main tenant: <form:select itemValue="id" itemLabel="transactionParty.description" path="mainTenant.id"
                                  items="${allTenants}"/> <form:errors path="mainTenant.id"/><br>
        Contract term:
        <form:input type="${dateFieldFormat}" path="startDate"/> <form:errors path="startDate"/> -
        <form:input type="${dateFieldFormat}" path="endDate"/> <form:errors path="endDate"/><br>
        Date signed: <form:input type="${dateFieldFormat}" path="signDate"/> <form:errors path="signDate"/><br>
        Monthly rent: <form:input type="number" path="rentAmount"/> <form:errors path="rentAmount"/><br>
        Service charges (est.): <form:input type="number" path="serviceChargesAmount"/> <form:errors
            path="serviceChargesAmount"/><br>
        Payment day of month: <form:input type="number" path="paymentDay"/> <form:errors path="paymentDay"/><br>
        <form:hidden path="contract"/>
        <form:hidden path="contract.apartment"/>
    </fieldset>
    <br>
    <c:if test="${operation == 'edit' || operation == 'add'}">
        <input class="btn btn-secondary btn-lg" type="submit" value="Save">
        <a href="../details/${contractPeriod.id}">Back</a><br>
    </c:if>
</form:form>

<c:if test="${operation == 'display'}">
    <a href="../edit/${contractPeriod.id}">Edit</a>
    <a href="../toggleActivePeriod/${contractPeriod.id}">Toggle period</a>
    <a href="../list?apartment=${contractPeriod.contract.apartment.id}">Back to contract list</a><br>
    <h2>Contract periods</h2><br>
    <table>
        <tr>
            <th>Sequence</th>
            <th>Start date</th>
            <th>End date</th>
            <th>Rent amount</th>
            <th>Main tenant</th>
            <th>Active</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="contractPeriodFromList" items="${contractPeriodsList}">
            <tr>
                <td>${contractPeriodFromList.sequenceNumber}</td>
                <td>${contractPeriodFromList.startDate}</td>
                <td>${contractPeriodFromList.endDate}</td>
                <td>${contractPeriodFromList.rentAmount}</td>
                <td>${contractPeriodFromList.mainTenant.transactionParty.description}</td>
                <td>${contractPeriodFromList.active}</td>
                <td>
                    <a href="../details/${contractPeriodFromList.id}">Details</a>
                    <a href="../deletePeriod/${contractPeriodFromList.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="../addPeriod?contract=${contractPeriod.contract.id}">Add contract period</a>
</c:if>
<%@ include file="../footer.jsp" %>