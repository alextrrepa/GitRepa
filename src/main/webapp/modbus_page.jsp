<%@ page import="org.webscada.entities.NodeEntity" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Modbus Настройки</title>
  <link href="css/reset.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">
</head>
<body>

<%
 List<NodeEntity> rtuList = (List<NodeEntity>) request.getAttribute("rtuEntities");
 System.out.println(rtuList.size());
%>

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

<nav class="sidebar" <%--style="height: 1728px"--%>>
  <h3>Опрос</h3>
  <ul>
    <li class="icn_settings"><a href="modbus_page.jsp">Modbus настройки</a></li>
  </ul>
</nav>

<section class="content" <%--style="height: 1728px"--%>>
  <h4 class="alert_info">Настройка Modbus опроса.</h4>
  <article class="module modbus_tree">
    <header>
      <h3 class="tabs_involved">Структура Modbus</h3>
    </header>
    <ul>
      <li>Сервер</li>
      <%--<c:import url = "/Modbusedit"/>--%>
      <%--<c:set var="rtuList" value="${requestScope.rtuEntities}" />--%>
      <%--<c:forEach items="${rtuList}" var="rtu">
      </c:forEach>--%>
    </ul>
  </article>
  <article class="modbus_param"></article>
</section>

</body>
</html>