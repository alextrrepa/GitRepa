<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <title>Панель админа</title>
    <link href="css/reset.css" rel="stylesheet">
    <link href="css/admin_style.css" rel="stylesheet">
</head>
<body>

<header>
    <hgroup>
        <h1 class="site_title"><a href="admin.jsp">Админка</a></h1>
        <h2 class="section_title">Панель</h2>
    </hgroup>
</header>

<section class="head_bar">
    <div class="user">
        <p><a href="">Admin</a></p>
    </div>
    <div class="breadcrumbs_container">
    </div>
</section>

<nav class="sidebar" <%--style="height: 1728px"--%>>
    <h3>Опрос</h3>
    <ul>
        <li class="icn_settings"><a href="ModbusEdit.do?action=getAll">Modbus настройки</a></li>
    </ul>
</nav>

<section class="content"<%-- style="height: 1728px"--%>>
    <h4 class="alert_info">Добро пожаловать в панель администрирования.</h4>

</section>

</body>
</html>