<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная</title>
    <link href="${pageContext.request.contextPath}/static/css/reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/index.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/index.js"></script>
</head>
<body>

<header class="header">
    <h1 class="site_title">
        <div class="menu-btn">&#9776; Меню</div>
        <!--<a href="index.html">Demo</a>-->
    </h1>
    <h2 class="section_title">Главная страница</h2>
</header>

<nav class="pushy pushy-left">
    <ul>
        <li><a href="${pageContext.request.contextPath}/index.jsp">На главную</a></li>
        <li><a href="${pageContext.request.contextPath}/view/monitor.jsp">Текущие параметры</a></li>
        <li><a href="${pageContext.request.contextPath}/data/data.jsp">Аналитика</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/admin.jsp">Админка</a></li>
    </ul>
</nav>
<div class="site-overlay"></div>

<div id="container">
    <section class="secondary_bar">
        <div class="user">
            <p><a href="#">Username</a></p>
            <a class="logout_user" href="#">Выйти</a>
        </div>
        <div class="breadcrumbs_container"></div>
    </section>
    <shiro:principal/>

    <shiro:authenticated>
        User is authenticated !!!
    </shiro:authenticated>

    <shiro:notAuthenticated>
        User is not authenticated !!!
    </shiro:notAuthenticated>
</div>


</body>
</html>
