<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>
<c:set var="dateFieldFormat" value="date"/>
<c:if test="${operation == 'display'}">
    <h1>Apartment dashboard</h1>
    <c:set var="dateFieldFormat" value="text"/>
</c:if>
<c:if test="${operation == 'add'}"> <h1>Add apartment</h1></c:if>
<c:if test="${operation == 'edit'}"> <h1>Edit apartment</h1></c:if>
<form:form method="post" modelAttribute="apartment">
    <fieldset <c:if test="${operation == 'display'}"> disabled = "disabled"</c:if>>
        Description: <form:input type="text" path="description"/> <form:errors path="description"/><br>
        Detailed description: <form:input type="text" path="detailedDescription"/> <form:errors path="detailedDescription"/><br>
        Usable area: <form:input type="text" path="usableArea"/> <form:errors path="usableArea"/><br>
        Street: <form:input type="text" path="street"/> <form:errors path="street"/><br>
        House number: <form:input type="text" path="houseNumber"/> <form:errors path="houseNumber"/><br>
        Flat number: <form:input type="text" path="flatNumber"/> <form:errors path="flatNumber"/><br>
        City: <form:input type="text" path="city"/> <form:errors path="city"/><br>
        Zip code: <form:input type="text" path="zip"/> <form:errors path="zip"/><br>
        Country: <form:input type="text" path="country"/> <form:errors path="country"/><br>
        Acquisition date: <form:input type="${dateFieldFormat}" path="acquisitionDate"/> <form:errors path="acquisitionDate"/><br>
        Acceptance to use date: <form:input type="${dateFieldFormat}" path="acceptanceToUseDate"/> <form:errors path="acceptanceToUseDate"/><br>
        Land mortgage register number: <form:input type="text" path="landMortgageRegisterNumber"/> <form:errors path="landMortgageRegisterNumber"/><br>
        Notarial act number: <form:input type="text" path="notarialActNumber"/> <form:errors path="notarialActNumber"/><br>
        Active: <form:checkbox path="active"/> <form:errors path="active"/><br>
        Date sold: <form:input type="${dateFieldFormat}" path="dateSold"/> <form:errors path="dateSold"/><br>
    </fieldset>
    <br>
    <c:if test="${operation == 'edit' || operation == 'add'}">
        <input class="btn btn-secondary btn-lg" type="submit" value="Save">
        <c:if test="${operation == 'edit'}">
            <a href="../details/${apartment.id}">Back</a><br>
        </c:if>
        <c:if test="${operation == 'add'}">
            <a href="./list">Back to apartment list</a><br>
        </c:if>
    </c:if>
</form:form>

<%-- Content displayed in Dashboard mode only --%>
<c:if test="${operation == 'display'}">
    <a href="../edit/${apartment.id}">Edit</a>
    <a class="btn btn-secondary btn-lg" href="../list" role="button">Go back</a>

    <%-- Contractors assigned to apartment --%>
    <br>
    <br>
    <h2>Contractors assigned to apartment</h2>
    <table>
        <tr>
            <th>Contractor type</th>
            <th>Description</th>
            <th>Settle payments with tenants</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="apartmentContractor" items="${apartment.apartmentContractors}">
            <tr>
                <td>${apartmentContractor.contractor.contractorType.description}</td>
                <td>${apartmentContractor.contractor.transactionParty.description}</td>
                <td>${apartmentContractor.settlePaymentsWithTenant}</td>
                <td>
                    <a href="/contractor/contractor/display/${apartmentContractor.contractor.id}">Display</a>
                    <a href="../contractors/toggleSettlement/${apartmentContractor.id}">Toggle settlement</a>
                    <a href="../details/${apartment.id}?apartmentContractor=${apartmentContractor.id}">Payment schedule</a>
                    <a href="../contractors/delete/${apartmentContractor.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form:form action="../contractors/add/${apartment.id}" method="post" modelAttribute="apartmentContractor">
        Contractor: <form:select itemValue="id" itemLabel="transactionParty.description" path="contractor.id" items="${contractors}"/>
        <input class="btn btn-secondary btn-sm" type="submit" value="Add contractor to apartment">
    </form:form>

    <c:if test="${apartmentContractor.id > 0}">
        <%-- Payment schedule for contractor --%>
        <br>
        <br>
        <h2>Payment schedule for contractor ${apartmentContractor.contractor.transactionParty.description}</h2>
        <table>
            <tr>
                <th>Date</th>
                <th>Amount</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="scheduledPayment" items="${apartmentContractor.scheduledPayments}">
                <tr>
                    <td>${scheduledPayment.date}</td>
                    <td>${scheduledPayment.amount}</td>
                    <td>
                        <a href="../contractors/deleteScheduledPayment/${scheduledPayment.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <form:form action="../contractors/addScheduledPayment/${apartmentContractor.id}" method="post" modelAttribute="scheduledPayment">
            <form:input type="date" path="date"/> <form:errors path="date"/>
            <form:input type="number" path="amount"/> <form:errors path="amount"/>
            <input class="btn btn-secondary btn" type="submit" value="Add scheduled payment">
        </form:form>
    </c:if>
</c:if>
<%@ include file="../footer.jsp" %>