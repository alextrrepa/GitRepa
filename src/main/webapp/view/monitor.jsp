<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мониторинг</title>
    <link href="${pageContext.request.contextPath}/static/css/reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/view/view.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/view/d3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/view/view.js"></script>
</head>
<body>
<header class="header">
    <h1 class="site_title">
        <div class="menu-btn">&#9776; Меню</div>
        <!--<a href="index.html">Demo</a>-->
    </h1>
    <h2 class="section_title">Demo</h2>
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

<section id="container">
    <article>
        <div id="image"></div>
    </article>
</section>
<%--
<div id="navigator">
    <ul id="nav">
        <li class="nav_tab">
            <a href="monitor.jsp"><p>Мониторинг</p></a>
        </li>
        <li class="nav_tab">
            <a href="#"><p>Demo</p></a>
        </li>
        <li class="nav_tab">
            <a href="#"><p>Demo</p></a>
        </li>
        <li class="nav_tab">
            <a href="#"><p>Demo</p></a>
        </li>
        <li class="nav_tab">
            <a href="#"><p>Demo</p></a>
        </li>
    </ul>
</div>
<div class="menu-icon">
    <div class="line_one"></div>
    <div class="line_two"></div>
    <div class="line_three"></div>
</div>
<header class="header">
    <hgroup>
        <div class="site_title"></div>
        <h2 class="section_title">Demo</h2>
    </hgroup>
</header>
--%>
</body>
</html>
