<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Для продолжения зарегистрируйтесь</h1>
<form action="${pageContext.request.contextPath}/registration" method="POST">
    Username: <input type="text" name="username"/>
    Password: <input type="password" name="password"/>
    Email: <input type="email" name="email">
    <input type="submit" value="Зарегистрироваться">
</form>

<form action="${pageContext.request.contextPath}/login" method="GET">
    <input type="submit" value="Уже есть аккаунт?">
</form>
</body>
</html>
