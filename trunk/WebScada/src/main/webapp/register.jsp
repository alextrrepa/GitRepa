<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<c:out value="${requestScope.message}"/>
<form action="Register.do" method="post">
    Login
    <input type="text" name="user"/>
    <br/>
    Password
    <input type="text" name="password"/>
    <br/>
    Email
    <input type="text" name="email"/>
    <br/>
    <input type="submit" value="save">
</form>
</body>
</html>
