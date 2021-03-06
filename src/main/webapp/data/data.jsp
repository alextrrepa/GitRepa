<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Архивные данные</title>

    <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/global.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/data/jquery.datetimepicker.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/bootstrap/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">

    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/jqwidgets/styles/jqx.base.css" type="text/css"/>--%>

    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/data/data.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/data/jquery.datetimepicker.js"></script>
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
            <%--<a class="navbar-brand" href="index.jsp">SB Admin</a>--%>
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
                    <a href="${pageContext.request.contextPath}/index.jsp"><i class="fa fa-fw fa-file"></i>Главная</a>
                </li>
                <li <%--class="active"--%>>
                    <a href="${pageContext.request.contextPath}/view"><i class="fa fa-fw fa-dashboard"></i>Текущие
                        параметры</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/data?action=page"><i class="fa fa-fw fa-table"></i>
                        Архивные данные</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/report?action=page"><i
                            class="fa fa-fw fa-file-text"></i>
                        Отчеты</a>
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
                        Архивные данные
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-file"></i> <a
                                href="${pageContext.request.contextPath}/index.jsp">Главная</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-fw fa-table"></i> Архивные данные
                        </li>
                    </ol>
                </div>
            </div>

            <%-- Form --%>
            <div class="row">
                <c:if test="${error != null}">
                    <div class="alert alert-danger">
                        <strong>Ошибка !</strong> ${error}
                    </div>
                </c:if>


                <div class="col-lg-3">
                    <form role="form" action="${pageContext.request.contextPath}/data" method="get">
                        <input type="hidden" name="action" value="viewing">
                        <label>Начало периода</label>

                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-fw fa-table"></i></span>
                            <input class="form-control" id="datetimepicker1" type="text" placeholder="Начало"
                                   name="startdatetime" value="${param.startdatetime}">
                        </div>

                        <label>Конец периода</label>

                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-fw fa-table"></i></span>
                            <input class="form-control" id="datetimepicker2" type="text" placeholder="Конец"
                                   name="enddatetime" value="${param.enddatetime}">
                        </div>

                        <div class="form-group">
                            <label>Тип данных</label>
                            <select id="datatype" name="datatype" class="form-control">
                                <option value="hours">Часовые</option>
                                <option value="day">Суточные</option>
                            </select>
                        </div>
                        <button class="btn btn-primary">OK</button>
                    </form>
                </div>

                <%-- Grid --%>
                <div class="col-lg-9">
                    <h2>${requestScope.datatype}</h2>

                    <div class="table-responsive">
                        <table class="table table-hover table-bordered table-striped">
                            <tr>
                                <th>Дата/Время</th>
                                <c:forEach var="c" items="${colnames}">
                                    <th>
                                            ${c.columnName}
                                    </th>
                                </c:forEach>
                            </tr>

                            <c:forEach var="dataItems" items="${data}" varStatus="i">
                                <tr>
                                    <td>
                                        <fmt:formatDate value="${dataItems[i.index].dtime}" pattern="yyyy-MM-dd HH:mm"/>
                                    </td>
                                    <c:forEach var="hourItems" items="${dataItems}">
                                        <td>
                                            <fmt:formatNumber value="${hourItems.value}" maxFractionDigits="2"/>
                                        </td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>