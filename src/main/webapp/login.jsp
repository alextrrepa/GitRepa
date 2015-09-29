<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <title>Вход</title>
    <link href="css/reset.css" rel="stylesheet">
    <link href="css/auth/auth.css" rel="stylesheet">
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/auth/auth.js"></script>
</head>
<body>
<div class="container">
    <section id="content">
        <form action="Login.do" method="post">
            <h1>Вход</h1>
            <div>
                <input type="text" placeholder="Пользователь" required="" id="username" name="user"/>
            </div>
            <div>
                <input type="password" placeholder="Пароль" required="" id="password" name="password"/>
            </div>
            <div>
                <input type="submit" value="Войти"/>
                <%--<a href="#">Lost your password?</a>--%>
                <%--<a href="#">Register</a>--%>
            </div>
        </form>
        <!-- form -->
        <div class="button">
            <%--<a href="#">Download source file</a>--%>
        </div>
        <!-- button -->
    </section>
    <!-- content -->
</div>
<!-- container -->
</body>
</html>
