<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мониторинг</title>
    <link href="css/reset.css" rel="stylesheet">
    <link href="css/client.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/d3.min.js"></script>
    <script type="text/javascript" src="js/view.js"></script>
</head>
<body>
<%--<header class="header">--%>
    <div id="navigator">
        <ul id="nav">
            <li class="nav_tab"> <a href="#">
                <p>Home</p>
            </a> </li>
            <li class="nav_tab"> <a href="#">
                <p>Services</p>
            </a> </li>
            <li class="nav_tab"> <a href="#">
                <p>Works</p>
            </a> </li>
            <li class="nav_tab"> <a href="#">
                <p>Contact</p>
            </a> </li>
            <li class="nav_tab"> <a href="#">
                <p>Blog</p>
            </a> </li>
        </ul>
    </div>
    <div class="menu-icon">
        <div class="line_one"></div>
        <div class="line_two"></div>
        <div class="line_three"></div>
    </div>
    <h1 class="site_title"><a href="monitor.jsp">Test</a></h1>
    <h2 class="section_title">Test</h2>
    <%--<hgroup>--%>
    <%--</hgroup>--%>
<%--</header>--%>
<div id="svg" style="background-color: #787878"></div>
</body>
</html>
