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

    <title>Администрирование</title>

    <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/static/css/global.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/bootstrap/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">

    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
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
                        Администрирование портала
                        <%--<small>Statistics Overview</small>--%>
                    </h1>
                    <ol class="breadcrumb">
                        <li class="active">
                            <i class="fa fa-file"></i> Администрирование
                        </li>
                    </ol>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="alert alert-info alert-dismissable">
                        <%--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>--%>
                        <%--<shiro:guest>--%>
                        <i class="fa fa-info-circle"></i> <strong>Добро пожаловать!</strong>
                        <%--</shiro:guest>--%>
                        <shiro:user>
                            <i class="fa fa-info-circle"></i> <strong>Вы вошли как, <shiro:principal/></strong>
                        </shiro:user>
                    </div>
                </div>
            </div>

            <%--
                        <div class="jumbotron">
                            <h1>Уважаемый пользователь!</h1>
                            <p>
                                Для того чтобы посещать необходимые вам ресурсы, необходимо сделать вход в систему
                            </p>

                            <p><a href="login.jsp" class="btn btn-primary btn-lg" role="button">Узнать больше &raquo;</a>
                            </p>
                        </div>
            --%>
        </div>
    </div>
</div>
</body>

<%--
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <title>Панель админа</title>
    <link href="${pageContext.request.contextPath}/static/css/reset.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/admin/admin.css" rel="stylesheet">
</head>
<body>

<header>
    <hgroup>
        <h1 class="site_title"><a href="${pageContext.request.contextPath}/admin/admin.jsp">Админка</a></h1>
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

<nav class="sidebar" &lt;%&ndash;style="height: 1728px"&ndash;%&gt;>
    <h3>Опрос</h3>
    <ul>
        <li class="icn_settings"><a href="modbus_page.jsp">Modbus настройки</a></li>
    </ul>
</nav>

<section class="content"&lt;%&ndash; style="height: 1728px"&ndash;%&gt;>
    <h4 class="alert_info">Добро пожаловать в панель администрирования.</h4>
</section>

</body>
--%>
</html>