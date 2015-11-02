<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
Добро пожаловать <shiro:principal/> ！<a href="${pageContext.request.contextPath}/logout">Выйти</a>
</body>
</html>
