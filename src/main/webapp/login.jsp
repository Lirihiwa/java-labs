<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Для продолжения войдите</h1>
        <form action="${pageContext.request.contextPath}/login" method="POST">
            Username: <input type="text" id="username" name="username" required/>
            Password: <input type="password" id="password" name="password" required/>
            <input type="submit" value="Войти">
        </form>

        <form action="${pageContext.request.contextPath}/registration" method="GET">
            <input type="submit" value="Пройти регистрацию">
        </form>
    </body>
</html>
