<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="valuemakers.app.rentye.util.TransactionSort" %>
<%@ include file="../header.jsp" %>
<c:set var="operation" value="Add"/>
<c:if test="${fixedAssetType.id>0}"> <c:set var="operation" value="Edit"/> </c:if>
<h1>${operation} fixed asset type</h1>
<br>
<form:form method="post" modelAttribute="fixedAssetType">
    Description: <form:input type="text" path="description"/> <form:errors path="description"/><br>
    <br>
    <input class="btn btn-secondary btn-lg" type="submit" value="Save">
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/fixedAsset/fixedAssetType/list" role="button">Back</a>
</form:form>
<%@ include file="../footer.jsp" %>