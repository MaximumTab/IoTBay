<%@ page import="com.iotbay.model.User" %>
<%@ page import="com.iotbay.model.dao.DAO" %>
<%@ page session="true" %>

<%
    DAO db = (DAO)session.getAttribute("db");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>IoTBay</title>
    <link rel="stylesheet" href="StyleSheet.css">
</head>
<body>
<div class="topnav">
    <div class="nav-left">
        <img src="images/iotbay.png" alt="logo" width="60" height="60" />
    </div>
    <b class="welcome-text">Welcome to IoTBAY</b>
    <div class="nav-right">
        <button onclick="location.href='LoginPage.jsp'">Log in</button>
        <button onclick="location.href='RegisterPage.jsp'">Register</button>
        <button onclick="location.href='DevicesListView.jsp'">View Devices</button>
    </div>
</div>

<div class="landingbody">
    <div class="landing-text">

        <b>Shop</b>
        <b>Anywhere</b>
        <b>Anytime</b>
        <p style="padding-top: 20px; font-size: 14px;">
        </p>

    </div>
    <div class="landing-image">
        <img src="images/shopping.png" alt="Shopping" />
    </div>

    <div class="current">
    </div>
</div>
</body>
</html>
