<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%--<%@page contentType="application/pdf" %>--%>
<html>
<head>
    <title>Просмотр данных</title>
    <link href="jq_ui/jquery-ui.min.css" rel="stylesheet" type="text/css">
    <link href="jtable/themes/metro/darkgray/jtable.min.css" rel="stylesheet" type="text/css">
    <link href="css/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>
    <link href="css/data.css" rel="stylesheet" type="text/css">

    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="jq_ui/jquery-ui.min.js" type="text/javascript"></script>
    <script src="jtable/jquery.jtable.min.js" type="text/javascript"></script>
    <script src="js/jquery.datetimepicker.js"></script>
    <script src="js/data.js" type="text/javascript"></script>
    <script>
        $(function () {
            $('#tableContainer').jtable({
                title: 'Demo',
                paging: true,
                pageSize: 5,
                actions: {
                    listAction: 'Report.do?actions=hoursData'
                },
                fields: {
                    h_id: {
                        title: "H_id",
                        width: '10%'
                    },
                    tag_id: {
                        title: "Tag_id",
                        width: '10%'
                    },
                    dtime: {
                        title: 'Dtime',
                        width: '40%',
                        type: 'datetime',
                        displayFormat: 'd/m/Y H:i'
//                        displayFormat: 'YYYY-MM-DD HH:MM:SS'
                    },
                    value: {
                        title: 'Value',
                        width: '40%'
                    }
                }
            });
            $('#tableContainer').jtable('load');
        });
    </script>
</head>
<%--<jsp:useBean id="data" class="ru.scada.controllers.data_delegation.DataBean" scope="request"/>--%>
<body>
<header class="header">
    <!--<h1 class="site_title"></h1>-->
    <h2 class="section_title">Demo</h2>
</header>
<div id="navigator">
    <ul id="nav">
        <li class="nav_tab">
            <a href="index.jsp"><p>Данные</p></a>
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
<div id="tableContainer"></div>

<table>
    <c:forEach items="${data}" var="params">
        <tr>
            <td>
                <c:out value="${params.columnName}"/>
            </td>
                <%--<td>
                    <c:out value="${params.value}"/>
                </td>--%>
        </tr>
    </c:forEach>
</table>

<%--<jsp:getProperty name="data" property="dtime1"/>--%>
<%--<jsp:getProperty name="data" property="dtime2"/>--%>
<%--<jsp:getProperty name="data" property="selectData"/>--%>
</body>
</html>
