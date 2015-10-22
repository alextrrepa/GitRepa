<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<%--<c:if test="${not empty requestScope.message}">
    <c:out value="${requestScope.message}"/>
</c:if>--%>
<%--<c:out value="${pageContext.request.contextPath}"/>--%>
<h1>Register</h1>

<form action="${pageContext.request.contextPath}/Register.do" method="post">
    Login
    <input type="text" name="username"/>
    <br/>
    Password
    <input type="text" name="password"/>
    <br/>
    Is blocked
    <input type="checkbox" name="blocked" value="blocked"/>
    <br/>
    Roles
    <select name="selectRole">
        <c:forEach var="r" items="${requestScope.roles}">
            <option value="${r.id}">${r.description}</option>
            <%--<c:out value="${r.role}"/>
            <c:out value="${r.description}"/>
            <c:out value="${r.id}"/>--%>
        </c:forEach>
        <%--<option value="admin">Администратор</option>
        <option value="user">Пользователь</option>--%>
    </select>
    <br/>
    <%--Description--%>
    <%--<textarea rows="10" cols="45" name="description"></textarea>--%>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
