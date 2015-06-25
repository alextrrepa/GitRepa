<%@taglib uri = "http://java.sun.com/jstl/core" prefix = "c" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>
<!DOCTYPE>
<html>
<head>
  <meta charset="utf-8">
  <title>Modbus Настройки</title>
  <link href="css/reset.css" rel="stylesheet">
  <link href="css/admin_style.css" rel="stylesheet">
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

<nav class="sidebar" <%--style="height: 1728px"--%>>
  <h3>Опрос</h3>
  <ul>
    <li class="icn_settings"><a href="ModbusEdit.do?action=getAll">Modbus настройки</a></li>
  </ul>
</nav>

<section class="content" <%--style="height: 1728px"--%>>
  <h4 class="alert_info">Настройка Modbus опроса.</h4>
  <article class="module modbus_tree">
    <header>
      <h3 class="tabs_involved">Структура Modbus</h3>
    </header>
    <div class="tree">
      <ul class="container">
        <li class="node root expandOpen">
          <div class="expand"></div>
          <div class="nodecontent">Сервер</div>
          </li>
           <c:forEach items="${rtuEntities}" var="node">
          <li class="node expandOpen">
            <div class="expand"></div>
            <div class="nodeContent">
               <c:out value="${node.name}"/>
            </div>
          </li>
          <ul class="container">
            <c:set var="device" value="${node.deviceEntity}"/>
            <c:forEach items="${device}" var="device">
              <li class="node expandOpen">
                 <div class="expand"></div>
                 <div class="nodeContent">
                    <c:out value="${device.name}"/>
                 </div>
                </li>
              <ul>
                <c:set var="tag" value="${device.tagEntities}"/>
                <c:forEach items="${tag}" var="tag">
                  <li class="node last">
                    <div class="expand"></div>
                    <div class="nodeContent">
                      <c:out value="${tag.name}"/>
                    </div>
                  </li>
                </c:forEach>
              </ul>
            </c:forEach>
          </ul>
        </c:forEach>
      </ul>
    </div>
  </article>
  <article class="modbus_param"></article>
</section>

</body>
</html>