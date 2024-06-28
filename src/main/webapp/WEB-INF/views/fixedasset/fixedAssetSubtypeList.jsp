<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Fixed asset subtype list <a class = "error-message">${param.message}</a></h1>
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
                <a href="./edit/${fixedAssetSubtype.id}">Edit</a>
                <a href="./delete/${fixedAssetSubtype.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn" href="./add">Add fixed asset subtype</a>
</div>
<%@ include file="../footer.jsp" %>