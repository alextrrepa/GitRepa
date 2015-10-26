<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Вход</title>
    <link href="${pageContext.request.contextPath}/static/css/reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/auth/auth.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/auth/auth.js"></script>
</head>
<body>
<form method="post" action="Login.do" class="login">
    <p>
        <label for="login">Логин:</label>
        <input type="text" name="login" id="login">
    </p>

    <p>
        <label for="password">Пароль:</label>
        <input type="password" name="password" id="password">
    </p>

    <p class="login-submit">
        <button type="submit" class="login-button">Вход</button>
    </p>
</form>
</body>
</html>