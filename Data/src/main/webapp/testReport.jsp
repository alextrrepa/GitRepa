<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@page contentType="application/pdf" %>--%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <%
            Connection conn = null;
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/scada?user=postgres&password=cezet123");
            String path1 = request.getServletContext().getRealPath(request.getContextPath());
            String path = request.getContextPath();
        %>
        <p><%=path%></p>
        <p><%=path1%></p>
    </body>
</html>
