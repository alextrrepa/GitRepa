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
                <form action="" method="post">
                    <label><span>Имя узла <span class="required">*</span></span>
                        <input type="text" name="nodename" class="input-field"/>
                    </label>

                    <label><span>Тип modbus <span class="required">*</span></span>
                        <select name="modbustype" class="select-field">
                            <option value="rtu">Rtu</option>
                            <option value="tcp">Tcp</option>
                        </select>
                    </label>

                    <label><span>Порт <span class="required">*</span></span>
                        <input type="text" name="port" class="input-field"/>
                    </label>

                    <label><span>Скорость <span class="required">*</span></span>
                        <select name="baudrate" class="select-field">
                            <option value="1200">1200</option>
                            <option value="1800">1800</option>
                            <option value="2400">2400</option>
                            <option value="4800">4800</option>
                            <option value="9600">9600</option>
                            <option value="19200">19200</option>
                            <option value="38400">38400</option>
                            <option value="57600">57600</option>
                            <option value="115200">115200</option>
                        </select>
                    </label>

                    <label><span>Данные <span class="required">*</span></span>
                        <select name="databits" class="select-field">
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                        </select>
                    </label>

                    <label><span>Четность <span class="required">*</span></span>
                        <input type="text" name="parity" class="input-field"/>
                    </label>

                    <label><span>Стоп биты <span class="required">*</span></span>
                        <select name="parity" class="select-field">
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="1.5">1.5</option>
                            <option value="2">2</option>
                        </select>
                    </label>

                    <label><span>Повторы при ошибке <span class="required">*</span></span>
                        <input type="text" name="retries" class="input-field">
                    </label>

                    <label><span>Время ответа <span class="required">*</span></span>
                        <input type="text" name="timeout" class="input-field">
                    </label>

                    <label><span>Период опроса<span class="required">*</span></span>
                        <input type="text" name="period" class="input-field">
                    </label>
                    <input type="submit" value="Сохранить"/>
                </form>
            </div>
        </div>
    </article>
</section>
</body>
</html>