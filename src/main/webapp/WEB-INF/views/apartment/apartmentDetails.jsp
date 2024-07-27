<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>
<c:set var="dateFieldFormat" value="date"/>
<c:if test="${operation == 'display'}">
    <h1>Apartment ${apartment.description} <a class="error-message">${param.message}</a></h1>
    <c:set var="dateFieldFormat" value="text"/>
</c:if>
<c:if test="${operation == 'add'}"><h1>Add apartment</h1></c:if>
<c:if test="${operation == 'edit'}"><h1>Edit apartment</h1></c:if>
<form:form method="post" modelAttribute="apartment">
    <fieldset <c:if test="${operation == 'display'}"> disabled = "disabled"</c:if>>
        <br>
        <h2>Details</h2>
        Description: <form:input type="text" path="description"/> <form:errors path="description"/><br>
        <form:textarea rows="4" cols="50" path="detailedDescription" placeholder="Apartment description here"/> <form:errors
            path="detailedDescription"/><br>
        Usable area: <form:input type="text" path="usableArea"/> <form:errors path="usableArea"/><br>
        Street: <form:input type="text" path="street"/> <form:errors path="street"/><br>
        House number: <form:input type="text" path="houseNumber"/> <form:errors path="houseNumber"/><br>
        Flat number: <form:input type="text" path="flatNumber"/> <form:errors path="flatNumber"/><br>
        City: <form:input type="text" path="city"/> <form:errors path="city"/><br>
        Zip code: <form:input type="text" path="zip"/> <form:errors path="zip"/><br>
        Country: <form:input type="text" path="country"/> <form:errors path="country"/><br>
        Acquisition date: <form:input type="${dateFieldFormat}" path="acquisitionDate"/> <form:errors
            path="acquisitionDate"/><br>
        Acceptance to use date: <form:input type="${dateFieldFormat}" path="acceptanceToUseDate"/> <form:errors
            path="acceptanceToUseDate"/><br>
        Land mortgage register number: <form:input type="text" path="landMortgageRegisterNumber"/> <form:errors
            path="landMortgageRegisterNumber"/><br>
        Notarial act number: <form:input type="text" path="notarialActNumber"/> <form:errors
            path="notarialActNumber"/><br>
        Active: <form:checkbox path="active"/> <form:errors path="active"/><br>
        Date sold: <form:input type="${dateFieldFormat}" path="dateSold"/> <form:errors path="dateSold"/><br>
    </fieldset>
    <br>
    <c:if test="${operation == 'edit' || operation == 'add'}">
        <input class="btn btn-secondary btn-lg" type="submit" value="Save">
        <c:if test="${operation == 'edit'}">
            <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/apartment/details/${apartment.id}">Back</a><br>
        </c:if>
        <c:if test="${operation == 'add'}">
            <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/apartment/list">Back</a><br>
        </c:if>
    </c:if>
</form:form>

<%-- Content displayed in display mode only --%>
<c:if test="${operation == 'display'}">
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/apartment/edit/${apartment.id}">Edit</a>
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/apartment/list">Back</a><br>

    <%-- Contractors assigned to apartment --%>
    <br>
    <br>
    <h2>Contractors assigned</h2>
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
                    <a class="btn btn-secondary btn-sm"
                       href="/contractor/contractor/display/${apartmentContractor.contractor.id}">Display</a>
                    <a class="btn btn-secondary btn-sm"
                       href="${pageContext.request.contextPath}/apartment/contractors/toggleSettlement/${apartmentContractor.id}">Toggle settlement</a>
                    <a class="btn btn-secondary btn-sm"
                       href="${pageContext.request.contextPath}/apartment/details/${apartment.id}?apartmentContractor=${apartmentContractor.id}">Schedule payment</a>
                    <a class="btn btn-secondary btn-sm"
                       href="${pageContext.request.contextPath}/apartment/contractors/delete/${apartmentContractor.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${contractors.size()>0}">
        <br>
        <form:form action="${pageContext.request.contextPath}/apartment/contractors/add/${apartment.id}" method="post" modelAttribute="apartmentContractor">
            Contractor: <form:select itemValue="id" itemLabel="transactionParty.description" path="contractor.id"
                                     items="${contractors}"/>
            <input class="btn btn-secondary btn-lg" type="submit" value="Add">
        </form:form>
    </c:if>
    <c:if test="${apartmentContractor.id > 0}">
        <%-- Payment schedule for contractor --%>
        <br>
        <br>
        <h2>Payment schedule for ${apartmentContractor.contractor.transactionParty.description}</h2>
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
                        <a class="btn btn-secondary btn-sm"
                           href="${pageContext.request.contextPath}/apartment/contractors/deleteScheduledPayment/${scheduledPayment.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <form:form action="${pageContext.request.contextPath}/apartment/contractors/addScheduledPayment/${apartmentContractor.id}" method="post"
                   modelAttribute="scheduledPayment">
            <p><b>Schedule payment</b></p>
            <form:input type="date" path="date"/> <form:errors path="date"/>
            <form:input type="number" path="amount"/> <form:errors path="amount"/>
            <input class="btn btn-secondary btn-lg" type="submit" value="Add">
        </form:form>
    </c:if>
</c:if>
<%@ include file="../footer.jsp" %>