<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Данные</title>
    <%--<meta name="description" content="Pushy is an off-canvas navigation menu for your website.">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

    <link href="css/reset.css" rel="stylesheet" type="text/css">
    <!--<link href="css/normalize.css" rel="stylesheet" type="text/css"/>-->
    <!--<link href="css/demo.css" rel="stylesheet" type="text/css"/>-->
    <link href="css/data/data.css" rel="stylesheet" type="text/css"/>
    <link href="css/data/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>

    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/data/data.js"></script>
    <script src="js/data/jquery.datetimepicker.js"></script>
    <script src="js/data/pushy.js"></script>
</head>
<body>

<header class="header">
    <h1 class="site_title">
        <div class="menu-btn">&#9776; Menu</div>
        <!--<a href="index.html">Demo</a>-->
    </h1>
    <h2 class="section_title">Demo</h2>
</header>

<nav class="pushy pushy-left">
    <ul>
        <li><a href="#">Item1</a></li>
        <li><a href="#">Item2</a></li>
        <li><a href="#">Item3</a></li>
        <li><a href="#">Item4</a></li>
        <li><a href="#">Item5</a></li>
        <li><a href="#">Item6</a></li>
    </ul>
</nav>

<div class="site-overlay"></div>

<section id="container">
    <article class="module width_quarter">
        <header><h3>Demo</h3></header>
        <div class="module_content">
            <form class="send_data">
                <input class="from" id="datetimepicker1" type="text" placeholder="Начало">
                <input class="to" id="datetimepicker2" type="text" placeholder="Конец">
                <input type="submit" value="OK">
            </form>
        </div>
        <footer>
        </footer>
    </article>
    <article class="module width_3_quarter">
        <header><h3>Demo</h3></header>
        <div class="module_content">
        </div>
        <footer></footer>
    </article>
</section>

<!--<div id="navigator">
    <ul id="nav">
        <li class="nav_tab">
            <a href="index.html"><p>Данные</p></a>
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

<form>
    <ul class="form_style">
        <li>
            <input type="text" class="from" placeholder="Начало" id="datetimepicker1">
            <input type="text" class="to" placeholder="Конец" id="datetimepicker2">
        </li>
        <li>
            <input type="submit" value="OK">
        </li>
    </ul>
</form>-->
</body>
</html>