<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>
<!DOCTYPE>
<html test="Test">
<head>
    <meta charset="utf-8">
    <title>Modbus Настройки</title>
    <link href="css/reset.css" rel="stylesheet">
    <link href="css/admin_style.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/admin_module.js"></script>
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
        <li class="icn_settings"><a href="ModbusEdit.do?action=getAll">Modbus настройки</a></li>
    </ul>
</nav>

<section class="content">
    <h4 class="alert_info">Настройка Modbus опроса.</h4>
    <article class="module modbus_tree">
        <header>
            <h3 class="tabs_involved">Структура Modbus</h3>
        </header>
        <ul class="tree">
            <li class="tree_item-li">
                <div class="tree_item fill_state_hover" style="display: inline-block">
                    Сервер
                </div>
                <ul>
                    <c:forEach items="${treeParams}" var="node">
                        <li class="tree_item-li" data-mtype=
                            <c:out value="${node.type}"/>
                                data-nodeid=<c:out value="${node.id}"/>
                                data-nodetype="node"
                                >
                            <div class="tree_item fill_state_hover" style="display: inline-block">
                                <c:out value="${node.name}"/>
                            </div>
                            <ul>
                                <c:set var="device" value="${node.deviceEntity}"/>
                                <c:forEach items="${device}" var="device">
                                    <li class="tree_item-li"
                                            data-nodeid=<c:out value="${device.id}"/>
                                            data-nodetype="device"
                                            >
                                        <div class="tree_item fill_state_hover" style="display: inline-block">
                                            <c:out value="${device.name}"/>
                                        </div>
                                        <ul>
                                            <c:set var="tag" value="${device.tagEntities}"/>
                                            <c:forEach items="${tag}" var="tag">
                                                <li class="tree_item-li"
                                                         data-nodeid=<c:out value="${tag.id}"/>
                                                         data-nodetype="tag"
                                                        >
                                                    <div class="tree_item fill_state_hover"
                                                         style="display: inline-block">
                                                        <c:out value="${tag.name}"/>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </ul>
    </article>
    <article class="module modbus_params">
        <header>
            <h3>Параметры</h3>
        </header>
        <div class="modbus_params_content">
            <div class="form_style">

            </div>
        </div>
    </article>
</section>
</body>
</html>