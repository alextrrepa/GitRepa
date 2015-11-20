<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Редактирование учетной записи</title>

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
                        Редактирование учетной записи пользователя
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-file"></i> <a
                                href="${pageContext.request.contextPath}/admin/admin.jsp">Главная</a>
                        </li>
                        <li>
                            <i class="fa fa-users"></i> <a
                                href="${pageContext.request.contextPath}/admin?action=accounts">Учетные записи</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-user"></i> Редактирование учетной записи
                        </li>
                    </ol>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6">
                    <form role="form" action="admin?action=update&userId=${user.id}" method="post">

                        <div class="form-group">
                            <label>Имя</label>
                            <input class="form-control" type="text" name="name" value="${user.name}">
                        </div>

                        <div class="form-group">
                            <label>Фамилия</label>
                            <input class="form-control" type="text" name="surname" value="${user.surname}">
                        </div>

                        <div class="form-group">
                            <label>Имя входа</label>
                            <input class="form-control" type="text" name="username" value="${user.username}">
                        </div>

                        <div class="form-group">
                            <div class="checkbox">
                                <c:choose>

                                    <c:when test="${user.locked}">
                                        <label>
                                            <input type="checkbox" value="true" checked name="locked">
                                            Заблокирована
                                        </label>
                                    </c:when>

                                    <c:otherwise>
                                        <label>
                                            <input type="checkbox" value="false" name="locked">
                                            Заблокирована
                                        </label>
                                    </c:otherwise>

                                </c:choose>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Описание</label>
                                 <textarea class="form-control" rows="3" name="description">
                                     ${user.description}
                                 </textarea>
                        </div>

                        <div class="form-group">
                            <button class="btn btn-default" type="submit">Сохранить</button>
                        </div>

                    </form>

                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
