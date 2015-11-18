<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Учетные записи</title>

    <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/static/css/global.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/bootstrap/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">
    <%--<link href="${pageContext.request.contextPath}/static/css/admin/admin.css" rel="stylesheet">--%>
    <%--<link href="${pageContext.request.contextPath}/static/dist/themes/default-dark/style.min.css" rel="stylesheet">--%>
    <%--<link href="${pageContext.request.contextPath}/static/css/admin/waitMe.min.css" rel="stylesheet">--%>

    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/dist/jstree.min.js"></script>--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin/waitMe.min.js"></script>--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin/admin.js"></script>--%>
</head>
<body>
<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <%--<a class="navbar-brand" href="${pageContext.request.contextPath}/admin">Admin</a>--%>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>
                    <shiro:guest>Гость</shiro:guest>
                    <shiro:user>
                        <shiro:principal/>
                    </shiro:user>
                    <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <shiro:user>
                        <li>
                            <a href="${pageContext.request.contextPath}/profile.jsp"><i class="fa fa-fw fa-user"></i>Профиль</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/logout">
                                <i class="fa fa-fw fa-power-off"></i> Выйти
                            </a>
                        </li>
                    </shiro:user>
                    <shiro:guest>
                        <li>
                            <a href="${pageContext.request.contextPath}/login">
                                <i class="fa fa-fw fa-sign-in"></i>Войти
                            </a>
                        </li>
                    </shiro:guest>
                </ul>
            </li>
        </ul>

        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li <%--class="active"--%>>
                    <a href="${pageContext.request.contextPath}/admin/admin.jsp"><i class="fa fa-fw fa-file"></i>Главная</a>
                </li>
                <li <%--class="active"--%>>
                    <a href="${pageContext.request.contextPath}/admin/modbus_page.jsp"><i class="fa fa-fw fa-cogs"></i>
                        Настройки опроса</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin?action=accounts"><i class="fa fa-fw fa-users"></i>
                        Учетные записи</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/analyse"><i class="fa fa-fw fa-bar-chart-o"></i> Анализ
                        данных</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Учетные записи пользователей
                        <%--<small>Statistics Overview</small>--%>
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-file"></i> <a
                                href="${pageContext.request.contextPath}/admin/admin.jsp">Главная</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-users"></i> Учетные записи
                        </li>
                    </ol>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6">
                    <table class="table table-hover table-bordered table-striped">
                        <tr>
                            <th>Имя</th>
                            <th>Фамилия</th>
                            <th>Имя входа</th>
                            <th>Заблокирована</th>
                            <th>Описание</th>
                        </tr>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.name}</td>
                                <td>${user.surname}</td>
                                <td>${user.username}</td>
                                <td>${user.locked}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
