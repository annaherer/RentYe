<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h1>Fixed asset type list <a class = "error-message">${param.message}</a></h1>
<table>
    <tr>
        <th>Description</th>
        <th>Fixed asset type actions</th>
    </tr>
    <c:forEach var="fixedAssetType" items="${fixedAssetTypes}">
        <tr>
            <td>${fixedAssetType.description}</td>
            <td>
                <a href="./edit/${fixedAssetType.id}">Edit</a>
                <a href="./delete/${fixedAssetType.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<div>
    <a class="btn btn-secondary btn" href="./add">Add fixed asset type</a>
</div>
<%@ include file="../footer.jsp" %>