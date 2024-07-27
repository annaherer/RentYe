<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Fixed asset subtypes <a class = "error-message">${param.message}</a></h1>
<br>
<table>
    <tr>
        <th>Fixed asset type</th>
        <th>Description</th>
        <th>Fixed asset subtype actions</th>
    </tr>
    <c:forEach var="fixedAssetSubtype" items="${fixedAssetSubtypes}">
        <tr>
            <td>${fixedAssetSubtype.fixedAssetType.description}</td>
            <td>${fixedAssetSubtype.description}</td>
            <td>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/fixedAsset/fixedAssetSubtype/edit/${fixedAssetSubtype.id}">Edit</a>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/fixedAsset/fixedAssetSubtype/delete/${fixedAssetSubtype.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/fixedAsset/fixedAssetSubtype/add">Add</a>
</div>
<%@ include file="../footer.jsp" %>