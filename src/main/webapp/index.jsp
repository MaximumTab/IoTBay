<%@ page import="com.iotbay.model.Customer" %>
<%@ page session="true" %>
<%
    Customer loggedIn = (Customer) session.getAttribute("loggedInCustomer");
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
    </div>
</div>

<div class="landingbody">
    <div class="landing-text">
        <b>Shop</b>
        <b>Anywhere</b>
        <b>Anytime</b>
    </div>
    <div class="landing-image">
        <img src="images/shopping.png" alt="Shopping" />
    </div>

    <div class="current">
        <% if (loggedIn != null) { %>
        <p>Currently logged-in user: <strong><%= loggedIn.getFname() %></strong></p>
        <% } else { %>
        <p></p>
        <% } %>
    </div>
</div>
</body>
</html>
