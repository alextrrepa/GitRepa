<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>Modbus Настройки</title>
    <link href="css/reset.css" rel="stylesheet">
    <link href="css/admin_style.css" rel="stylesheet">
    <link href="dist/themes/default-dark/style.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/admin_module.js"></script>
    <script type="text/javascript" src="dist/jstree.min.js"></script>
</head>
<body>

<header>
    <hgroup>
        <h1 class="site_title"><a href="admin.jsp">Админка</a></h1>

        <h2 class="section_title">Modbus</h2>
    </hgroup>
</header>

<section class="head_bar">
    <div class="user">
        <p><a href="">Admin</a></p>
    </div>
    <div class="breadcrumbs_container">
    </div>
</section>

<nav class="sidebar">
    <h3>Опрос</h3>
    <ul>
        <li class="icn_settings"><a href="modbus_page.jsp">Modbus настройки</a></li>
    </ul>
</nav>

<section class="content">
    <h4 class="alert_info">Настройка Modbus опроса.</h4>
    <article class="module modbus_tree">
        <header>
            <h3 class="tabs_involved">Структура Modbus</h3>
        </header>
        <div id="tree"></div>
    </article>
    <article class="module modbus_params">
        <header>
            <h3>Параметры</h3>
        </header>
        <div class="modbus_params_content">
            <div class="form_style"></div>
        </div>
    </article>
</section>
</body>
</html>