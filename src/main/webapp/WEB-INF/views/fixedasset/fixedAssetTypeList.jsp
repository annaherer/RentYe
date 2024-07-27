<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Fixed asset types <a class = "error-message">${param.message}</a></h1>
<br>
<table>
    <tr>
        <th>Description</th>
        <th>Fixed asset type actions</th>
    </tr>
    <c:forEach var="fixedAssetType" items="${fixedAssetTypes}">
        <tr>
            <td>${fixedAssetType.description}</td>
            <td>
                <a class="btn btn-secondary btn" href="${pageContext.request.contextPath}/fixedAsset/fixedAssetType/edit/${fixedAssetType.id}">Edit</a>
                <a class="btn btn-secondary btn" href="${pageContext.request.contextPath}/fixedAsset/fixedAssetType/delete/${fixedAssetType.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/fixedAsset/fixedAssetType/add">Add</a>
</div>
<%@ include file="../footer.jsp" %>