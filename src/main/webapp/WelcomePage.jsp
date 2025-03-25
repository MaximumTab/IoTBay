<%--
  Created by IntelliJ IDEA.
  User: maksy
  Date: 18/03/2025
  Time: 7:26 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <link rel="stylesheet" href="StyleSheet.css">
    <title>Welcome Page</title>
</head>
<body>
<div class="welcome-container">
<h1>Welcome!</h1>

<%
    session = request.getSession();
    String fullname = (String) session.getAttribute("fullname");
    boolean validUser = true;
    if (validUser = true) {
%>
<p>Welcome, <strong><%= (String) session.getAttribute("fullname") %></strong>! You are logged in.</p>
<a href="MainPage.jsp">Go to Main Page</a>
<%
} else {
%>
<p>You are not logged in. Please <a href="LoginPage.jsp">log in</a>.</p>
<%
    }
%>
</div>
</body>
</html>
