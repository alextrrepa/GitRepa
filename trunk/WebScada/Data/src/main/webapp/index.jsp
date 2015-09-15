<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Данные</title>
    <link href="css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="css/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>

    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/jquery.datetimepicker.js"></script>
    <script src="js/data.js"></script>
</head>
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

<form action="Data.do" method="post">
    <input id="datetimepicker1" class="from" placeholder="Начало" type="text" name="dtime1">
    <input id="datetimepicker2" class="to" placeholder="Конец" type="text" name="dtime2">
    <select class="selectType" name="selectData">
        <option selected="selected">Выберите период</option>
        <option value="hoursData">Часовые</option>
        <option value="dayData">Суточные</option>
    </select>
    <input type="submit" value="OK">
</form>

</body>
</html>