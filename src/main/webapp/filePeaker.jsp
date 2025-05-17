<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String currentPath = request.getParameter("path");
    if (currentPath == null) currentPath = "";

    int lastSlash = currentPath.lastIndexOf('/');
    String parentPath = (lastSlash > 0)
            ? currentPath.substring(0, lastSlash)
            : "";
%>

<html>
<head>
    <title>Java Servlet App</title>
</head>
<body>
<header>
    <div>👤 ${sessionScope.userProfile.username}</div>
    <div>📅 ${timestamp}</div>
    <form action="${pageContext.request.contextPath}/logout" method="GET">
        <button type="submit">
            Выход
        </button>
    </form>
    </header>
    <h1>📌 ${currentPath}</h1>

    <a href="?path=${parentPath}">⬆️ Перейти на директорию выше</a>
    <c:forEach var="file" items="${files}">
        <li>
            <c:choose>
                <c:when test="${file.isDirectory()}">
                    <a href="?path=${fn:replace(file.getAbsolutePath(), '\\', '/')}">📁 ${file.getName()}</a>
                </c:when>
                <c:otherwise>
                    <a href="download?path=${fn:replace(file.getAbsolutePath(), '\\', '/')}">📃 ${file.getName()}</a>
                </c:otherwise>
            </c:choose>
        </li>
    </c:forEach>
    <c:if test="${empty files}">
        <li>Папка пуста</li>
    </c:if>
</body>
</html>